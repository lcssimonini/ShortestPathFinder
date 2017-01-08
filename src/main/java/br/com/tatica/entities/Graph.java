package br.com.tatica.entities;

import java.util.Collection;

// definição de um grafo, com um conjunto de nós e arestas.
public class Graph {
	private Collection<Node> nodes;
	private Collection<Edge> edges;

	public Graph(Collection<Node> nodes, Collection<Edge> edges) {
		this.nodes = nodes;
		this.edges = edges;
	}

	public Collection<Node> getNodes() {
		return nodes;
	}

	public Collection<Edge> getEdges() {
		return edges;
	}

}