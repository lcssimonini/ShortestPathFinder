package br.com.tatica.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ShortestPathFinder {

	private List<Edge> allEdges;

	// já foram avaliados e o menor caminho já foi encontrado
	private Set<Node> evaluatedNodes;

	// ainda devem ser avaliados e a distância da fonte até esses nós é infinita
	private Set<Node> unEvaluatedNodes;

	private Map<Node, Node> predecessors;
	
	//distancias da fonte aos demais nós
	private Map<Node, Integer> distance;

	private List<Node> allNodes;

	public ShortestPathFinder(Graph graph) {
		this.allNodes = new ArrayList<Node>(graph.getNodes());
		this.allEdges = new ArrayList<Edge>(graph.getEdges());
	}

	public void execute(Node source) {
		evaluatedNodes = new HashSet<Node>();
		unEvaluatedNodes = new HashSet<Node>();
		unEvaluatedNodes.addAll(this.allNodes);
		distance = new HashMap<Node, Integer>();
		predecessors = new HashMap<Node, Node>();

		// distância do mesmo nó é sempre 0
		distance.put(source, 0);
		unEvaluatedNodes.add(source);

		while (unEvaluatedNodes.size() > 0) {
			// verifica a distância do nó em questão a todos os nós ainda não
			// verificados
			Node node = getMinimumCostFromSource(unEvaluatedNodes);
			// um no adicionado aos já avaliados, já teve sua mínima distância
			// até a fonte encontrada
			addToEvaluated(node);
			findMinimalDistances(node);
		}
	}

	private void addToEvaluated(Node node) {
		evaluatedNodes.add(node);
		unEvaluatedNodes.remove(node);
	}

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

	private Integer getDistance(Node node, Node target) {
		for (Edge edge : allEdges) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getCost();
			}
		}

		return null;
	}

	private List<Node> getNeighbors(Node node) {
		List<Node> neighbors = new ArrayList<Node>();

		for (Edge edge : allEdges) {
			if (edge.getSource().equals(node) && !isEvaluated(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}

		return neighbors;
	}

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

	private Integer getShortestDistance(Node destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

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

}
