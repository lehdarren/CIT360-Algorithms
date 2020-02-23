import java.io.*;
import java.util.Scanner;
public class GraphAssignment {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("map.dat"); //file to be scanned
		File directions = new File("directions.dat");
		Scanner scanner = new Scanner(file); //scanner for the file
		PrintWriter pw = new PrintWriter(directions);
		WDGraph<Integer> graph = new WDGraph<Integer>(); //graph of vertex integers
		GeoLocation[] geoLocationArray = null; //geolocation array so geolocation latitudes, longitudes, heights, and location names can be kept.

		String iterations; //consistenly updating in case the number of vertices and edges or paths were to change
		int convIterations; //same as above.

		boolean didVertices = false; //booleans created for indicating if edges, vertices, and path's were finished.
		boolean didEdges = false;
		boolean foundPath = false;

		for(int i = 0; i < 3; i++) {
			iterations = scanner.nextLine(); //scans number of iterations
			convIterations = Integer.parseInt(iterations); //converts iterations to integer
			
			if (didVertices == false) { //tests if vertices were done yet
				String split; //line to be split when scanned
				String[] array = new String[5]; //array to be filled when String is split
				geoLocationArray = new GeoLocation[convIterations]; //amount of vertices = amount of geolocations
				int counter = 0;
				
				for (int j = 0; j < convIterations; j++) {
					split = scanner.nextLine();
					array = split.split(","); //splits the scanned line into an array
					
					GeoLocation geoLocation = new GeoLocation(Integer.parseInt(array[0]), Double.parseDouble(array[2]), 
															Double.parseDouble(array[1]), Double.parseDouble(array[3]), array[4]); //puts into geolocation object in order
					
					geoLocationArray[counter] = geoLocation;
					
					graph.addVertex(geoLocation.vertexNum); //adds geolocation to graph as vertex
					System.out.println("Adding Vertex: " + array[4]); //confirms addition
					counter++;
				}
				
				didVertices = true;
			} else if (didEdges == false) { //tests if edges were done yet
				String split;
				String[] array = new String[3];
				
				for (int j = 0; j < convIterations; j++) {
					split = scanner.nextLine();
					array = split.split(" ");
					
					graph.addEdge(Integer.parseInt(array[0]), Integer.parseInt(array[1]), Double.parseDouble(array[2]));
					System.out.println("Adding Edge from " + Integer.parseInt(array[0]) + " to " + 
					Integer.parseInt(array[1]) + " with weight of " + Double.parseDouble(array[2])); //transmits to console if completed
				}
				
				didEdges = true; //confirms edges are finished
			} else if (foundPath == false) { //indicates completion of path finding
				String split;
				String[] array = new String[2];
				
				for (int j = 0; j < convIterations; j++) {
					split = scanner.nextLine();
					
					array = split.split(" ");
					
					int[] prev;
					
					Pair distAndPrev; //created to provide the pair of dist and prev arrays
									  //EDIT: dist was deleted, waste of RAM and was unused.
					
					distAndPrev = graph.shortestPath(Integer.parseInt(array[0]), Integer.parseInt(array[1])); 

					prev = distAndPrev.getTrailArray(); //prev is used to find the path and determine directions
					
					int reverseSearch = geoLocationArray[Integer.parseInt(array[0])].vertexNum; //starting location
					int currentLocation = geoLocationArray[Integer.parseInt(array[1])].vertexNum; //ending location
					int[] steps = new int [prev.length]; //provides the steps it occurs
					int median;
					int counter = 0;
					
					steps[counter] = currentLocation;
					median = prev[currentLocation];
					counter++;
					
					while (reverseSearch != median) { //provides steps with data from prev array
						steps[counter] = median;
						median = prev[median];
						counter++;
					}
					
					steps[counter++] = median; //final data is put in
					
					System.out.println(); //spaces out content
					
					System.out.print("Shortest Path from " + reverseSearch + " to " + currentLocation + ": ");
					pw.write("Shortest Path from " + reverseSearch + " to " + currentLocation + ": ");
					pw.flush();
					
					double totalDistance = 0; //initializes data 
					String[] completeDirections = new String[steps.length]; //creates array same size as steps
					counter = 0; //counter is reset, waste of RAM to initialize another variable
					
					for(int k = steps.length - 1; k >= 0; k--) { //counts backwards to find the steps 
						if (steps[k] == currentLocation) {
							System.out.print(steps[k]);
							pw.write(steps[k] + ", "); //print to directions.dat
							
							completeDirections[counter] = geoLocationArray[steps[k]].locationName; //finds location name
							
							totalDistance += geoLocationArray[steps[k]].distanceInMilesBetweenEarthCoordinates(
									geoLocationArray[steps[k + 1]].latitude, geoLocationArray[steps[k + 1]].longitude);
							//adds to total distance the amount of miles
							
							counter++; //increment
							
						} else if (steps[k] != 0) {
								System.out.print(steps[k] + ", ");
								pw.write(steps[k] + ", "); //print to directions.dat
								
								completeDirections[counter] = geoLocationArray[steps[k]].locationName + " to "; //same as above
								
								if (steps[k+1] != 0) {
									totalDistance += geoLocationArray[steps[k]].distanceInMilesBetweenEarthCoordinates(
										geoLocationArray[steps[k + 1]].latitude, geoLocationArray[steps[k + 1]].longitude);
								}
								//same as above
								
								counter++;
						}
					}
					
					System.out.println();
					pw.write("\n");
					pw.flush();
					
					for(int l = 0; l < completeDirections.length; l++) {
						if (completeDirections[l] != "" && completeDirections[l] != null) {
							System.out.print(completeDirections[l]); //prints out complete directions without null values
																	 //if there are any
							pw.write(completeDirections[l]); //print directions to directions.dat
							pw.flush();
						}
					}
					
					System.out.println(); //spacing
					pw.write("\n"); //spacing for directions.dat
					pw.flush();
					
					String result = String.format("%7.2f", totalDistance); //prints out KM rounded up or down to the 2 dec
					
					System.out.println("Total Distance to get there: " + result + " Miles"); //print result
					pw.write("Total Distance to get there: " + result + " Miles \n\n"); //print miles to directions.dat
					pw.flush();
					
				}
				
				foundPath = true;
				scanner.close(); //close scanner
				pw.close();
			}
		}
	}
}