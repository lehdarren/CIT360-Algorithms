//Binary Search Tree Node
public class BSTNode <K, V>{
	private K key;
	private V value;
	private BSTNode<K, V> left;
	private BSTNode<K, V> right;
	
	public BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
		super();
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public BSTNode() {
		super();
		this.key = null;
		this.value = null;
		this.left = null;
		this.right = null;
	}
	
	public BSTNode(K key, V value) {
		super();
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
	}

	public K key() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V value() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public BSTNode<K, V> left() {
		return left;
	}

	public void setLeft(BSTNode<K, V> left) {
		this.left = left;
	}

	public BSTNode<K, V> right() {
		return right;
	}

	public void setRight(BSTNode<K, V> right) {
		this.right = right;
	}
	
	public boolean isLeaf() {
		return (left == null) && (right == null);
	}

	@Override
	public String toString() {
		return "BSTNode [key=" + key + ", value=" + value + "]";
	}
	
	public boolean isInternal() {
		return !isLeaf();
	}
	
	
}
