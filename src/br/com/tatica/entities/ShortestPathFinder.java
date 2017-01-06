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

	private List<Node> nodes;
	private List<Edge> edges;

	// já foram avaliados e o menor caminho já foi encontrado
	private Set<Node> evaluatedNodes;

	// ainda devem ser avaliados e a distância da fonte até esses nós é infinita
	private Set<Node> unEvaluatedNodes;

	private Map<Node, Node> predecessors;
	private Map<Node, Integer> distance;

	public ShortestPathFinder(Graph graph) {
		this.nodes = new ArrayList<Node>(graph.getNodes());
		this.edges = new ArrayList<Edge>(graph.getEdges());
	}

	public void execute(Node source) {
		evaluatedNodes = new HashSet<Node>();
		unEvaluatedNodes = new HashSet<Node>();
		distance = new HashMap<Node, Integer>();
		predecessors = new HashMap<Node, Node>();
		
		// distância do mesmo nó é sempre 0
		distance.put(source, 0);
		unEvaluatedNodes.add(source);
		
		while (unEvaluatedNodes.size() > 0) {
			// verifica a distância do nó em questão a todos os nós ainda não verificados
			Node node = getMinimumCostFromSource(unEvaluatedNodes);
			evaluatedNodes.add(node);
			unEvaluatedNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Node node) {
		List<Node> adjacentNodes = getNeighbors(node);
		for (Node target : adjacentNodes) {
			if (getShortestDistance(target).compareTo((getShortestDistance(node) + getDistance(node, target))) > 1) {
				distance.put(target, getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unEvaluatedNodes.add(target);
			}
		}

	}

	private Integer getDistance(Node node, Node target) {
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getCost();
			}
		}
		
		return null;
	}

	private List<Node> getNeighbors(Node node) {
		List<Node> neighbors = new ArrayList<Node>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	private Node getMinimumCostFromSource(Set<Node> vertexes) {
		Node minimum = null;
		for (Node vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex).compareTo(getShortestDistance(minimum)) < 1) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Node vertex) {
		return evaluatedNodes.contains(vertex);
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
