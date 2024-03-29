package dipl.sourcesCompiler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import dipl.Constants;
import dipl.dataStructures.Block;
import dipl.dataStructures.Model;
import dipl.dataStructures.outputTerm.*;
import dipl.errors.Errors;
import dipl.sourcesCompiler.goal.*;
import dipl.sourcesCompiler.goal.factGoal.HiddenFactGoal;
import dipl.sourcesCompiler.goal.factGoal.PrincipalRuleGoal;
import dipl.sourcesCompiler.graph.*;
import dipl.sourcesCompiler.graph.node.*;
import dipl.sourcesCompiler.graph.node.adversaryRuleNode.*;
import dipl.sourcesCompiler.graph.node.functionNode.*;
import dipl.sourcesParser.SourcesParser.*;

public class SourcesCompilerVisitor {
	private FileWriter writer;
  private Model model;
  
  public SourcesCompilerVisitor(Model model, FileWriter writer) {
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
      return new AdversaryGoal(terms.get(0)); // intruder goal fact contains exactly 1 term
    } else if (!persistent && symbol.matches(Constants.FACT_PREFIX_PRINCIPALID + "[0-9]+" + Constants.NAME_SEPARATOR + "[0-9]+")) {
      Block block = model.blocks.get(Integer.parseInt(symbol.split(Constants.NAME_SEPARATOR)[1]));
      return new PrincipalRuleGoal(block, persistent, symbol, terms);
    } else {
      return new HiddenFactGoal(persistent, symbol, terms);
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
    String number = ctx.nameID() == null ? "" : ctx.nameID().getText();
    return new OutputVariable(model, name, number);
  }

  /**
   * IMPORTANT: order of key and value are reversed in Tamarin,
   * functions have format f(value, key)
   */
  public OutputTerm visitFunction(FunctionContext ctx) {
    String functionName = ctx.IDENTIFIER().getText();
    switch (functionName) {
      case Constants.T_FIRST: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new FunctionFirst(subterm);
      }
      case Constants.T_SECOND: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new FunctionSecond(subterm);
      }
      case Constants.T_PK: {
        OutputTerm sk = visitTerm(ctx.term(0));
        return new OutputFunctionPk(sk);
      }
      case Constants.T_SIGN: {
        OutputTerm message = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionSign(key, message);
      }
      case Constants.T_VERIFY: {
        OutputTerm signature = visitTerm(ctx.term(0));
        OutputTerm message = visitTerm(ctx.term(1));
        OutputTerm key = visitTerm(ctx.term(2));
        return new OutputFunctionVerify(key, message, signature);
      }
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
      case Constants.T_EXP_WORD: {
        OutputTerm base = visitTerm(ctx.term(0));
        OutputTerm exponent = visitTerm(ctx.term(1));
        return new OutputExponentiation(base, exponent);
      }
      case Constants.T_MUL_WORD: {
        OutputTerm left = visitTerm(ctx.term(0));
        OutputTerm right = visitTerm(ctx.term(1));
        return new OutputMultiplication(left, right);
      }
      case Constants.T_INVERSE: {
        OutputTerm base = visitTerm(ctx.term(0));
        return new OutputFunctionInverse(base);
      }
      case Constants.T_TUPLE_WORD: {
        OutputTerm fst = visitTerm(ctx.term(0));
        OutputTerm snd = visitTerm(ctx.term(1));
        return new OutputTuple(fst, snd);
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
    OutputTerm metaTerm = null;
    for (JsonKeyValueContext kvctx : ctx.jsonKeyValue()) {
      switch (kvctx.jsonKey().jsonString().getText()) {
        case (Constants.JSON_NODEID):
          nodeID = kvctx.jsonValue().jsonString().getText();
          break;
        case (Constants.JSON_NODELABEL):
          nodeLabel = kvctx.jsonValue().jsonString().getText();
          break;
        case (Constants.JSON_NODETYPE):
          nodeType = kvctx.jsonValue().jsonString().getText();
          break;
        case (Constants.JSON_NODEMETA):
          metaTerm = visitGraphMetadata(kvctx.jsonValue().jsonObj());
          break;
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
      case (Constants.JSON_NODE_ADVERSARY): {
        switch (nodeLabel) {
          case Constants.JSON_FUNCTION_LABEL_COERCE:
            return new CoerceRuleNode(nodeID, nodeLabel, metaTerm);
          case Constants.JSON_FUNCTION_LABEL_FRESH:
            return new FreshRuleNode(nodeID, nodeLabel, metaTerm);
          case Constants.JSON_FUNCTION_LABEL_RECEIVE:
            return new ReceiveRuleNode(nodeID, nodeLabel, metaTerm);
          case Constants.JSON_FUNCTION_LABEL_SEND:
            return new SendRuleNode(nodeID, nodeLabel, metaTerm);
          default: {
            // function application
            // first name is the construction/deconstruction modifier, last name is the function symbol
            String[] labelParts = nodeLabel.split(Constants.NAME_SEPARATOR);
            if (labelParts[0].equals(Constants.JSON_FUNCTION_PREFIX_CONSTRUCT)) {
              return new ConstructionNode(nodeID, labelParts[labelParts.length-1], metaTerm);
            } else if (labelParts[0].equals(Constants.JSON_FUNCTION_PREFIX_DECONSTRUCT)) {
              return new DeconstructionNode(nodeID, labelParts[labelParts.length-1], metaTerm);
            } else {
              Errors.DebugUnexpectedValueType("function prefix", labelParts[0], "parseLabel in FunctionNode");
              return null;
            }
          }
        }
      }
      case (Constants.JSON_NODE_UNSOLVED):
        return new UnsolvedNode(nodeID, nodeLabel, metaTerm);
      case (Constants.JSON_NODE_FRESH):
        return new FreshNode(nodeID, metaTerm);
      case (Constants.JSON_NODE_MISSING):
        return new StubNode(nodeID);
      default:
        Errors.DebugUnexpectedTokenType(nodeType, "visitGraphNode in sources compiler");
    }
    return null;
  }

  /**
   * If there is exactly one conclusion with exactly one term, then return this term
   * otherwise try action too
   */
  public OutputTerm visitGraphMetadata(JsonObjContext ctx) {
    JsonObjContext block = null;
    for (JsonKeyValueContext kvctx : ctx.jsonKeyValue()) {
      if (kvctx.jsonKey().jsonString().getText().equals(Constants.JSON_NODEMETA_CONCLUSIONS)) {
        if (kvctx.jsonValue().jsonArray().jsonValue().size() == 1) {
          block = kvctx.jsonValue().jsonArray().jsonValue(0).jsonObj();
        }
        break;
      }
    }

    if (block == null) {
      for (JsonKeyValueContext kvctx : ctx.jsonKeyValue()) {
        if (kvctx.jsonKey().jsonString().getText().equals(Constants.JSON_NODEMETA_ACTIONS)) {
          if (kvctx.jsonValue().jsonArray().jsonValue().size() == 1) {
            block = kvctx.jsonValue().jsonArray().jsonValue(0).jsonObj();
          }
          break;
        }
      }
    }
    if (block == null) {
      return null;
    }

    JsonObjContext term = null;
    for (JsonKeyValueContext kvctx : block.jsonKeyValue()) {
      if (kvctx.jsonKey().jsonString().getText().equals(Constants.JSON_NODEMETA_TERMS)) {
        if (kvctx.jsonValue().jsonArray().jsonValue().size() != 1) {
          return null;
        }
        term = kvctx.jsonValue().jsonArray().jsonValue(0).jsonObj();
      }
    }

    for (JsonKeyValueContext kvctx : term.jsonKeyValue()) {
      if (kvctx.jsonKey().jsonString().getText().equals(Constants.JSON_NODEMETA_FULLTERM) ||
          kvctx.jsonKey().jsonString().getText().equals(Constants.JSON_NODEMETA_CONSTANT)) {
        if (kvctx.jsonValue().jsonString().term() == null) {
          return null;
        }
        return visitTerm(kvctx.jsonValue().jsonString().term());
      }
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
