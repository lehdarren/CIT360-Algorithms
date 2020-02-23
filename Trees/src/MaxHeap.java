
public class MaxHeap <T extends Comparable<? super T>>{
	private T[] heap;
	private int capacity = 100;
	private int size; //actual number of elements in heap
	
	public MaxHeap() {
		heap = (T[]) new Comparable[capacity];
		size = 0;
	}
	
	public void add(T element) {
		heap[size] = element;
		size++;
		//travel up
		int index = size - 1;
		int parent;
		T temp;
		
		while (index != 0) {
			parent = (index - 1) / 2;
			if (heap[index].compareTo(heap[parent]) <= 0)
				break;
			
			//swap
			temp = heap[parent];
			heap[parent] = heap[index];
			heap[index] = temp;
			
			index = parent;
		}
	}
	
	public T remove() {
		if (this.isEmpty())
			return null;
		T maxElement = heap[0];
		T tempElement;

		// move the last element to the top and re-heapify
		heap[0] = heap[size - 1];
		size--;
		int index = 0;
		int left, right;

		while (index < size) {
			left = 2 * index + 1;
			right = 2 * index + 2;

			if (left >= size)   //no children
				break;
			else if (right == size) {  // hav only a left child
				if (heap[index].compareTo(heap[left]) < 0) {
					tempElement = heap[left];
					heap[left] = heap[index];
					heap[index] = tempElement;
				}
				break;
			}

			else if (heap[index].compareTo(heap[left]) >= 0 && 
					 heap[index].compareTo(heap[right]) >= 0)
				break;
			else  {
				// find the largest child and swap
				if (heap[left].compareTo(heap[right]) >= 0) {
					// left child if the larger one
					tempElement = heap[left];
					heap[left] = heap[index];
					heap[index] = tempElement;
					index = left;
				} else {
					// right child is larger
					tempElement = heap[right];
					heap[right] = heap[index];
					heap[index] = tempElement;
					index = right;
				}
			}
		}

		return maxElement;

	}
	
	public boolean isEmpty() {
		return size == 0;
	}
}
