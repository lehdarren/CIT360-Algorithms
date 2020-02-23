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
		int index = vertexIndex(vertex);
		if (index == -1)
			return;
		
		
		int numEdgesRemoved = 0;
		for (int i = 0; i < numVertices; i++) {
			if(adjMatrix[index][i] != INFINITY)
				numEdgesRemoved++;
			if(adjMatrix[i][index] != INFINITY)
				numEdgesRemoved++;
		}

		numEdges -= numEdgesRemoved;
		
		//shift the columns left
		for (int col = index; col < numVertices -1; col++) {
			for (int row = 0; row < numVertices; row++) {
				adjMatrix[row][col] = adjMatrix[row][col+1]; 
			}
		}
		
		//shift the rows up
		for (int row = index; row < numVertices -1; row++) {
			for (int col = 0; col < numVertices; col++) {
				adjMatrix[row][col] = adjMatrix[row+1][col]; 
			}
		}
		
		//set last row and last columns to infinity
		for (int i = 0; i < numVertices; i++) {
			adjMatrix[numVertices-1][i] = INFINITY;
			adjMatrix[i][numVertices-1] = INFINITY;
		}
		//move vertices to the left
		for (int i = index; i < numVertices-1; i++) {
			vertices[i] = vertices[i+1];
		}		
		vertices[numVertices-1] = null;  //nullify the last value
		numVertices--;
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
	
	private int getMinimumVertex(boolean[] mst, int[] key) {
		int minKey = Integer.MAX_VALUE;
		int vertex = -1;
		
		for (int i = 0; i < this.numVertices; i++) {
			if(mst[i] == false && minKey > key[i]) { //finds if the vertex was visited and if the distance would be shorter
				minKey = key[i]; 
				vertex = i;
			}
		}
		return vertex;
	}

	@Override
	public Pair shortestPath(T fromVertex, T toVertex) {
		boolean[] visited = new boolean[this.numVertices()]; //creates visited based on amount of vertices
		int[] prev = new int[this.numVertices()]; //creates trail based on amount of vertices
		int[] dist = new int[this.numVertices()]; //creates distance based on amount of vertices
		
		int INFINITY = Integer.MAX_VALUE;
		
		for (int i = 0; i < this.numVertices(); i++) {
			dist[i] = INFINITY; //fills array with all infinity values
		}
		
		dist[(int) fromVertex] = 0; //first number starts at 0 distance
		prev[(int) fromVertex] = -1; //first number starts at -1 to provide starting point for trail
		
		boolean twoWayStreet = false; //finds out if vertice is a two way street
		int newKey = 0;
		
		while(visited[(int) toVertex] == false) {
			int minVertex = getMinimumVertex(visited, dist); //pass distance and visited to minimum vertex to find smallest distance vertex from starting point
			visited[minVertex] = true; //set vertex in visited to true
			
			for(int vertex_V = 0; vertex_V < this.numVertices; vertex_V++) { //go through each vertex
				if(adjMatrix[minVertex][vertex_V] < INFINITY || adjMatrix[vertex_V][minVertex] == 2) { //if it isn't infinity OR if it is a two way street (can go both directions)
					if(adjMatrix[vertex_V][minVertex] == 2) { //if it was stepped in because of two way street
						twoWayStreet = true;                  //recognize that
					}
					
					if(visited[vertex_V] == false) { //if it isn't visited
						if (twoWayStreet == false) { //and its not a two way street
							newKey = (int) (adjMatrix[minVertex][vertex_V] + dist[minVertex]); //create the key based on first argument in first if statement
						} else if (twoWayStreet == true) { //and it is a two way street
							newKey = (int) (adjMatrix[vertex_V][minVertex] + dist[minVertex]); //create the key based on second argument in first if statement
						}
						
						if(newKey < dist[vertex_V]) { //if this distance is less than the distance of vertex_V
							dist[vertex_V] = newKey;   //set distance at vertex_V equal to new key
							prev[vertex_V] = minVertex; //and set the previous number equal to minVertex to keep track of location
						}
					}
					
					twoWayStreet = false; //reset the two way street boolean
				}
			}
		}
		
		
		Pair distAndPrev = new Pair(dist, prev); //create the pair
		
		return distAndPrev; //and pass out the trail with the distance
	}

	/**
	 * use the BFS alg to determine if there is a path
	 */
	@Override
	public boolean existsPath(T fromVertex, T toVertex) {
		if(!existVertex(fromVertex) || !existVertex(toVertex))
			return false;
		
		boolean[] visited = new boolean[numVertices];
		LQueue<T> que = new LQueue<T>();
		T tempVertex;
		List<T> ngbrs;
		
		que.enqueue(fromVertex);
		boolean done = false;
		visited[vertexIndex(fromVertex)] = true;
		
		while(!que.isEmpty() && !done) {
		   if(que.peek().equals(toVertex)) {
			   done = true;
			   break;
		   }
		   tempVertex = que.dequeue();
		   ngbrs = neighbors(tempVertex);
		   for(T ver : ngbrs) {
			   if(!visited[vertexIndex(ver)]) {
				   visited[vertexIndex(ver)] = true;
				   que.enqueue(ver);
			   }
		   }
		   
		}
		
		return done;
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
