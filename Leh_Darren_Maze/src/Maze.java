/*
 * Darren Leh
 * October 17th, 2019
 * 
 * Algorithm that generates a random maze, and finds the shortest path using breadth first search algorithm
 * This algorithm generates its own maze, start, finish, and backtracks to find and display its path on the console
 * If there is no distance, there is no path, and that is displayed.
 */
import java.awt.Point;

class CPoint extends Point {
	public CPoint(int i, int j) {
		this.x = i;
		this.y = j;
	}

	public CPoint() {
		// TODO Auto-generated constructor stub
	}

	public CPoint(int i, int j, int distance, CPoint parent) {
		this.x = i;
		this.y = j;
		this.distance = distance;
		this.parent = parent;
	}

	CPoint parent; //used to track its own parent Point
	int distance; //used to track distance
}

public class Maze {
		//declaring the size of maze
		private static final int L = 10;
		private static final int W = 10;

		//4 movements to different points
		private static final int row[] = { -1, 0, 0, 1 };
		private static final int col[] = { 0, -1, 1, 0 };
		
		//declare main points
		private static CPoint sourcePoint = new CPoint();
		private static CPoint destination = new CPoint();
		
		//declare visited array
		private static boolean[][] visited = new boolean[L][W];

		//Method to check if it is possible to go to position (row, col)
		//from current position. The function returns false if (row, col)
		//is not a valid position or has value 0 or it is already visited
		private static boolean isValid(int maze[][], boolean visited[][],
														int row, int col)
		{
			return (row >= 0) && (row < L) && (col >= 0) && (col < W)
						   && maze[row][col] != 1 && !visited[row][col];
		}
		
		//Method to check if destination and source points are able to be 
		//placed in the given location
		private static boolean isValid(int maze[][], int x, int y) {
			return maze[x][y] != 1;
		}

		// Find Shortest Possible Route in maze from source
		// point (i, j) to destination point (x, y)
		private static void BFS(int maze[][], int i, int j, int x, int y)
		{

			//create LQueue
			LQueue<CPoint> queue = new LQueue<CPoint>();

			//show we visited this point, queue for movement
			visited[i][j] = true;
			queue.enqueue(new CPoint(i, j));

			// stores length of longest path from source to destination
			int min_dist = Integer.MAX_VALUE;
			
			CPoint point = null; //declares point

			//while loop to keep going until empty
			while (!queue.isEmpty())
			{
				//dequeue current point
				point = queue.dequeue();

				// (i, j) represents current point
				i = point.x;
				j = point.y;
				int distance = point.distance;

				//if destination is found stop the while loop, keep the distance
				if (i == x && j == y)
				{
					min_dist = distance;
					break;
				}

				// check for all 4 possible movements from current point
				// and enqueue each movement that is valid
				for (int k = 0; k < 4; k++)
				{
					// check if it is possible to go to position
					// (i + row[k], j + col[k]) from current position
					if (isValid(maze, visited, i + row[k], j + col[k]))
					{
						// mark next point as visited and enqueue it
						visited[i + row[k]][j + col[k]] = true;
						queue.enqueue(new CPoint(i + row[k], j + col[k], distance + 1, point));
						// the line above enqueues a CPoint and keeps it parent point
					}
				}
			}
			
			//if the distance isnt equal to the value, it means that a distance was kept
			if (min_dist != Integer.MAX_VALUE) {
				System.out.println("The shortest path from source to destination "
								+ "has length " + min_dist);
				System.out.println();
				
				printMazePath(point, maze);
			}
			else { //but if a distance wasnt kept, it means that it cannot be reached
				System.out.println("Destination can't be reached from source");
			}
			
		}
	private static void printMazePath(CPoint point, int[][] maze) {
		//while the point isnt null, set the maze's point value to 2
		//and change the point to be the parent of the current point
		while (point != null) {
			if (maze[point.x][point.y] != 3 && maze[point.x][point.y] != 4) {
				maze[point.x][point.y] = 2;
			}
			
			point = point.parent;
        }

		//then print out the completed maze
		System.out.println("Completed Maze");
		printMaze(maze);
	}

	public static void main(String[] args) {
		int[][] maze = new int[L][W];
		maze = generateMaze(); //generates the maze with a density of 0.2

		maze = generateSourcePoint(maze); //generates the source point
		System.out.println("Your source point: (" + sourcePoint.x + "," + sourcePoint.y + ") labeled '3'");
		
		maze = generateDestination(maze); //generates the destination point
		System.out.println("Your destination:  (" + destination.x + "," + destination.y + ") labeled '4'");
		System.out.println();
		
		System.out.println("This is your maze");
		printMaze(maze); //print maze
		System.out.println();
		
		BFS(maze, sourcePoint.x, sourcePoint.y, destination.x, destination.y); //initiates breadth first search
		System.out.println();
		
		//printVisited(visited);
	}
	
	private static int[][] generateSourcePoint(int maze[][]) {
		boolean result = false;
		
		//while the result is false, keep finding a new value for the source point until its true
		while (result == false) {
			sourcePoint.x = (int)(Math.random() * ((9 - 0) + 1) + 0);
			sourcePoint.y = (int)(Math.random() * ((9 - 0) + 1) + 0);
			result = isValid(maze, sourcePoint.x, sourcePoint.y);
		}
		
		maze[sourcePoint.x][sourcePoint.y] = 3;
		
		return maze;
		
	}
	
	private static int[][] generateDestination(int maze[][]) {
		boolean result = false;
		
		//while the result is false, keep finding a new value for the
		//destination point until its true
		while(result == false) {
			destination.x = (int)(Math.random() * ((9 - 0) + 1) + 0);
			destination.y = (int)(Math.random() * ((9 - 0) + 1) + 0);
			result = isValid(maze, destination.x, destination.y);
		}
		
		maze[destination.x][destination.y] = 4;
		
		return maze;
	}

	public static int[][] generateMaze() {
		
		int[][] maze = new int[L][W];
		
		//generates the maze using a density of 0.2
		for(int i = 0; i < L; i++) {
			for(int j = 0; j < W; j++) {
				if (Math.random() < 0.2) {
					maze[i][j] = 1;
				} else {
					maze[i][j] = 0;
				}
			}
		}
		
		return maze;
	}
	
	public static void printMaze(int[][] maze) {
		//prints out all points on the maze
		for(int i = 0; i < L; i++) {
			for(int j = 0; j < W; j++) {
				System.out.print(" " + maze[i][j]);
			}
			
			System.out.println();
		}
	}
	
	/*
	public static void printVisited(boolean [][] visited) {
		for(int i = 0; i < L; i++) {
			for(int j = 0; j < W; j++) {
				if(visited[i][j] == true)
					System.out.print(" " + visited[i][j] + " ");
				else
					System.out.print(" " + visited[i][j]);
			}
			
			System.out.println();
		}
	}
	*/

}
