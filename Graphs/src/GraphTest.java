import java.util.List;

public class GraphTest {

	public static void main(String[] args) {
		WDGraph<String> airport;
		
		airport = new WDGraph<String>();
		List<String> list;
		
		airport.addVertex("LAX");
		airport.addVertex("SFO");
		airport.addVertex("EWR");
		airport.addVertex("AID");
		airport.addEdge("EWR", "SFO", 2500);
		airport.addEdge("SFO", "LAX", 500);
		airport.addEdge("SFO", "AID",  2400);
		
		
		
		//System.out.println(airport);
		
		//airport.removeEdge("EWR", "SFO");
		
		System.out.println(airport);
		//System.out.println(airport.numEdges());
		
		list = airport.neighbors("SFO");
		
		System.out.println(list);
	}

}
