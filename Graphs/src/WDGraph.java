import java.util.ArrayList;
import java.util.List;

/**
 * T is required to have overriden the method equal
 * 
 * @author Darren J. Leh
 *
 * @param <T>
 */

//this class is a weighted and directed graph
public class WDGraph<T> implements GraphADT<T> {
	
	private static int CAPACITY = 2;
	private double[][] adjMatrix;
	private int numEdges;
	private int numVertices;
	private T[] vertices;
	private final double INFINITY = Double.POSITIVE_INFINITY;
	
	@SuppressWarnings("unchecked")
	public WDGraph(int capacity) {
		numVertices = 0;
		numEdges = 0;
		adjMatrix = new double[capacity][capacity];
		vertices = (T[]) new Object[capacity];
		
		for (int i = 0; i < adjMatrix.length; i++) {
			for (int j = 0; j < adjMatrix[i].length; j++) {
				adjMatrix[i][j] = INFINITY;
			}
		}
	}
	
	public WDGraph() {
		this(CAPACITY);
	}
	
	
	@Override
	public int numVertices() {
		// TODO Auto-generated method stub
		return numVertices;
	}

	@Override
	public int numEdges() {
		// TODO Auto-generated method stub
		return numEdges;
	}

	@Override
	public int numComponents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addVertex(T vertex) {
		if(numVertices == CAPACITY) {
			addCapacity();
		}
		
		if (!existVertex(vertex)) {
			vertices[numVertices] = vertex;
			numVertices++;
		}
		
	}

	@Override
	public void removeVertex(T vertex) {
		
	}

	@Override
	public void addEdge(T fromVertex, T toVertex, double weight) {
		//both vertices must exist.
		//if they are, does the edge exist (then update it)
		//if not it becomes a brand new edge that increments numEdges
		if(this.existVertex(fromVertex) && this.existVertex(toVertex) && (fromVertex != toVertex) && (weight >= 0)) {
			if(!this.existEdge(fromVertex, toVertex)) {
				numEdges++;
			}
			
			adjMatrix[vertexIndex(fromVertex)][vertexIndex(toVertex)] = weight;
		}
	}

	@Override
	public void removeEdge(T fromVertex, T toVertex) {
		if(this.existVertex(fromVertex) && this.existVertex(toVertex) && this.existEdge(fromVertex, toVertex)) {
			numEdges--;
			adjMatrix[vertexIndex(fromVertex)][vertexIndex(toVertex)] = INFINITY;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void addCapacity() {
		int newCapacity = 2 * CAPACITY;
		double[][] newAdjMatrix = new double[newCapacity][newCapacity];
		
		T[] newVertices = (T[]) new Object[newCapacity];
		
		for (int i = 0; i < numVertices; i++) {
			newVertices[i] = vertices[i];
		}
		
		for (int i = 0; i < newAdjMatrix.length; i++) {
			for (int j = 0; j < newAdjMatrix[i].length; j++) {
				newAdjMatrix[i][j] = INFINITY;
			}
		}
		
		for (int i = 0; i < numVertices; i++) {
			for (int j = 0; j < numVertices; j++) {
				newAdjMatrix[i][j] = adjMatrix[i][j];
			}
		}
		
		CAPACITY = newCapacity;
		adjMatrix = newAdjMatrix;
		vertices = newVertices;
	}

	@Override
	public boolean existEdge(T fromVertex, T toVertex) {
		return (this.existVertex(fromVertex) && 
				this.existVertex(toVertex) &&
				adjMatrix[vertexIndex(fromVertex)][vertexIndex(toVertex)] != INFINITY);
	}
	
	/**
	 * The object type T should have overridden the .equals method
	 * @param vertex
	 * @return   only if the vertex already exists in graph
	 */
	private boolean existVertex(T vertex) {
		for (int i = 0; i < numVertices; i++) {
			if(vertex.equals(vertices[i])) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return numVertices == 0;
	}

	@Override
	public ArrayList<T> path(T fromVertex, T toVertex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<T> shortestPath(T fromVertex, T toVertex) {
		/*
		LQueue<T> queue = new LQueue<T>();
		int dist[] = new int[30];
		T prev[] = (T[]) new Object[30];
		int counter = 0;
		
		for(T v : vertices) {
			dist[counter] = Integer.MAX_VALUE;
			prev[counter] = null;
			queue.enqueue(v);
		}
		
		dist[fromVertex] = 0;
		
		*/
		return null;
	}

	/**
	 * use the BFS alg to determine if there is a path
	 */
	@Override
	public boolean existsPath(T fromVertex, T toVertex) {
		if(!this.existVertex(fromVertex) || !this.existVertex(toVertex)) {
			return false;
		}
		
		boolean visited[] = new boolean[numVertices];
		
		
		
		return false;
	}

	public String toString() {
		int GAP = 5;
		String result = "";
		
		if(isEmpty())
			return result;
		
		result += String.format("%7s", "");
		
		for (int i = 0; i < numVertices; i++) {
			result += String.format("%7s", vertices[i]);
		}
		
		result += "\n";
		
		for (int i = 0; i < numVertices; i++) {
			result += String.format("%7s", vertices[i]);
			
			for (int j = 0; j < numVertices; j++) {
				if(adjMatrix[i][j] == INFINITY) {
					result += String.format("%7s", '\u221E');
				} else {
					result += String.format("%7.0f", adjMatrix[i][j]);
				}
			}
			
			result += "\n";
		}
		
		return result;
	}
	
	private int vertexIndex(T vertex) {
		for(int i = 0; i < numVertices; i++) {
			if(vertices[i].equals(vertex))
				return i;
		}
		
		return -1;
	}

	
	@Override
	public List<T> neighbors(T vertex) {
		if(!existVertex(vertex))
			return null;
		
		int index = vertexIndex(vertex);
		ArrayList<T> list = new ArrayList<T>();
		
		for(int i = 0; i < numVertices; i++) {
			if(adjMatrix[index][i] != INFINITY) {
				list.add(vertices[i]);
			}
		}
		
		return list;
	}
	
	
}
