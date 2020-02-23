
public class Graph<T> extends WGraph<T> {
	public Graph() {
		super();
	}
	
	@Override
	public void addEdge(T fromVertex, T toVertex, double weight) {
		//should throw an exception. It prevents users from calling it (Golshan didnt do this)
		super.addEdge(fromVertex, toVertex, 1);
	}
	
	public void addEdge(T fromVertex, T toVertex) {
		super.addEdge(fromVertex, toVertex, 1);
	}
}
