
public class SLList<T extends Comparable<T>> implements List<T> {

	private SLLNode<T> head;
	private SLLNode<T> tail;
	private int size;

	public SLList() {
		super();
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	/**
	 * append the element e to the end of the list
	 * 
	 * @Override
	 */
	public void add(T e) {
		SLLNode<T> temp;
		if (size == 0) { //special case: list is empty
			head = new SLLNode(e);
			tail = head;
			size = 1;
		} else {
			temp = new SLLNode(e, null); //same as comments below, just broken down
			tail.setNext(temp);
			tail = temp;
			
			//tail.setNext(new SLLNode(e));
			//tail = tail.getNext();
			size++;
		}

	}

	@Override
	public void add(T e, int i) {
		if (i < 0 || i >= size)
			return; // invalid i given

		if (i == 0) {
			SLLNode<T> temp = new SLLNode(e, head);
			head = temp;
			size++;
		} else {
			SLLNode<T> temp = head;
			for (int pos = 0; pos < i - 1; pos++)
				temp = temp.getNext();
			//SLLNode newNode = new SLLNode(e, temp.getNext());
			SLLNode newNode = new SLLNode(e, null);
			newNode.setNext(temp.getNext()); //this breaks down the commented out code.
			
			temp.setNext(newNode);
			size++;

		}

	}

	/**
	 * Insert element e at location i
	 * @Override
	 */
	public void set(T e, int i) {
		if (i < 0 || i >= size)
			return; // invalid i given

		SLLNode<T> temp = head;
		for (int pos = 0; pos < i; pos++)
			temp = temp.getNext();
		temp.setData(e);

	}

	/**
	 * remove element at location i
	 * @return the removed element
	 * 
	 * @Override
	 */
	public T remove(int i) {
		T tempData;

		if (i < 0 || i >= size)
			return null; // invalid i given

		if (i == 0) {

			tempData = head.getData();
			head = head.getNext();
			size--;
			return tempData;
		}

		SLLNode<T> temp = head;
		for (int pos = 0; pos < i - 1; pos++)
			temp = temp.getNext(); //temp stops at the previous node
		
		tempData = temp.getNext().getData();
		temp.setNext(temp.getNext().getNext());
		size--;
		
		
		if (temp.getNext() == null) 
			// deleting tail
			tail = temp;
	
		return tempData;
		

	}

	/**
	 * Remove the first occurrence of e if exists
	 * 
	 * @Override
	 */
	public void remove(T e) {
		int pos = this.firstIndexOf(e);

		if (pos != -1)
			this.remove(pos);
			//System.out.println("Removing " + this.remove(pos) + " at pos " + pos);

	}

	/**
	 * Remove all occurrences of e from the l
	 * 
	 * @param e
	 */
	public void removeAll(T e) {
		int pos = firstIndexOf(e);

		if (pos == -1)
			return;
		System.out.println("need to remove " + e + " at pos " + pos);
		remove(pos);
		removeAll(e);

	}

	/**
	 * @return the index of the first occurrence of e. if e is not found, return -1
	 * 
	 * @Override
	 */
	public int firstIndexOf(T e) {
		int pos = 0;
		SLLNode<T> temp = head;

		while (temp != null) {
			if ((temp.getData()).compareTo(e) == 0)
				return pos;
			temp = temp.getNext();
			pos++;

		}
		return -1;
	}

	/**
	 * @return the element data in position i.  If i is invalid, return null
	 * 
	 * @Override
	 */
	public T get(int i) {
		SLLNode<T> temp;

		if (i >= 0 && i < size) {
			temp = head;
			for (int count = 0; count < i; i++)
				temp = temp.getNext();
			return temp.getData();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @return the size of the list
	 * @Override
	 */
	public int size() {

		return size;
	}

	/**
	 * Sort the list using the bubble sort algorithm
	 * @Override
	 */
	public void sort() {
		SLLNode<T> temp;
		boolean swapped;
		T tempData;

		if (size <= 1)
			return; // nothing to do

		swapped = true;
		while (swapped) {
			swapped = false;
			temp = head;
			while (temp.getNext() != tail) {
				if (temp.getData().compareTo(temp.getNext().getData()) > 0) {
					tempData = temp.getData();
					temp.setData(temp.getNext().getData());
					temp.getNext().setData(tempData);
					swapped = true;
				}
				temp = temp.getNext();
			}
		}

	}
	
	//sort the list using the insertion sort algorithm
	public void insertionSort() {
		SLLNode<T> before = head;
		SLLNode<T> current = head.getNext();
		SLLNode<T> temp;
		
		if (size <= 0)
			return; //nothing to do
		
		while (current != null) {
			if (current.getData().compareTo(before.getData()) >= 0) {
				before = current;
				current = current.getNext();
			} else {
				temp = head;
				
				while(current.getData().compareTo(temp.getData()) > 0)
					temp = temp.getNext();
				if(temp == head) {
					before.setNext(current.getNext());
					current.setNext(head);
					head = current;
				} else {
					//incomplete
				}
			}
		}
	}

	@Override
	public String toString() {
		String str = "[";
		SLLNode<T> temp = head;
		for (int i = 0; i < size - 1; i++) {
			str += temp.getData() + ", ";
			temp = temp.getNext();
		}
		if (size != 0)
			str += tail.getData();
		str += "]";
		return str;
	}

}
