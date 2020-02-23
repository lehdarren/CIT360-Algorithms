//makes graphs bidirectional
public class WGraph<T> extends WDGraph<T> {
	public WGraph() {
		super();
	}
	
	@Override
	public int numEdges() {
		// TODO Auto-generated method stub
		return super.numEdges() / 2;
	}
	
	@Override
	public void addEdge(T fromVertex, T toVertex, double weight) {
		super.addEdge(fromVertex, toVertex, weight);
		super.addEdge(toVertex, fromVertex, weight);
	}
	
	@Override
	public void removeEdge(T fromVertex, T toVertex) {
		super.removeEdge(fromVertex, toVertex);
		super.removeEdge(toVertex, fromVertex);
	}
}
