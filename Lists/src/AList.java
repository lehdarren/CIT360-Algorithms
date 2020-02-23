	/**
	 * This class will implement the list with an array
	 */
public class AList<T extends Comparable<T>> implements List<T> {
	
	private final static int INITIAL_CAPACITY = 10;
	private T[] list;
	private int size;   // actual number of elements in the list
	private int capacity;
	
	
	
	public AList() {
       this(INITIAL_CAPACITY);
	}
	
	
	public AList(int initialCapacity) {
		
		this.capacity = initialCapacity;
		size = 0;
		list = (T[])(new Comparable[initialCapacity]);
		
		
	}
	
	
	
	

	@Override
	public void add(T e) {
		
		//this method is O(n)
		
		if(size < capacity) {
			list[size] = e;
			size++;
		}
		else {  

			expandList();
			add(e);
			//list[size] = e;
			//size++;
		}
		
	}

	
	
	private void expandList() {
		capacity = 2 * capacity;
		T[] newList = (T[])(new Comparable[capacity]);
		
		for(int i = 0; i < size; i++)
			newList[i] = list[i];
		
		list = newList;
	}
	
	
	
	
	
	
	
	@Override
	public void add(T e, int i) {
		
		//this method is O(n)
		
		if(i > size + 1) {
			return;  //can not do
		}
		if (size == capacity) {
			expandList();
			add(e, i);
		}
		else {
			for(int j = size-1; j >= i; j--)
				list[j+1] = list[j];
			list[i] = e;
			size++;
		}

		
	}

	@Override
	public void set(T e, int i) {
		
		
		//  O(1)
		if(i >=0 && i < capacity) {
			list[i] = e;
		}
		
	}

	@Override
	public T remove(int i) {
		//O(n)

		if(i >=0 && i < size) {
			T temp = list[i];
			if(i == size-1) {
				size--;
				return temp;
			}
			
			
			for (int j = i; j < size-1; j++) {
				list[j] = list[j+1];
			}
			size--;
			return temp;
		}
		return null;
	}

	@Override
	public void remove(T e) {

		int pos = this.firstIndexOf(e);
		if(pos != -1) {   //found one
			this.remove(pos);
			this.remove(e);
		}
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
	 * return the index f the first occurrence of e
	 * if not found return -1
	 * 
	 * @Override
	 */
	public int firstIndexOf(T e) {
		for (int i = 0; i < size; i++) {
			if(list[i].compareTo(e) == 0) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @Override
	 */
	public T get(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		
		return size;
	}
	


	public String toString() {
		String str = "list = [";
		
		for(int i = 0; i < size -1; i++)
			str += list[i] + ", ";
		if(size != 0)
		    str += list[size - 1];
		str += "]";
		return str;
		
	}


	/**
	 * sort the list using the insertion sort algorithm
	 * 
	 * @Override
	 */
	public void sort() {
		T temp;
		int j;
		
		for (int i = 1; i < size; i++) {
			temp = list[i];
			j = i-1;
		   while(j >= 0 && list[j].compareTo(temp) > 0) {
			   list[j+1] = list[j];
			   j--;
		   }
		   list[j+1] = temp;
		}
		
	}
	
	
}
