package br.com.tatica.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

// algoritmo de dijkstra, permite que sejam encontrados à partir de uma fonte (source), os menores caminhos
// a todos os demais nós pertencentes a um grafo.
public class ShortestPathFinder {

	private List<Edge> allEdges;

	// já foram avaliados e o menor caminho já foi encontrado
	private Set<Node> evaluatedNodes;

	// ainda devem ser avaliados e a distância da fonte até esses nós é infinita
	private Set<Node> unEvaluatedNodes;

	// conjunto dos nós pertencentes a um caminho, do destino (target) até a fonte
	private Map<Node, Node> predecessors;

	// distancias da fonte aos demais nós
	private Map<Node, Integer> distance;

	private List<Node> allNodes;

	private Node source;

	public ShortestPathFinder(Graph graph) {
		this.allNodes = new ArrayList<Node>(graph.getNodes());
		this.allEdges = new ArrayList<Edge>(graph.getEdges());
	}

	public void execute(Node source) {
		this.source = source;
		evaluatedNodes = new HashSet<Node>();
		unEvaluatedNodes = new HashSet<Node>();
		unEvaluatedNodes.addAll(this.allNodes);
		distance = new HashMap<Node, Integer>();
		predecessors = new HashMap<Node, Node>();

		// dist�ncia do mesmo n� � sempre 0
		distance.put(source, 0);
		unEvaluatedNodes.add(source);

		while (unEvaluatedNodes.size() > 0) {
			// verifica a distância do nó em questão a todos os nós ainda não
			// verificados
			Node node = getMinimumCostFromSource(unEvaluatedNodes);
			// um no adicionado aos já avaliados, teve sua  mínima distância
			// até a fonte encontrada
			addToEvaluated(node);
			findMinimalDistances(node);
		}
	}

	private void addToEvaluated(Node node) {
		evaluatedNodes.add(node);
		unEvaluatedNodes.remove(node);
	}

	// verifica a distância mínima de um nó com seus adjacentes
	private void findMinimalDistances(Node node) {
		List<Node> adjacentNodes = getNeighbors(node);

		for (Node target : adjacentNodes) {
			if (getShortestDistance(target).compareTo((getShortestDistance(node) + getDistance(node, target))) > 0) {
				distance.put(target, getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unEvaluatedNodes.add(target);
			}
		}
	}
	
	// retorna a distância entre dois nós
	private Integer getDistance(Node node, Node target) {
		for (Edge edge : allEdges) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getCost();
			}
		}

		return null;
	}

	// retorna os nós vizinhos, que são os avaliados em seguida
	private List<Node> getNeighbors(Node node) {
		List<Node> neighbors = new ArrayList<Node>();

		for (Edge edge : allEdges) {
			if (edge.getSource().equals(node) && !isEvaluated(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}

		return neighbors;
	}
	
	// verifica se a distância a um nó é menor que alguma das distâncias já cacluladas
	private Node getMinimumCostFromSource(Set<Node> nodes) {
		Node minimum = null;
		for (Node node : nodes) {
			if (minimum == null) {
				minimum = node;
			} else {
				if (getShortestDistance(node).compareTo(getShortestDistance(minimum)) < 0) {
					minimum = node;
				}
			}
		}
		return minimum;
	}
	
	private boolean isEvaluated(Node node) {
		return evaluatedNodes.contains(node);
	}
	
	// caso ainda não exista distância calculada para um nó, seta a distância como infinita
	private Integer getShortestDistance(Node destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}
	
	// retorna o caminho mínimo da fonte calculada até um dos nós do grafo
	public LinkedList<Node> getPath(Node target) {
		LinkedList<Node> path = new LinkedList<Node>();
		Node step = target;

		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}

		Collections.reverse(path);
		return path;
	}
	
	// forma de mostrar para o usuario o caminho encontrado de maneira formatada
	public void prettyPrintPath(Node target) {
		LinkedList<Node> path = getPath(target);

		System.out.println("Shortest path from " + this.source + " to " + target);

		Iterator<Node> iterator = path.iterator();

		while (iterator.hasNext()) {
			Node node = iterator.next();
			System.out.print(node);

			if (iterator.hasNext()) {
				System.out.print(" --> ");
			} else {
				System.out.println();
			}
		}
		System.out.println("================================================");
	}
}
