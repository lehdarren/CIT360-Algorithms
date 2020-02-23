
public class SLLNode<T extends Comparable<T>> {

		private T data;
		private SLLNode<T> next;
		
		
		public SLLNode(T data, SLLNode<T> next) {
			super();
			this.data = data;
			this.next = next;
		}
		
		public SLLNode(T data) {
			super();
			this.data = data;
			this.next = null;
		}
		
		
		public SLLNode() {
			super();
			this.data = null;
			this.next = null;
		}	
		
		
		public SLLNode(SLLNode<T> node) {
			super();
			this.data = node.data;
			this.next = node.next;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public SLLNode<T> getNext() {
			return next;
		}

		public void setNext(SLLNode<T> next) {
			this.next = next;
		}

		@Override
		public String toString() {
			return data.toString();
		}
   
		
		
		
}
