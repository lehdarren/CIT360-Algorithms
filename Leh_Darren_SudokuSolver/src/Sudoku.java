/**
 * Created and modified by Darren Leh
 * Date completed: 9/29/2019
 */
import java.io.File;
import java.util.Stack;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Sudoku {
	public static int SIZE; //size of the grid
	public static int BOXSIZE; //size of each square in sudoku grid
	private static int [][] board; //establishing the grid
	static Stack<int [][]> boardStack = new Stack<int [][]>(); //instantiates the stack
	
	public static void main(String[] args) {
		Sudoku sudoku = new Sudoku(); //creates Sudoku object
		
		System.out.println("This is the programs grid to solve: ");
		displayBoard(); //displays the board as barebones
		
		if(sudoku.solve()) { //starts recursive resolution
			System.out.println("Your board has been solved"); 
			displayBoard();
		} else {
			System.out.println("Your board is unsolvable"); //returns if the board is unsolvable
			displayBoard();
		}
		
	}
	
	public Sudoku() {
		createGrid(); //calls createGrid() method
	}
	
	/**
	 * checks if the number is in row
	 * @param row
	 * @param number
	 * @return
	 */
	public boolean inRow(int row, int number) {
		for(int i = 0; i < SIZE; i++) {
			if (board[row][i] == number) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * checks to see if number is in column
	 * @param col
	 * @param number
	 * @return
	 */
	public boolean inColumn(int col, int number) {
		for(int i = 0; i < SIZE; i++) {
			if (board[i][col] == number) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * checks to see if the number is in square
	 * @param row
	 * @param col
	 * @param number
	 * @return
	 */
	public boolean inSquare(int row, int col, int number) {
		int r = row - row % BOXSIZE;
		int c = col - col % BOXSIZE;
		
		for(int i = r; i < r + BOXSIZE; i++) {
			for(int j = c; j < c + BOXSIZE; j++) {
				if (board[i][j] == number) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	/**
	 * as long as all of these return true, it will return that the number can be used
	 * if not, the number cannot be used and must restart the process
	 * @param row
	 * @param col
	 * @param number
	 * @return
	 */
	public boolean numberCheck(int row, int col, int number) {
		return !inRow(row, number) && !inColumn(col, number) && !inSquare(row, col, number);
	}
	
	/**
	 * This method recursively finds the solution to each spot on the board
	 * If the spot equals zero in current board slot, check if the number in current loop can be used
	 * If not, continue to find a number that can
	 * when number can be used, push that current board onto the stack to be used, and recursively call
	 * if not, pop that board, and carry on my wayward son.
	 * @return
	 */
	public boolean solve() {
		for (int row = 0; row < SIZE; row++){
			for (int col = 0; col < SIZE; col++) {
				
				board = boardStack.peek(); //peeks at board on top, gets values
				
				if (board[row][col] == 0) { //if the board is zero, find number to fill
					for (int number = 1; number <= SIZE; number++) {
						if (numberCheck(row, col, number)) { //checks if the number in loop is inRow, inColumn, or inSquare
							board[row][col] = number; //if all are true, put number in board
							
							boardStack.push(board); //push board onto stack
							
							if(solve()) { //recursively call to repeat
								return true;
							} else {
								boardStack.pop(); //pops previous if the top number doesnt work
							}
						}
					}
					
					return false;
				}
			}
		}
		return true;
	}
	
	public static void createGrid() {
		//pulled from https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
		//edited by Darren Leh
		try {

			File fXmlFile = new File("UnsolvedSudoku9x9.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
	
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			
			NodeList sizeList = doc.getElementsByTagName("size"); //pulls the size of the grid
			
			SIZE = Integer.parseInt(sizeList.item(0).getTextContent());
			
			BOXSIZE = SIZE; //puts size of squares into public variable for inSquare method
			
			SIZE *= SIZE; //finds full size of the sudoku grid
			
			board = new int [SIZE][SIZE]; //instantiates the grid
					
			NodeList nList = doc.getElementsByTagName("field"); //puts all fields into grid
			
			int index;
			int row;
			int col;
	
			for (int temp = 0; temp < nList.getLength(); temp++) {
	
				Node nNode = nList.item(temp);
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
					Element eElement = (Element) nNode;
					
					index = Integer.parseInt(eElement.getElementsByTagName("index").item(0).getTextContent()); //pulls the index to fill spaces in grid
					
					row = index / SIZE; //finds row index belongs to
					col = index % SIZE; //finds column index belongs to
					
					board[row][col] = Integer.parseInt(eElement.getElementsByTagName("value").item(0).getTextContent()); //puts value in found indices
				}
			}
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		boardStack.push(board); //pushes board onto the stack
	}
	
	public static void displayBoard() {
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				System.out.print(" " + board[i][j]); //prints each item in the sudoku board
			}
			
			System.out.println();
		}
	}
}
