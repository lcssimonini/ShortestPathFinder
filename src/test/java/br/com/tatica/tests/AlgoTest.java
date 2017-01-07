package br.com.tatica.tests;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import br.com.tatica.entities.Edge;
import br.com.tatica.entities.Graph;
import br.com.tatica.entities.Node;
import br.com.tatica.entities.ShortestPathFinder;

public class AlgoTest {

	private static final int QTD_NODES = 4;
	private List<Node> nodes;
	private List<Edge> edges;
	
	private Node source;
	private Node target;

	@Test
	public void testDijkstra() {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		
		for (int i = 0; i < QTD_NODES; i++) {
			Node location = new Node("Node_" + i, i);
			nodes.add(location);
		}

		addNewEdge("Edge 0", 0, 1, 50);
		addNewEdge("Edge 1", 1, 2, 178);
		addNewEdge("Edge 2", 2, 3, 439);
		addNewEdge("Edge 3", 3, 0, 12);
		addNewEdge("Edge 4", 0, 2, 998);
		
		this.source = nodes.get(0);
		this.target = nodes.get(2);

		Graph graph = new Graph(nodes, edges);
		ShortestPathFinder dijkstra = new ShortestPathFinder(graph);
		dijkstra.execute(source);
		LinkedList<Node> path = dijkstra.getPath(target);

		assertNotNull(path);
		assertTrue(path.size() > 0);
		dijkstra.prettyPrintPath(target);
	}

	private void addNewEdge(String edgeId, int sourceId, int destinationId, int cost) {
		Edge edge = new Edge(edgeId, nodes.get(sourceId), nodes.get(destinationId), cost);
		edges.add(edge);
	}
}
