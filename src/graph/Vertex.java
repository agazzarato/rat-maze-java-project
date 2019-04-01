package graph;

import java.util.HashMap;

class Vertex<T> {
	private HashMap<T, Edge<T>> edges = new HashMap<>();
	private final T data;
	
	Vertex(T data) {
		this.data = data;
	}
	
	//Mutators
	void addEdge(Edge<T> edge) {
		edges.put(edge.to().data, edge);
	}
	
	//Accessors
	public T getData() {
		return this.data;
	}
	
	HashMap<T, Vertex<T>> getEdgeVertices() {
		HashMap<T, Vertex<T>> edgeVertices = new HashMap<>();
		for(T key : this.edges.keySet()) {
			edgeVertices.put(key, this.edges.get(key).to());
		}
		return edgeVertices;
	}
	
	public boolean equals(Vertex<T> vertex) {
		return this.data == vertex.data;
	}
	public String toString() {
		String s = data + " [ ";
		for(T other : edges.keySet()) {
			s += other + " ";
		}
		return s + "]";
	}
}
