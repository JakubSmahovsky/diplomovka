package simple_tamarin.sourcesCompiler;

import java.util.ArrayList;

import simple_tamarin.Constants;
import simple_tamarin.dataStructures.StModel;
import simple_tamarin.dataStructures.term.*;
import simple_tamarin.errors.Errors;
import simple_tamarin.sourcesCompiler.term.*;
import simple_tamarin.sourcesCompiler.graph.*;
import simple_tamarin.sourcesCompiler.graph.node.*;
import simple_tamarin.sourcesParser.SourcesParser.*;

public class SourcesCompilerVisitor {
  private StModel model;
  
  public SourcesCompilerVisitor(StModel model) {
    this.model = model;
  }

  public void visitSources(SourcesContext ctx) {
    System.out.println("sources");
    for (GroupContext gctx : ctx.group()) {
      visitGroup(gctx);
    }
  }

  public void visitGroup(GroupContext ctx) {
    Fact goal = visitFact(ctx.goal().fact());
    System.out.println("Group " + goal);
    ArrayList<Source> sources = new ArrayList<>();
    for (SourceContext sctx : ctx.source()) {
      Source source = visitSource(sctx);
      sources.add(source);
      System.out.println(source);
    }

    model.sourceGroups.add(new SourceGroup(goal, sources));
  }

  public Source visitSource(SourceContext ctx) {
    String name = ctx.name.getText();
    Graph graph = visitGraph(ctx.jsonObj(), name);
    return new Source(model, name, graph);
  }

  public Fact visitFact(FactContext ctx) {
    boolean persistent = ctx.PERSISTENT() != null;
    String factName = ctx.IDENTIFIER().getText();
    ArrayList<Term> terms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      terms.add(visitTerm(tctx));
    }
    
    return new Fact(persistent, factName, terms);
  }

  public Term visitTerm(TermContext ctx) {
    if (ctx.function() != null) {
      return visitFunction(ctx.function());
    } else if (ctx.tuple() != null) {
      return visitTuple(ctx.tuple());
    } else if (ctx.variable() != null) {
      return visitVariable(ctx.variable());
    }
    return null; // TODO: debug
  }

  /**
   * IMPORTANT: order of key and value are reversed in Tamarin,
   * functions have format f(value, key)
   */
  public Term visitFunction(FunctionContext ctx) {
    String functionName = ctx.IDENTIFIER().getText();
    switch (functionName) {
      case Constants.SENC: {
        Term value = visitTerm(ctx.term(0));
        Term key = visitTerm(ctx.term(1));
        return new FunctionSenc(key, value);
      }
      case Constants.SDEC: {
        Term value = visitTerm(ctx.term(0));
        Term key = visitTerm(ctx.term(1));
        Term decoded = null;
        return new FunctionSdec(key, value, decoded);
      }
      case Constants.HASH: {
        Term subterm = visitTerm(ctx.term(0));
        return new FunctionHash(subterm);
      }
      case Constants.FIRST: {
        Term subterm = visitTerm(ctx.term(0));
        return new FunctionFirst(subterm);
      }
      case Constants.SECOND: {
        Term subterm = visitTerm(ctx.term(0));
        return new FunctionSecond(subterm);
      }
    }
    return new Variable("Function ph"); // TODO debug
  }

  public Term visitTuple(TupleContext ctx) {
    ArrayList<Term> subterms = new ArrayList<>();
    for (TermContext tctx : ctx.term()) {
      subterms.add(visitTerm(tctx));
    }
    return new Tuple(subterms);
  }

  public Variable visitVariable(VariableContext ctx) {
    String name = ctx.IDENTIFIER().getText();
    String number = (ctx.NUMBER() != null) ? ("." + ctx.NUMBER().getText()) : "";
    return new Variable(name + number);
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
