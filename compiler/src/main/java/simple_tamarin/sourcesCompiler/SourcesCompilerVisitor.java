package simple_tamarin.sourcesCompiler;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.STModel;
import simple_tamarin.dataStructures.outputTerm.*;
import simple_tamarin.errors.Errors;
import simple_tamarin.errors.STException;
import simple_tamarin.sourcesCompiler.graph.*;
import simple_tamarin.sourcesCompiler.graph.node.*;
import simple_tamarin.sourcesParser.SourcesParser.*;

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
        writer.write(group.render().toString() + "\r\n" + "\r\n");
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
    boolean persistent = ctx.PERSISTENT() != null;
    String factName = ctx.IDENTIFIER().getText();
    ArrayList<OutputTerm> terms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      terms.add(visitTerm(tctx));
    }
    
    return new Goal(persistent, factName, terms);
  }

  public OutputTerm visitTerm(TermContext ctx) {
    if (ctx.terminatingTerm() != null && ctx.multiplication() == null) {
      return visitTerminatingTerm(ctx.terminatingTerm());
    }
    if (ctx.term() != null) { // bracketed term
      return visitTerm(ctx.term());
    }

    if (ctx.multiplication() != null) {
      throw new STException("output compiling for multiplication is not implemented");
    }
    return null; // TODO debug
  }

  public OutputTerm visitTerminatingTerm(TerminatingTermContext ctx) {
    
    if (ctx.variable() != null) {
      return visitVariable(ctx.variable());
    }
    if (ctx.function() != null) {
      return visitFunction(ctx.function());
    }
    if (ctx.tuple() != null) {
      return visitTuple(ctx.tuple());
    }
    return null; // TODO: debug
  }

  public OutputConstant visitConstant(ConstantContext ctx) {
    return new OutputConstant(ctx.word.getText());
  }

  public OutputVariable visitVariable(VariableContext ctx) {
    String name = ctx.IDENTIFIER().getText();
    String number = (ctx.NUMBER() != null) ? ("." + ctx.NUMBER().getText()) : "";
    return new OutputVariable(name, number);
  }

  /**
   * IMPORTANT: order of key and value are reversed in Tamarin,
   * functions have format f(value, key)
   */
  public OutputTerm visitFunction(FunctionContext ctx) {
    String functionName = ctx.IDENTIFIER().getText();
    switch (functionName) {
      case Constants.SENC: {
        OutputTerm value = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionSenc(key, value);
      }
      case Constants.SDEC: {
        OutputTerm encodedValue = visitTerm(ctx.term(0));
        OutputTerm key = visitTerm(ctx.term(1));
        return new OutputFunctionSdec(key, encodedValue);
      }
      case Constants.HASH: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new OutputFunctionHash(subterm);
      }
      case Constants.FIRST: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new FunctionFirst(subterm);
      }
      case Constants.SECOND: {
        OutputTerm subterm = visitTerm(ctx.term(0));
        return new FunctionSecond(subterm);
      }
    }
    return new OutputVariable("Function ph", ""); // TODO debug
  }

  public OutputTuple visitTuple(TupleContext ctx) {
    ArrayList<OutputTerm> subterms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      subterms.add(visitTerm(tctx));
    }
    return new OutputTuple(subterms);
  }

  public Graph visitGraph(JsonObjContext ctx, String sourceName) {
    // { "graph" : [ { graph } ] } // TODO assertions
    JsonObjContext graph = ctx.jsonKeyValue(0).jsonValue().jsonArray().jsonValue(0).jsonObj();
    
    JsonArrayContext edgectxs = null;
    JsonArrayContext nodectxs = null;
    for (JsonKeyValueContext kvctx : graph.jsonKeyValue()) {
      // TODO assert edges and nodes are only assigned once
      switch (kvctx.jsonKey().jsonString().getText()) {
        case Constants.JSON_EDGES:
          edgectxs = kvctx.jsonValue().jsonArray();
        case Constants.JSON_NODES:
          nodectxs = kvctx.jsonValue().jsonArray();
      }
    }
    // TODO assert edges and nodes aren't null

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
      // TODO assert values are only assigned once
      switch (kvctx.jsonKey().jsonString().getText()) {
        case (Constants.JSON_NODEID):
          nodeID = kvctx.jsonValue().jsonString().getText();
        case (Constants.JSON_NODELABEL):
          nodeLabel = kvctx.jsonValue().jsonString().getText();
        case (Constants.JSON_NODETYPE):
          nodeType = kvctx.jsonValue().jsonString().getText();
      }
    }
    // TODO assert values aren't null
    switch (nodeType) {
      case (Constants.JSON_NODE_BLOCK):
        return new BlockNode(nodeID, nodeLabel, model);
      case (Constants.JSON_NODE_FUNCTION):
        return new FunctionNode(nodeID, nodeLabel);
      case (Constants.JSON_NODE_UNSOLVED):
        return new UnsolvedNode(nodeID, nodeLabel);
      case (Constants.JSON_NODE_FRESH):
        return new FreshNode(nodeID);
      case (Constants.JSON_NODE_MISSING):
        return new StubNode(nodeID);
      default:
        System.out.println("unrecognized node type:" + nodeType);
    }
    Errors.DebugUnexpectedTokenType(nodeType, "visitGraphNode");
    return null;
  }

  public Edge visitGraphEdge(JsonObjContext ctx, ArrayList<Node> nodes) {
    String fromID = null;
    String toID = null;
    for (JsonKeyValueContext kvctx : ctx.jsonKeyValue()) {
      // TODO assert IDs are only assigned once
      switch (kvctx.jsonKey().jsonString().getText()) {
        case (Constants.JSON_EDGEFROM):
          fromID = Node.ArrowEndpointToNode(kvctx.jsonValue().jsonString().getText());
        case (Constants.JSON_EDGETO):
          toID = Node.ArrowEndpointToNode(kvctx.jsonValue().jsonString().getText());
      }
    }
    // TODO assert IDs aren't null
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
    // TODO assert from and to aren't null
    
    return new Edge(from, to);
  }
}
