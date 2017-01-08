package br.com.tatica.entities;

// descrição de uma aresta do grafo, com no inicial, no final e custo
public class Edge {

	private final String id;
	private final Node source;
	private final Node destination;
	private final Integer cost;

	public Edge(String id, Node source, Node destination, Integer cost) {
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.cost = cost;
	}

	public String getId() {
		return id;
	}

	public Node getDestination() {
		return destination;
	}

	public Node getSource() {
		return source;
	}

	public Integer getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return "From: " + source + " To: " + destination + " cost: " + cost;
	}
}
