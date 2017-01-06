package br.com.tatica.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShortestPathFinder {
	
    private List<Node> nodes;
    private List<Edge> edges;
    // 
    private Set<Node> settledNodes;
    //
    private Set<Node> unSettledNodes;
    private Map<Node, Node> predecessors;
    private Map<Node, Integer> distance;
    
    public ShortestPathFinder(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Node>(graph.getNodes());
        this.edges = new ArrayList<Edge>(graph.getEdges());
    }

}
