
public class BSTTester {

	public static void main(String[] args) {
		BST<String, String> contacts;
		
		contacts = new BST<String, String>();
		
		contacts.insert("Joe", "123 Main");
		contacts.insert("Sue", "1234 Main");
		contacts.insert("Bob", "1235 Main");
		contacts.insert("Sam", "1236 Main");
		contacts.insert("Adam", "1237 Main");
		contacts.insert("Bill", "1238 Main");
		
		System.out.println("In Order Traversal");
		contacts.inOrderTraversal();
		System.out.println();
		
		System.out.println("Post Order Traversal");
		contacts.postOrderTraversal();
		System.out.println();
		
		System.out.println("Pre Order Traversal");
		contacts.preOrderTraversal();
		System.out.println();
		
		System.out.println("Level Order Traversal");
		contacts.levelOrderTraversal();
		System.out.println();
		
		System.out.println("Size");
		System.out.println(contacts.size());
		System.out.println();
		
		System.out.println("Height");
		System.out.println(contacts.height());
		
		System.out.println("");
		contacts.remove("Adam");
		
		contacts.levelOrderTraversal();
		contacts.insert("Adam",  "test");
		System.out.println("");
		
		contacts.levelOrderTraversal();
		

	}

}
