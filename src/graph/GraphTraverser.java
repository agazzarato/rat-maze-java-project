package graph;

import java.util.Stack;
import java.util.function.Predicate;

public class GraphTraverser<T> {
	public final Graph<T> graph;
	
	public GraphTraverser(Graph<T> graph) {
		this.graph = graph;
	}

	public Stack<T> dataPath(T from, T to) {
		Stack<Vertex<T>> vertexPath = path(from, to);
		Stack<T> dataPath = new Stack<T>();
		if(vertexPath != null) {
			for(Vertex<T> vertex : vertexPath) {
				dataPath.push(vertex.getData());
			}
			return dataPath;
		}
		return null;
	}
	public Stack<T> dataPath(T from, T to, Predicate<T> pred) {
		Stack<Vertex<T>> vertexPath = path(from, to, pred);
		Stack<T> dataPath = new Stack<T>();
		if(vertexPath != null) {
			for(Vertex<T> vertex : vertexPath) {
				dataPath.push(vertex.getData());
			}
			return dataPath;
		}
		return null;
	}
	public Stack<Vertex<T>> path(T from, T to) {
		Vertex<T> fromV = graph.getVertex(from);
		Vertex<T> toV = graph.getVertex(to);
		if(fromV != null && toV != null) {
			return path(fromV, toV, new Stack<Vertex<T>>());
		}
		return null;
	}
	public Stack<Vertex<T>> path(T from, T to, Predicate<T> pred) {
		Vertex<T> fromV = graph.getVertex(from);
		Vertex<T> toV = graph.getVertex(to);
		if(fromV != null && toV != null) {
			return path(fromV, toV, pred, new Stack<Vertex<T>>());
		}
		return null;
	}
	private Stack<Vertex<T>> path(Vertex<T> from, Vertex<T> to, Stack<Vertex<T>> path) {
		Stack<Vertex<T>> currentPath = new Stack<Vertex<T>>();
		for(Vertex<T> vertex : path) { currentPath.push(vertex); } //Copying the current path into a temporary stack.
		currentPath.push(from); //Add the current vertex to the temporary path.
		
		if(from.equals(to)) {
			return currentPath;
		}
		
		Stack<Vertex<T>> shortestPath = null;
		
		for(Vertex<T> connected : from.getEdgeVertices().values()) {
			if(!currentPath.contains(connected)) {
				Stack<Vertex<T>> connectedPath = path(connected, to, currentPath);
				if(connectedPath != null) {
					if(shortestPath == null) {
						shortestPath = connectedPath;
					} else if(shortestPath.size() >= connectedPath.size()) {
						shortestPath = connectedPath;
					}
				}
			}
		}
		
		if(shortestPath != null) {
			return shortestPath;
		}
		
		currentPath.pop();
		
		return null;
	}
	private Stack<Vertex<T>> path(Vertex<T> from, Vertex<T> to, Predicate<T> pred, Stack<Vertex<T>> path) {
		Stack<Vertex<T>> currentPath = new Stack<Vertex<T>>();
		for(Vertex<T> vertex : path) { currentPath.push(vertex); } //Copying the current path into a temporary stack.
		currentPath.push(from); //Add the current vertex to the temporary path.
		
		if(from.equals(to)) {
			return currentPath;
		}
		
		Stack<Vertex<T>> shortestPath = null;
		
		for(Vertex<T> connected : from.getEdgeVertices().values()) {
			if(!currentPath.contains(connected) && pred.test(connected.getData())) {
				Stack<Vertex<T>> connectedPath = path(connected, to, pred, currentPath);
				if(connectedPath != null) {
					if(shortestPath == null) {
						shortestPath = connectedPath;
					} else if(shortestPath.size() >= connectedPath.size()) {
						shortestPath = connectedPath;
					}
				}
			}
		}
		
		if(shortestPath != null) {
			return shortestPath;
		}
		
		currentPath.pop();
		
		return null;
	}

	public Stack<T> longDataPath(T from, T to) {
		Stack<Vertex<T>> vertexPath = longPath(from, to);
		Stack<T> dataPath = new Stack<T>();
		if(vertexPath != null) {
			for(Vertex<T> vertex : vertexPath) {
				dataPath.push(vertex.getData());
			}
			return dataPath;
		}
		return null;
	}
	public Stack<T> longDataPath(T from, T to, Predicate<T> pred) {
		Stack<Vertex<T>> vertexPath = longPath(from, to, pred);
		Stack<T> dataPath = new Stack<T>();
		if(vertexPath != null) {
			for(Vertex<T> vertex : vertexPath) {
				dataPath.push(vertex.getData());
			}
			return dataPath;
		}
		return null;
	}
	public Stack<Vertex<T>> longPath(T from, T to) {
		Vertex<T> fromV = graph.getVertex(from);
		Vertex<T> toV = graph.getVertex(to);
		if(fromV != null && toV != null) {
			return longPath(fromV, toV, new Stack<Vertex<T>>());
		}
		return null;
	}
	public Stack<Vertex<T>> longPath(T from, T to, Predicate<T> pred) {
		Vertex<T> fromV = graph.getVertex(from);
		Vertex<T> toV = graph.getVertex(to);
		if(fromV != null && toV != null) {
			return longPath(fromV, toV, pred, new Stack<Vertex<T>>());
		}
		return null;
	}
	private Stack<Vertex<T>> longPath(Vertex<T> from, Vertex<T> to, Stack<Vertex<T>> path) {
		Stack<Vertex<T>> currentPath = new Stack<Vertex<T>>();
		for(Vertex<T> vertex : path) { currentPath.push(vertex); } //Copying the current path into a temporary stack.
		currentPath.push(from); //Add the current vertex to the temporary path.
		
		if(from.equals(to)) {
			return currentPath;
		}
		
		Stack<Vertex<T>> longestPath = null;
		
		for(Vertex<T> connected : from.getEdgeVertices().values()) {
			if(!currentPath.contains(connected)) {
				Stack<Vertex<T>> connectedPath = longPath(connected, to, currentPath);
				if(connectedPath != null) {
					if(longestPath == null) {
						longestPath = connectedPath;
					} else if(longestPath.size() < connectedPath.size()) {
						longestPath = connectedPath;
					}
				}
			}
		}
		
		if(longestPath != null) {
			return longestPath;
		}
		
		currentPath.pop();
		
		return null;
	}
	private Stack<Vertex<T>> longPath(Vertex<T> from, Vertex<T> to, Predicate<T> pred, Stack<Vertex<T>> path) {
		Stack<Vertex<T>> currentPath = new Stack<Vertex<T>>();
		for(Vertex<T> vertex : path) { currentPath.push(vertex); } //Copying the current path into a temporary stack.
		currentPath.push(from); //Add the current vertex to the temporary path.
		
		if(from.equals(to)) {
			return currentPath;
		}
		
		Stack<Vertex<T>> longestPath = null;
		
		for(Vertex<T> connected : from.getEdgeVertices().values()) {
			if(!currentPath.contains(connected) && pred.test(connected.getData())) {
				Stack<Vertex<T>> connectedPath = longPath(connected, to, pred, currentPath);
				if(connectedPath != null) {
					if(longestPath == null) {
						longestPath = connectedPath;
					} else if(longestPath.size() < connectedPath.size()) {
						longestPath = connectedPath;
					}
				}
			}
		}
		
		if(longestPath != null) {
			return longestPath;
		}
		
		currentPath.pop();
		
		return null;
	}


}
