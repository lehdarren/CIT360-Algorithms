
public class DLLNode<T extends Comparable<T>> {

		private T data;
		private DLLNode<T> next;
		private DLLNode<T> previous;
		
		
		public DLLNode(T data, DLLNode<T> next, DLLNode<T> prev) {
			super();
			this.data = data;
			this.next = next;
			this.previous = prev;
		}
		
		public DLLNode(T data) {
			super();
			this.data = data;
			this.next = null;
			this.previous = null;
		}
		
		
		public DLLNode() {
			super();
			this.data = null;
			this.next = null;
			this.previous = null;
		}	
		
		
		public DLLNode(DLLNode<T> node) {
			super();
			this.data = node.data;
			this.next = node.next;
			this.previous = node.previous;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public DLLNode<T> getNext() {
			return next;
		}

		public void setNext(DLLNode<T> node) {
			this.next = node;
		}

		public DLLNode<T> getPrevious() {
			return previous;
		}

		public void setPrevious(DLLNode<T> node) {
			this.previous = node;
		}


		@Override
		public String toString() {
			return data.toString();
		}
   
		
		
		
}
