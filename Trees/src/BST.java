//Binary Search Tree
public class BST <K extends Comparable<? super K>, V>{
	private BSTNode<K, V> root;
	
	/** default constructor */
	public BST() {
		super();
		root = null;
	}
	
	public void insert(K key, V value) {
		root = insertHelper(key, value, root);
	}

	private BSTNode<K, V> insertHelper(K key, V value, BSTNode<K, V> node) {
		if (node == null) {
			node = new BSTNode<K, V>(key, value); //generates the node
		} 
		else if (node.key().compareTo(key) >= 0){
			node.setLeft(insertHelper(key, value, node.left())); //if the key is less than current node key, set left
		} else {
			node.setRight(insertHelper(key, value, node.right())); //if the key is more than current node key, set right
		}
		
		
		return node;
	}
	
	/**
	 * IN ORDER TRAVERSAL
	 */
	public void inOrderTraversal() {
		inOrderHelper(root);
	}

	private void inOrderHelper(BSTNode<K, V> node) {
		if (node == null)
			return;
		
		if(node.left() != null)
			inOrderHelper(node.left());
		
		visit(node);
		
		if(node.right() != null)
			inOrderHelper(node.right());
	}
	
	/**
	 * POST ORDER TRAVERSAL
	 */
	public void postOrderTraversal() {
		postOrderHelper(root);
	}
	
	private void postOrderHelper(BSTNode<K, V> node) {
		if(node == null)
			return;
		
		if(node.left() != null)
			postOrderHelper(node.left());
		
		if(node.right() != null)
			postOrderHelper(node.right());
		
		visit(node);
		
	}
	
	/**
	 * PRE ORDER TRAVERSAL
	 * @param node
	 */
	public void preOrderTraversal() {
		preOrderHelper(root);
	}
	
	private void preOrderHelper(BSTNode<K, V> node) {
		if(node == null)
			return;
		
		visit(node);
		
		if(node.left() != null)
			postOrderHelper(node.left());
		
		if(node.right() != null)
			postOrderHelper(node.right());
	}

	public void levelOrderTraversal() {
		LQueue<BSTNode<K, V>> queue = new LQueue<BSTNode<K, V>>();
		
		BSTNode<K, V> temp = new BSTNode<K, V>();
		
		queue.enqueue(root);
		
		while(!queue.isEmpty()) {
			temp = queue.dequeue();
			
			visit(temp);
			
			if(temp.left() != null)
				queue.enqueue(temp.left());
			
			if(temp.right() != null)
				queue.enqueue(temp.right());
		}
	}

	/* return the number of elements in the BST MY WAY*/
	public int size() {
		LQueue<BSTNode<K, V>> queue = new LQueue<BSTNode<K, V>>();
		
		BSTNode<K, V> temp = new BSTNode<K, V>();
		
		int size = 0;
		
		queue.enqueue(root);
		
		while(!queue.isEmpty()) {
			temp = queue.dequeue();
			
			if(temp.left() != null)
				queue.enqueue(temp.left());
			
			if(temp.right() != null)
				queue.enqueue(temp.right());
			
			size++;
		}
		
		return size;
	}
	
	/* GOLSHANS WAY TO FIND THE SIZE RECURSIVELY */
	public int sizeRecursion() {
		return sizeHelper(root);
	}
	
	private int sizeHelper(BSTNode<K, V> node) {
		if (node == null)
			return 0;
		
		return 1 + sizeHelper(node.left()) + sizeHelper(node.right());
	}
	
	private void visit(BSTNode<K, V> node) {
		System.out.println(node.key() + "\t" + node.value());
	}
	
	/** return the heigh of the BST */
	public int height() {
		return heightHelper(root);
	}

	private int heightHelper(BSTNode<K, V> node) {
		if (node == null)
			return 0;
		
		return 1 + Math.max(heightHelper(node.left()), heightHelper(node.right()));
	}
	
	public V remove(K key) {
		return removeHelper(key, root);
	}

	public V removeHelper(K key, BSTNode<K, V> node) {
		BSTNode<K, V> tempNode;
		
		if (node == null)
			return null;
		
		if(node.key().compareTo(key) == 0 && node.left() == null) {
			//node to be removed has no left child
			V tempValue = node.value();
			//node = node.right();
			node.setKey(node.right().key());
			node.setValue(node.right().value());
			node.setLeft(node.right().left());
			node.setRight(node.right().right());
		}
		else if(node.key().compareTo(key) == 0  && node.left() != null) {
			//remove the largest node from the left subtree
			//and  modifies the left subtree
			tempNode = this.removeLargestNode(node.left());
			node.setKey(tempNode.key());
			node.setValue(tempNode.value());
			return tempNode.value();
		}
		else if(node.key().compareTo(key) > 0){
			this.removeHelper(key, node.left());
			//remove the node from the right subtree
			//this modifies the right subtree
		}
		else {
			this.removeHelper(key, node.right());
		}
		
		return null;
	

	}
	
	/**
	 * remove and return the node containing the largest child in the subtree
	 * @param node
	 * @return
	 */
	private BSTNode<K, V> removeLargestNode(BSTNode<K, V> node){
		BSTNode<K, V> tempNode;
		
		if (node == null)
			return null;
		if(node.isLeaf()) {
			tempNode = new BSTNode<K, V>(node.key(), node.value());
			node = null;
			return tempNode;
		}
		
		BSTNode<K, V> prev = null;
		BSTNode<K, V> curr = node;
		while(curr.right() != null) {
			prev = curr;
			curr = curr.right();
		}
		if(curr.isLeaf() && prev == null) {
			 tempNode = new BSTNode<K, V>(curr.key(), curr.value());
			curr = null;
		  return tempNode;
		}
		else if(curr.isLeaf() && prev != null) {
			 tempNode = new BSTNode<K, V>(curr.key(), curr.value());
			curr = null;
			prev.setRight(null);
		  return tempNode;
		}
		else if(prev == null) {
			 tempNode = new BSTNode<K, V>(curr.key(), curr.value());
			curr.setKey(curr.left().key());
			curr.setValue(curr.left().value());
			curr.setLeft(curr.left().left());
			curr.setRight(curr.left().right());
			return tempNode;
		}
		else { // prev ! null
			tempNode = new BSTNode<K, V>(curr.key(), curr.value());
			curr.setKey(curr.left().key());
			curr.setValue(curr.left().value());
			curr.setLeft(curr.left().left());
			curr.setRight(curr.left().right());
			return tempNode;
		}
		
	}
}