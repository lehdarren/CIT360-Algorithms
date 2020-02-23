
public class LStack<T>implements Stack<T>{
	
	private int size;
	private StackNode<T> top;
	
	
	
	
	public LStack(){

		top = null;
		size = 0;
	}
	

	/**
	 * O(1)
	 * @Override
	 */
	public T pop() throws Exception {
		if(!isEmpty()) {
			T data = top.getData();
			top = top.getNext();
			size--;
			return data;
		}
			
		throw new Exception("Stack is emty");
		//return null;
	}

	/**
	 * O(1)
	 * 
	 * @Override
	 */
	public void push(T e) {
		StackNode<T> temp = new StackNode(e, top);
		top = temp;
		size++;
		
	}

	/**
	 * O(1)
	 * @Override
	 */
	public T peek() {
		if(!isEmpty()) {
			StackNode<T> dataCopy = new StackNode(top.getData());
			return dataCopy.getData();
			
			//return top.getData();
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	
	private class StackNode<S> {

		private S data;
		private StackNode<S> next;
		
		
		public StackNode(S data, StackNode<S> next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		public StackNode(S data) {
			super();
			this.data = data;
			this.next = null;
		}
		
		
		public StackNode() {
			super();
			this.data = null;
			this.next = null;
		}	
		


		public S getData() {
			return data;
		}

		public void setData(S data) {
			this.data = data;
		}

		public StackNode<S> getNext() {
			return next;
		}

		public void setNext(StackNode<S> next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return data.toString();
		}
   
		
		
		
}
	
	
}
