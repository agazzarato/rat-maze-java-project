package graph;

import java.util.HashMap;

public class Graph<E> {
	HashMap<E, Vertex<E>> vertexMap = new HashMap<>();
	
	public Graph() {}
	@SafeVarargs
	public Graph(E... elems) {
		this.add(elems);
	}
	
	//Mutators
	@SafeVarargs
	public final void add(E... elems) {
		for(E elem : elems) {
			if(!vertexMap.containsKey(elem)) {
				vertexMap.put(elem, new Vertex<E>(elem));
			}
		}
	}
	
	public void connect(E from, E to) {
		Vertex<E> fromV = vertexMap.get(from);
		Vertex<E> toV = vertexMap.get(to);
		if(fromV != null && toV != null) {
			new Edge<E>(fromV, toV);
		}
	}
	
	//Accessors
	Vertex<E> getVertex(E key) {
		return vertexMap.get(key);
	}
	HashMap<E, Vertex<E>> getVertexMap() {
		return this.vertexMap;
	}
	public String toString() {
		String s = "";
		for(E key : vertexMap.keySet()) {
			s += vertexMap.get(key).toString() + "\n";
		}
		return s;
	}
}
