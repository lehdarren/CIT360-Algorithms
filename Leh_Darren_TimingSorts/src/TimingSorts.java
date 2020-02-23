/*
 * Project created by Darren Leh
 * This project uses sorts that were generated in Data Structures and Algorithms 360 by Professor Golshan
 */
import java.util.Arrays;
import java.util.Random;

public class TimingSorts {
	public static void main(String[] args) {
		final int ARRAYMAX = 1000; //maximum array size
		final int ITERATIONS = 500; //number of iterations per average time
		long mTime = 0;
		long startTime;
		int averageCounter;
		long [][] timeArray = new long [5][2];//used to keep time
		
		int [] numberArray = new int [ARRAYMAX]; //keeps numbers for size 1000
		int [] numberArray2 = new int [100]; //keeps numbers for size 100
		int [] resetArray = new int[ARRAYMAX];
		int [] resetArray2 = new int[100];
		
		randomizeNumbers(numberArray); //randomizes the numbers in the array
		resetArray = numberArray.clone(); //clones the array into a reset so the same data is always used
		
		//FINDING THE FIRST SET OF TIMES
		for (averageCounter = 0; averageCounter < ITERATIONS; averageCounter++) {
			startTime = System.nanoTime(); //creates startTime variable
			mergeSort(numberArray); //calls merge sort method
			mTime += (System.nanoTime() - startTime); //finds mTime, which is your time for this sort
			numberArray = resetArray.clone();
		}
		
		mTime /= ITERATIONS; //finds the average of all of them
		
		timeArray[0][0] = mTime; //inserts time into array for safekeeping
		mTime = 0;
		
		for (averageCounter = 0; averageCounter < ITERATIONS; averageCounter++) { // this for loop helps to repeat to get the average
			startTime = System.nanoTime();
			quickSort(numberArray); //calls quick sort method
			mTime += (System.nanoTime() - startTime);
			numberArray = resetArray.clone();
		}
		
		mTime /= ITERATIONS;
		
		timeArray[1][0] = mTime;
		mTime = 0;
		
		for (averageCounter = 0; averageCounter < ITERATIONS; averageCounter++) {
			startTime = System.nanoTime();
			bubbleSort(numberArray); //calls bubble sort method
			mTime += (System.nanoTime() - startTime);
			numberArray = resetArray.clone();
		}
		
		mTime /= ITERATIONS;
		
		timeArray[2][0] = mTime;
		mTime = 0;
		
		for (averageCounter = 0; averageCounter < ITERATIONS; averageCounter++) {
			startTime = System.nanoTime();
			insertionSort(numberArray); //calls insertion sort method
			mTime += (System.nanoTime() - startTime);
			numberArray = resetArray.clone();
		}
		
		mTime /= ITERATIONS;
		
		timeArray[3][0] = mTime;
		mTime = 0;
		
		for (averageCounter = 0; averageCounter < ITERATIONS; averageCounter++) {
			startTime = System.nanoTime();
			selectionSort(numberArray); //calls selection sort method
			mTime += (System.nanoTime() - startTime);
			numberArray = resetArray.clone();
		}
		
		mTime /= ITERATIONS;
		
		timeArray[4][0] = mTime;
		mTime = 0;
		
		//-------------------------------------------------
		//FINDING THE SECOND SET OF TIMES
		randomizeNumbers(numberArray2);
		resetArray2 = numberArray2.clone();
		
		for (averageCounter = 0; averageCounter < ITERATIONS; averageCounter++) {
			startTime = System.nanoTime();
			mergeSort(numberArray2);
			mTime += (System.nanoTime() - startTime);
			numberArray2 = resetArray2.clone();
		}
		
		mTime /= ITERATIONS;
		
		timeArray[0][1] = mTime;
		mTime = 0;
		
		for (averageCounter = 0; averageCounter < ITERATIONS; averageCounter++) {
			startTime = System.nanoTime();
			quickSort(numberArray2);
			mTime += (System.nanoTime() - startTime);
			numberArray2 = resetArray2.clone();
		}
		
		mTime /= ITERATIONS;
		
		timeArray[1][1] = mTime;
		mTime = 0;
		
		for (averageCounter = 0; averageCounter < ITERATIONS; averageCounter++) {
			startTime = System.nanoTime();
			bubbleSort(numberArray2);
			mTime += (System.nanoTime() - startTime);
			numberArray2 = resetArray2.clone();
		}
		
		mTime /= ITERATIONS;
		
		timeArray[2][1] = mTime;
		mTime = 0;
		
		for (averageCounter = 0; averageCounter < ITERATIONS; averageCounter++) {
			startTime = System.nanoTime();
			insertionSort(numberArray2);
			mTime += (System.nanoTime() - startTime);
			numberArray2 = resetArray2.clone();
		}
		
		mTime /= ITERATIONS;
		
		timeArray[3][1] = mTime;
		mTime = 0;
		
		for (averageCounter = 0; averageCounter < ITERATIONS; averageCounter++) {
			startTime = System.nanoTime();
			selectionSort(numberArray2);
			mTime += (System.nanoTime() - startTime);
			numberArray2 = resetArray2.clone();
		}
		
		mTime /= ITERATIONS;
		
		timeArray[4][1] = mTime;
		
		displayTimes(timeArray); //calls display times method
	}
	
	public static void displayTimes(long[][] timeArray) { //this method is used to find the max values,
														  //and display the times compared to the longest time
		double mTime; //merge time
		double qTime; //quick time
		double bTime; //bubble time
		double iTime; //insertion time
		double sTime; //selection time
		int maxLength = 50;
		double maxValue = 0;
		
		for (int i = 0; i < 2; i++) {
			maxValue = timeArray[0][i];
			for (int j = 0; j < timeArray.length; j++) {
				
				if (maxValue < timeArray[j][i])
				{
					maxValue = timeArray[j][i];
				}
			}
			
			mTime = (timeArray[0][i] / maxValue);
			qTime = (timeArray[1][i] / maxValue);
			bTime = (timeArray[2][i] / maxValue);
			iTime = (timeArray[3][i] / maxValue);
			sTime = (timeArray[4][i] / maxValue);
			
			//these methods and variables find the amount of stars needed to create a bar chart
			String mergeBar = barChart((int)(mTime * maxLength));
			String quickBar = barChart((int)(qTime * maxLength));
			String bubbleBar = barChart((int)(bTime * maxLength));
			String insertionBar = barChart((int)(iTime * maxLength));
			String selectionBar = barChart((int)(sTime * maxLength));
			
			if (i == 0) { //this if statement will display certain outcomes based on size of array
				System.out.println("Case 1");
				System.out.println("Array Size:  100");
			} else {
				System.out.println("Case 2");
				System.out.println("Array Size: 1000");
			}
			
			//these are fixed displays
			System.out.println("Max Value:  1000");
			System.out.println("Iterations:  500");
			System.out.println("");
			System.out.println("Average Run Times (in Nano Seconds):");
			System.out.println("Merge Sort:     " + mergeBar + " " + ((int) (mTime * 50)));
			System.out.println("Quick Sort:     " + quickBar + " " + ((int) (qTime * 50)));
			System.out.println("Bubble Sort:    " + bubbleBar + " " + ((int) (bTime * 50)));
			System.out.println("Insertion Sort: " + insertionBar + " " + ((int) (iTime * 50)));
			System.out.println("Selection Sort: " + selectionBar + " " + ((int) (sTime * 50)));
			System.out.println("-----------------------------------------------------------------");
			
		}
	}
	
	//CONVERTS THE NUMBERS INTO A BAR CHART OF STARS
	public static String barChart(int number) {
	    StringBuilder barChart = new StringBuilder();
	    for (int i = 0; i < number; i++) {
	        barChart.append("*");
	    }
	    return barChart.toString();
	}
	
	//RANDOMIZING NUMBERS
	public static void randomizeNumbers(int[] array) {
		Random randNum = new Random();
		
		for (int i = 0; i < array.length; i++) {
			array[i] = randNum.nextInt(1000);
		}
	}
	
	/**
	 * MERGE SORTING
	 */
	private static void mergeSort(int[] array) {
		int[] helper = new int[array.length];

		mergeSortHelper(array, helper, 0, array.length - 1);
	}

	private static void mergeSortHelper(int[] array, int[] helper, int low, int high) {

		if (low < high) {

			int middle = low + (high - low) / 2;

			mergeSortHelper(array, helper, low, middle);

			mergeSortHelper(array, helper, middle + 1, high);

			merge(array, helper, low, middle, high);
		}
	}

	private static void merge(int[] array, int[] helper, int low, int middle, int high) {

		for (int i = low; i <= high; i++) {
			helper[i] = array[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;

		while (i <= middle && j <= high) {
			if (helper[i] <= helper[j]) {
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			array[k] = helper[i];
			k++;
			i++;
		}
	}
	
	/**
	 * QUICK SORTING
	 */
	public static void quickSort(int[] array) {
		quickSortHelper(array, 0, array.length - 1);
	}

	private static void quickSortHelper(int[] array, int lowerIndex, int higherIndex) {

		int i = lowerIndex;
		int j = higherIndex;
		int temp;

		// calculate pivot number, I am taking pivot as middle index number
		int pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];
		// Divide into two arrays
		
		while (i <= j) {
			while (array[i] < pivot) {
				i++;
			}
			while (array[j] > pivot) {
				j--;
			}
			if (i <= j) {
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				// move index to next position on both sides
				
				i++;
				j--;
			}
		}
		
		// call quickSort() method recursively
		if (lowerIndex < j)
			quickSortHelper(array, lowerIndex, j);
		if (i < higherIndex)
			quickSortHelper(array, i, higherIndex);
	}
	
	/**
	 * BUBBLE SORTING
	 */
	public static void bubbleSort(int[] array) {
		int temp;

		for (int i = 0; i < array.length - 1; i++)
			for (int j = 0; j < array.length - 1; j++) {
				if (array[j] > array[j + 1]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
	}
	
	/**
	 * INSERTION SORTING
	 */
	public static void insertionSort(int[] array) {
		int i, key, j;
		for (i = 1; i < array.length; i++) {
			key = array[i];
			j = i - 1;

			
			while (j >= 0 && array[j] > key) {
				array[j + 1] = array[j];
				j = j - 1;
			}
			array[j + 1] = key;
		}
	}
	
	/**
	 * SELECTION SORTING
	 */
	public static void selectionSort(int[] array) {

		int arrayMAX;
		int pos;
		int temp;

		for (int i = 0; i < array.length - 1; i++) {
			pos = i;
			arrayMAX = array[i];
			for (int j = i; j < array.length; j++)
				if (array[j] > arrayMAX) {
					arrayMAX = array[j];
					pos = j;
				}
			temp = array[pos];
			array[pos] = array[i];
			array[i] = temp;

		}
	}
}
