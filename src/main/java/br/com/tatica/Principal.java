package br.com.tatica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.com.tatica.entities.Edge;
import br.com.tatica.entities.Graph;
import br.com.tatica.entities.Node;
import br.com.tatica.entities.ShortestPathFinder;

public class Principal {
	
	static Scanner scanner = new Scanner(System.in);
	static int qtdNodes = 0;
	static int qtdEdges = 0;
	static Map<Integer, Node> nodesMap = new HashMap<Integer, Node>();
	static List<Edge> edgesList = new ArrayList<Edge>();
	
	public static void main(String[] args) {
		
//		System.out.println("Digite o número de nós que deseja cadastrar: ");
//		qtdNodes = scanner.nextInt();
//		
//		for (int i = 0; i < qtdNodes; i++) {
//			Node node = new Node(i);
//			nodesMap.put(i, node);
//			System.out.println("no inserido " + node);
//		}
//		
//		System.out.println("Digite o número de arestas que deseja cadastrar: ");
//		qtdEdges = scanner.nextInt();
//		
//		for (int i = 0; i < qtdEdges; i++) {
//			addEdge(i);
//		}
//		
//		Graph graph = new Graph(nodesMap.values(), edgesList);
//		
//		System.out.println("Qual será o nó inicial da busca?");
//		int sourceNodeID = scanner.nextInt();
//		
//		System.out.println("Qual será o nó final da busca?");
//		int destinationNodeID = scanner.nextInt();
//		
//		printShortestPath(graph, sourceNodeID, destinationNodeID);
//	}
//
//	private static void printShortestPath(Graph graph, int sourceNodeID, int destinationNodeID) {
//		ShortestPathFinder pathFinder = new ShortestPathFinder(graph);
//		
//		pathFinder.execute(new Node(sourceNodeID));
//		LinkedList<Node> nodesPathList = pathFinder.getPath(new Node(destinationNodeID));
//		
//		System.out.println(" Shortest path from " + sourceNodeID + " to " + destinationNodeID);
//		for (Node node : nodesPathList) {
//			System.out.println(node + " --> ");
//		}
//		System.out.println("================================================");
//	}
//	
//	static void addEdge(int id) {
//		int source;
//		int destination;
//		int cost;
//		System.out.println("Digite o nó de origem: ");
//		source = scanner.nextInt();
//		System.out.println("Digite o nó de destino: ");
//		destination = scanner.nextInt();
//		System.out.println("Digite o peso: ");
//		cost = scanner.nextInt();
//	
//		Node sourceNode = nodesMap.get(source);
//		Node destinationNode = nodesMap.get(destination);
//		
//		Edge edge = new Edge(id, sourceNode, destinationNode, cost);
//		edgesList.add(edge);
//		System.out.println("aresta inserida: "+ edge);
	}
}

















