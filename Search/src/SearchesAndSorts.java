//zip in Programming Year 2 Folder
import java.util.Arrays;

public class SearchesAndSorts {
	public static void main(String[] args) {
		int[] array = { 4, 7, 1, 34, 56, 12, 11, 8, 9, 44 };

		System.out.println(Arrays.toString(array));
		// selectionSort(array);
		// bubbleSortV1(array);
		// bubbleSortV2(array);
		// bubbleSortV3(array);
		// insertionSort(array);
		 quickSort(array);
		// mergeSort(array);

		System.out.println(Arrays.toString(array));

	}

	/**
	 * 
	 * @param A
	 *            is any given array
	 * @param key
	 * @return the position of the key element in the array, return -1 if not found
	 */
	public static int sequentialSearch(int[] A, int key) {

		for (int i = 0; i < A.length; i++)
			if (A[i] == key)
				return i;
		return -1; // was not found

		// operational complexity O(n)

	}

	/**
	 * implementing the binary search algorithm
	 * 
	 * @param A
	 *            is a sorted array
	 * @param key
	 * @return the position of the key element in the array, return -1 if not found
	 */
	public static int binarySearch(int[] A, int key) {

		int start = 0;
		int end = A.length - 1;
		int mid;

		while (start <= end) {
			mid = (start + end) / 2;
			if (key == A[mid])
				return mid;
			else if (key > A[mid])
				start = mid + 1;
			else
				end = mid - 1;
		}

		return -1; // was not found

		// operational complexity O(log n)

	}

	/**
	 * sort the array using the selection sort algorithm
	 * 
	 * @param A
	 */
	public static void selectionSort(int[] A) {

		int maxValue;
		int pos;
		int temp;

		for (int i = 0; i < A.length - 1; i++) {
			pos = i;
			maxValue = A[i];
			for (int j = i; j < A.length; j++)
				if (A[j] > maxValue) {
					maxValue = A[j];
					pos = j;
				}
			temp = A[pos];
			A[pos] = A[i];
			A[i] = temp;

		}

		// operational complexity O(n^2)

	}

	/**
	 * bubble sort not a very good version
	 * 
	 * @param A
	 */
	public static void bubbleSortV1(int[] A) {
		int temp;

		for (int i = 0; i < A.length - 1; i++)
			for (int j = 0; j < A.length - 1; j++) {
				if (A[j] > A[j + 1]) {
					temp = A[j];
					A[j] = A[j + 1];
					A[j + 1] = temp;
				}
			}
		// operational complexity O(n^2)
	}

	/**
	 * bubble sort improved version
	 * 
	 * @param A
	 */
	public static void bubbleSortV2(int[] A) {
		int temp;
		boolean swapped = true;

		while (swapped) {
			swapped = false;
			for (int j = 0; j < A.length - 1; j++) {
				if (A[j] > A[j + 1]) {
					swapped = true;
					temp = A[j];
					A[j] = A[j + 1];
					A[j + 1] = temp;
				}
			}
		}
		//operational complexity O(n^2)
	}

	/**
	 * bubble sort improved version
	 * 
	 * @param A
	 */
	public static void bubbleSortV3(int[] A) {
		int temp;
		boolean swapped = true;
		int count = 0;  //will be used as the while loop cycle counter

		while (swapped) {
			swapped = false;
			for (int j = 0; j < A.length - 1 - count; j++) {
				if (A[j] > A[j + 1]) {
					swapped = true;
					temp = A[j];
					A[j] = A[j + 1];
					A[j + 1] = temp;
				}
			}
			count++;
		}
		// operational complexity O(n^2)
	}

	public static void insertionSort(int[] A) {
		int i, key, j;
		for (i = 1; i < A.length; i++) {
			key = A[i];
			j = i - 1;

			/*
			 * Move elements of A[0..i-1], that are greater than key, to one position ahead
			 * of their current position
			 */
			while (j >= 0 && A[j] > key) {
				A[j + 1] = A[j];
				j = j - 1;
			}
			A[j + 1] = key;
		}

		// operational complexity O(n^2)

	}

	public static void quickSort(int[] A) { //technique can be considered divide and conquer
		quickSortHelper(A, 0, A.length - 1); //this sort works 1000 times better than any sort that is n^2 (literally)

		// operational complexity O(nlog n) 
	}

	private static void quickSortHelper(int[] A, int lowerIndex, int higherIndex) {

		int i = lowerIndex;
		int j = higherIndex;
		int temp;

		// calculate pivot number, I am taking pivot as middle index number
		int pivot = A[lowerIndex + (higherIndex - lowerIndex) / 2];
		// Divide into two arrays
		while (i <= j) {
			/**
			 * In each iteration, we will identify a number from left side which is greater
			 * then the pivot value, and also we will identify a number from right side
			 * which is less then the pivot value. Once the search is done, then we exchange
			 * both numbers.
			 */
			while (A[i] < pivot) {
				i++;
			}
			while (A[j] > pivot) {
				j--;
			}
			if (i <= j) {
				temp = A[i];
				A[i] = A[j];
				A[j] = temp;
				// move index to next position on both sides
				i++;
				j--;
			}
		}
		// call quickSort() method recursively
		if (lowerIndex < j)
			quickSortHelper(A, lowerIndex, j);
		if (i < higherIndex)
			quickSortHelper(A, i, higherIndex);
	}

	private static void mergeSort(int[] A) {
		int[] helper = new int[A.length];

		mergeSortHelper(A, helper, 0, A.length - 1);

		// operational complexity O(nlog n)
	}

	private static void mergeSortHelper(int[] A, int[] helper, int low, int high) {

		if (low < high) {

			int middle = low + (high - low) / 2;

			mergeSortHelper(A, helper, low, middle);

			mergeSortHelper(A, helper, middle + 1, high);

			merge(A, helper, low, middle, high);
		}
	}

	private static void merge(int[] A, int[] helper, int low, int middle, int high) {

		for (int i = low; i <= high; i++) {
			helper[i] = A[i];
		}

		int i = low;
		int j = middle + 1;
		int k = low;

		while (i <= middle && j <= high) {
			if (helper[i] <= helper[j]) {
				A[k] = helper[i];
				i++;
			} else {
				A[k] = helper[j];
				j++;
			}
			k++;
		}

		while (i <= middle) {
			A[k] = helper[i];
			k++;
			i++;
		}
	}
}
