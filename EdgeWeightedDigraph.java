import java.util.LinkedHashSet;
import java.util.LinkedList;

public class EdgeWeightedDigraph {
	private final int V;
	public Bag<DirectedEdge>[] adj;

	@SuppressWarnings("unchecked")
	public EdgeWeightedDigraph(int V) {
		this.V = V;
		adj = (Bag<DirectedEdge>[]) new Bag[V];
		for (int v = 0; v < V; v++)
			adj[v] = new Bag<DirectedEdge>();
	}

	public void addEdge(DirectedEdge e) {
		int temp = 0;
		for (int i = 0; i < adj.length; i++) {
			if (e.from().getName().equals(adj[i].getName())) {

				temp = i;
			}
		}

		adj[temp].add(e);

	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public LinkedList<Integer> adjacentNodes(Integer last) {
		LinkedHashSet<Integer> adjacent = new LinkedHashSet<Integer>();

		for (DirectedEdge e : adj[last]) {

			adjacent.add(e.to().getNo());
		}

		if (adjacent == null) {
			return new LinkedList();
		}
		return new LinkedList<Integer>(adjacent);
	}

	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}

	public String toString() {
		StringBuilder s = new StringBuilder();

		for (int v = 0; v < V; v++) {
			s.append(adj[v].getName() + v + ": ");
			for (DirectedEdge e : adj[v]) {
				s.append("(" + e.to().getName() + " " + e.to().getNo() + ")" + "  ");
			}
			s.append("\n");
		}
		return s.toString();
	}
}