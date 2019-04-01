package graph;

class Edge<T> {
	private final Vertex<T> to;
	
	Edge(Vertex<T> from, Vertex<T> to) {
		this.to = to;
		from.addEdge(this);
	}
	
	Vertex<T> to() {
		return this.to;
	}
}
