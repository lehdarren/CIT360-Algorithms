
public interface Stack<T> {
	/**
	 * 
	 * @return remove and return the element at the top
	 * @throws Exception 
	 */
	public T pop() throws Exception;
	/**
	 * 
	 * @param e  adding the new element e to the stack
	 */
	public void push(T e);
	/**
	 * 
	 * @return the element at the top without removing it
	 */
	public T peek();
	/**
	 * 
	 * @return number of elements in stack
	 */
	public int size();
	/**
	 * 
	 * @return true only if stack is empty
	 */
	public boolean isEmpty();
	

}
