//keeps weight the same across all edges
public class DGraph<T> extends WDGraph<T> {
	public DGraph() {
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
