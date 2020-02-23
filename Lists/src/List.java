
public interface List<T> {
	/**
	 * e will be added to the end of the list
	 * @param e
	 */
	public void add(T e);
	
	
	
	
	
	/**
	 * e will be added at location i
	 * @param e
	 * @param i
	 */
	public void add(T e, int i);
	
	
	
	/**
	 * reset the element at location i to be the given element e
	 * @param e
	 * @param i
	 */
	public void set(T e, int i);
	
	
	
	
	public T remove(int i);
	
	
	/**
	 * Remove all occurrences of e
	 * @param e
	 */
	public void remove (T e);
	
	
	
	
	
	
	/**
	 * 
	 * @param e
	 * @return  the position of the first occurrence of e.  return -1 if not in list
	 */
	public int firstIndexOf(T e);
	
	
	
	
	public T get(int i);
	
	
	
	public boolean isEmpty();
	
	
	public int size();
	
	public void sort();
	
}
