
public class DLList<T extends Comparable<T>> implements List<T> {

	private DLLNode<T> head;
	private DLLNode<T> tail;
	private int size;

	public DLList() {
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
		DLLNode<T> temp;
		
		if (size == 0) {
			head = new DLLNode(e);
			tail = head;
			size = 1;
		} else {
			temp = new DLLNode<T>(e);
			tail.setNext(temp);
			temp.setPrevious(tail);
			tail = temp;
			
			//tail.setNext(new DLLNode(e, null, tail));
			//tail = tail.getNext();
			size++;
		}

	}

	@Override
	public void add(T e, int i) {
		if (i < 0 || i > size)
			return; // invalid i given

		if (i == 0) {
			DLLNode<T> temp = new DLLNode(e, head, null);
			head.setPrevious(temp);
			head = temp;
			size++;
		} 
		else if(i == size){
			DLLNode<T> temp = new DLLNode(e, null, tail);
			tail.setNext(temp);
			tail = temp;
			size++;
		}	
		else {   //adding general case
			DLLNode<T> temp = head;
			for (int pos = 0; pos < i - 1; pos++)
				temp = temp.getNext();
			
			DLLNode newNode = new DLLNode(e, temp.getNext(), temp);
			temp.getNext().setPrevious(newNode);
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

		DLLNode<T> temp = head;
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
		DLLNode<T> tempNode;
		
		if (i < 0 || i >= size)
			return null; // invalid i given

		if (i == 0) {
			tempNode = head;
			tempData = head.getData();
			head = head.getNext();
			head.setPrevious(null);
			tempNode.setNext(null);
			tempNode = null;
			size--;
			return tempData;
		}

		DLLNode<T> temp = head;
		for (int pos = 0; pos < i - 1; pos++)
			temp = temp.getNext();
		
		tempNode = temp.getNext();  //node to be deleted
		tempData = tempNode.getData();
		
		temp.setNext(tempNode.getNext());
		if(tempNode.getNext() != null)
		  tempNode.getNext().setPrevious(temp);
		tempNode.setData(null);
		tempNode.setNext(null);
		tempNode.setPrevious(null);
		
		size--;
		
		
		if (temp.getNext() == null) 
			// tail was deleted
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
			System.out.println("Removing " + this.remove(pos) + " at pos " + pos);

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
		DLLNode<T> temp = head;

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
		DLLNode<T> temp;

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
		DLLNode<T> temp;
		boolean swapped;
		T tempData;

		if (size <= 1)
			return; // nothing to do

		swapped = true;
		while (swapped) {
			swapped = false;
			temp = head;
			while (temp.getNext() != null) {
				if (temp.getData().compareTo(temp.getNext().getData()) > 0) {
					tempData = temp.getData();
					temp.setData(temp.getNext().getData());
					temp.getNext().setData(tempData);
					
					//needs to be fixed.  set the prev references acordingly
					
					swapped = true;
				}
				temp = temp.getNext();
			}
		}

	}

	@Override
	public String toString() {
		String str = "[";
		DLLNode<T> temp = head;
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
