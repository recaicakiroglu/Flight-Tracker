import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class DFS {
	public static Map<Integer, ArrayList<Integer>> hashmap = new HashMap<Integer, ArrayList<Integer>>();
	static int temp = 0;

	public DFS(EdgeWeightedDigraph graph, int src, int destination) {
		LinkedList<Integer> visited = new LinkedList<Integer>();
		visited.add(src);
		depthFirst(graph, visited, destination);
	}

	public void depthFirst(EdgeWeightedDigraph graph, LinkedList<Integer> visited, int destination) {
		LinkedList<Integer> nodes = graph.adjacentNodes(visited.getLast());
		for (Integer node : nodes) {
			if (visited.contains(node)) {
				continue;
			}
			if (node.equals(destination)) {
				visited.add(node);

				printPath(visited);
				visited.removeLast();
				break;
			}
		}
		for (Integer node : nodes) {

			if (visited.contains(node) || node.equals(destination)) {

				continue;
			}

			visited.addLast(node);
			depthFirst(graph, visited, destination);
			visited.removeLast();
		}
	}

	private static void printPath(LinkedList<Integer> visited) {
		ArrayList<Integer> patharray = new ArrayList<Integer>();
		for (Integer node : visited) {

			patharray.add(node);

		}
		hashmap.put(temp++, patharray);

	}
}
