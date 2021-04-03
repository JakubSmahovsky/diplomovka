package simpleT.sourcesCompiler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import simpleT.Constants;
import simpleT.dataStructures.STModel;
import simpleT.dataStructures.outputTerm.*;
import simpleT.errors.Errors;
import simpleT.sourcesCompiler.goal.*;
import simpleT.sourcesCompiler.graph.*;
import simpleT.sourcesCompiler.graph.node.*;
import simpleT.sourcesParser.SourcesParser.*;

public class SourcesCompilerVisitor {
	private FileWriter writer;
  private STModel model;
  
  public SourcesCompilerVisitor(STModel model, FileWriter writer) {
    this.model = model;
    this.writer = writer;
  }

  public void visitSources(SourcesContext ctx) {
    for (GroupContext gctx : ctx.group()) {
      visitGroup(gctx);
    }
    
    model.sortSourceGroups();
    
    try {
      for (SourceGroup group : model.sourceGroups) {
        writer.write(group.render().toString());
      }
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();;
    }
  }

  public void visitGroup(GroupContext ctx) {
    Goal goal = visitFact(ctx.goal().fact());
    ArrayList<Source> sources = new ArrayList<>();
    for (SourceContext sctx : ctx.source()) {
      Source source = visitSource(sctx);
      sources.add(source);
    }
    
    model.sourceGroups.add(new SourceGroup(goal, sources));
  }

  public Source visitSource(SourceContext ctx) {
    String name = ctx.name.getText();
    Graph graph = visitGraph(ctx.jsonObj(), name);
    return new Source(model, name, graph);
  }

  public Goal visitFact(FactContext ctx) {
    boolean persistent = ctx.persistent != null;
    String symbol = ctx.IDENTIFIER().getText();
    ArrayList<OutputTerm> terms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      terms.add(visitTerm(tctx));
    }
    if (persistent && symbol.equals(Constants.INTRUDER_KNOWS_OUTPUT)) {
      return new IntruderGoal(terms.get(0)); // intruder goal fact contains exactly 1 term
    } else {
      return new FactGoal(persistent, symbol, terms);
    }
    
  }

  public OutputTerm visitTerm(TermContext ctx) {
    // bracketed term
    if (ctx.term().size() == 1) {
      return visitTerm(ctx.term(0));
    }
    if (ctx.constant() != null) {
      return visitConstant(ctx.constant());
    }
    if (ctx.variable() != null) {
      return visitVariable(ctx.variable());
    }
    if (ctx.function() != null) {
      return visitFunction(ctx.function());
    }
    if (ctx.tuple() != null) {
      return visitTuple(ctx.tuple());
    }
    if (ctx.infixOp != null) {
      if (ctx.infixOp.getText().equals(Constants.T_EXP)) {
        return new OutputExponentiation(visitTerm(ctx.term(0)), visitTerm(ctx.term(1)));
      }
      if (ctx.infixOp.getText().equals(Constants.T_MUL)) {
        return new OutputMultiplication(visitTerm(ctx.term(0)), visitTerm(ctx.term(1)));
      }
    }

    Errors.DebugUnexpectedTokenType(ctx.getText(), "soucres compiler visitTerm");;
    return null;
  }

  public OutputConstant visitConstant(ConstantContext ctx) {
    return new OutputConstant(ctx.word.getText());
  }

  public OutputVariable visitVariable(VariableContext ctx) {
    String name = ctx.IDENTIFIER().getText();
    String number = (ctx.NUMBER() != null) ? ("." + ctx.NUMBER().getText()) : "";
    return new OutputVariable(model, name, number);
  }

  /**
   * IMPORTANT: order of key and value are reversed in Tamarin,
   * functions have format f(value, key)
   */
  public OutputTerm visitFunction(FunctionContext ctx) {
    String functionName = ctx.IDENTIFIER().getText();
    switch (functionName) {
      case Constants.T_SENC: {
        OutputTerm value = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionSenc(key, value);
      }
      case Constants.T_SDEC: {
        OutputTerm encryptedValue = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionSdec(key, encryptedValue);
      }
      case Constants.T_AENC: {
        OutputTerm value = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionAenc(key, value);
      }
      case Constants.T_ADEC: {
        OutputTerm encryptedValue = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionAdec(key, encryptedValue);
      }
      case Constants.T_HASH: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new OutputFunctionHash(subterm);
      }
      case Constants.T_FIRST: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new FunctionFirst(subterm);
      }
      case Constants.T_SECOND: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new FunctionSecond(subterm);
      }
    }
    Errors.DebugUnexpectedTokenType(ctx.IDENTIFIER().getText(), "visitFunction in sources compiler");
    return null;
  }

  public OutputTuple visitTuple(TupleContext ctx) {
    ArrayList<OutputTerm> subterms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      subterms.add(visitTerm(tctx));
    }
    return new OutputTuple(subterms);
  }

  public Graph visitGraph(JsonObjContext ctx, String sourceName) {
    // { "graph" : [ { graph } ] }
    JsonObjContext graph = ctx.jsonKeyValue(0).jsonValue().jsonArray().jsonValue(0).jsonObj();
    
    JsonArrayContext edgectxs = null;
    JsonArrayContext nodectxs = null;
    for (JsonKeyValueContext kvctx : graph.jsonKeyValue()) {
      switch (kvctx.jsonKey().jsonString().getText()) {
        case Constants.JSON_EDGES:
          edgectxs = kvctx.jsonValue().jsonArray();
        case Constants.JSON_NODES:
          nodectxs = kvctx.jsonValue().jsonArray();
      }
    }
    if (edgectxs == null) {
      Errors.DebugUnexpectedTokenType("graph without edges", "visitGraph in source compiler");
    }
    if (nodectxs == null) {
      Errors.DebugUnexpectedTokenType("graph without nodes", "visitGraph in source compiler");
    }

    ArrayList<Node> nodes = new ArrayList<>();
    for (JsonValueContext nodectx : nodectxs.jsonValue()) {
      nodes.add(visitGraphNode(nodectx.jsonObj()));
    }
    ArrayList<Edge> edges = new ArrayList<>();
    for (JsonValueContext edgectx : edgectxs.jsonValue()) {
      edges.add(visitGraphEdge(edgectx.jsonObj(), nodes));
    }
    return new Graph(nodes, edges, sourceName);
  }

  public Node visitGraphNode(JsonObjContext ctx) {
    String nodeID = null;
    String nodeLabel = null;
    String nodeType = null;
    for (JsonKeyValueContext kvctx : ctx.jsonKeyValue()) {
      switch (kvctx.jsonKey().jsonString().getText()) {
        case (Constants.JSON_NODEID):
          nodeID = kvctx.jsonValue().jsonString().getText();
        case (Constants.JSON_NODELABEL):
          nodeLabel = kvctx.jsonValue().jsonString().getText();
        case (Constants.JSON_NODETYPE):
          nodeType = kvctx.jsonValue().jsonString().getText();
      }
    }
    if (nodeID == null) {
      Errors.DebugUnexpectedTokenType("node without id", "visitGraphNode in source compiler");
    }
    if (nodeLabel == null) {
      Errors.DebugUnexpectedTokenType("node without label", "visitGraphNode in source compiler");
    }
    if (nodeType == null) {
      Errors.DebugUnexpectedTokenType("node without type", "visitGraphNode in source compiler");
    }

    switch (nodeType) {
      case (Constants.JSON_NODE_BLOCK): {
        if (nodeLabel.matches(Constants.FACT_PREFIX_PRINCIPALID + "[0-9]+" + Constants.NAME_SEPARATOR + "[0-9]+")) {
          return new PrincipalRuleNode(nodeID, nodeLabel, model);
        } else if (nodeLabel.matches(Constants.FACT_PREFIX_PRINCIPALID + "[0-9]+" + Constants.NAME_SEPARATOR + Constants.RULE_LONG_TERM_REVEAL)) {
          return new RevealRuleNode(nodeID, nodeLabel, model);
        } else {
          return new CustomRuleNode(nodeID, nodeLabel);
        }
      }
      case (Constants.JSON_NODE_FUNCTION):
        return new FunctionNode(nodeID, nodeLabel);
      case (Constants.JSON_NODE_UNSOLVED):
        return new UnsolvedNode(nodeID, nodeLabel);
      case (Constants.JSON_NODE_FRESH):
        return new FreshNode(nodeID);
      case (Constants.JSON_NODE_MISSING):
        return new StubNode(nodeID);
      default:
        Errors.DebugUnexpectedTokenType(nodeType, "visitGraphNode in sources compiler");
    }
    return null;
  }

  public Edge visitGraphEdge(JsonObjContext ctx, ArrayList<Node> nodes) {
    String fromID = null;
    String toID = null;
    for (JsonKeyValueContext kvctx : ctx.jsonKeyValue()) {
      switch (kvctx.jsonKey().jsonString().getText()) {
        case (Constants.JSON_EDGEFROM):
          fromID = Node.ArrowEndpointToNode(kvctx.jsonValue().jsonString().getText());
        case (Constants.JSON_EDGETO):
          toID = Node.ArrowEndpointToNode(kvctx.jsonValue().jsonString().getText());
      }
    }
    if (fromID == null) {
      Errors.DebugUnexpectedTokenType("edge without origin", "visitGraphEdge in source compiler");
    }
    if (toID == null) {
      Errors.DebugUnexpectedTokenType("edge without target", "visitGraphEdge in source compiler");
    }
    
    Node from = null;
    Node to = null;
    for (Node node : nodes) {
      if (node.id.equals(fromID)) {
        from = node;
        break;
      }
    }
    for (Node node : nodes) {
      if (node.id.equals(toID)) {
        to = node;
        break;
      }
    }
    if (from == null) {
      Errors.DebugUnexpectedTokenType("edge with wrong origin", "visitGraphEdge in source compiler");
    }
    if (to == null) {
      Errors.DebugUnexpectedTokenType("edge with wrong target", "visitGraphEdge in source compiler");
    }
    
    return new Edge(from, to);
  }
}
