import java.util.ArrayList;
import java.util.List;

public interface GraphADT<T> {
	public int numVertices();
	public int numEdges();
	public int numComponents();
	public void addVertex(T vertex);
	public void removeVertex(T vertex);
	public void addEdge(T fromVertex, T toVertex, double weight);
	public void removeEdge(T fromVertex, T toVertex);
	public boolean existEdge(T fromVertex, T toVertex);
	public boolean isEmpty();
	public ArrayList<T> path(T fromVertex, T toVertex);
	public Pair shortestPath(T fromVertex, T toVertex);
	public boolean existsPath(T fromVertex, T toVertex);
	public List<T> neighbors(T vertex);

	//may be others to be added later
}
