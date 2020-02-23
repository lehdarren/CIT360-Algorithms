
public class ListTester {

	public static void main(String[] args) {
//		AList<String>  contacts;
		SLList<String>  contacts;
		
			
//		contacts = new AList<String>();
		contacts = new SLList<String>();
		System.out.println("Step 1: " + contacts);
		
		
		contacts.add("Joe");
		System.out.println("Step 2: " + contacts);

		contacts.add("Sue");
		contacts.add("Jane");
		contacts.add("Bob");
		contacts.add("Adam");
		
		System.out.println("Step 3: " + contacts);
		
		
		System.out.println("Step 4: " + contacts.firstIndexOf("Adam"));

		

	
		contacts.add("Kim",  0);
		contacts.add("Stacy",  20);

		
		System.out.println("Step 5: " + contacts);

		
		contacts.add("Max",  3);
		System.out.println("Step 6: " + contacts);
		
		
		
		


		contacts.set("Peter", 2);
		contacts.set("Kimbely", 0);
		
		System.out.println("Step 7: " + contacts);
		

		System.out.println("Step 8: " + contacts.remove(2));
		
		System.out.println("Step 9: " + contacts);
		
		System.out.println("Step 10: " + contacts.remove(0));
		
		System.out.println("Step 11: " + contacts);
		
		System.out.println("Step 12: " + contacts.firstIndexOf("Bob"));

		contacts.remove(3);
		
		System.out.println("Step 13: " + contacts);
		
		contacts.add("Sue");
		contacts.add("Jim");
		contacts.add("Sue");
		contacts.add("Al");
		
		System.out.println("Step 14: " + contacts);

		contacts.remove("Sue");
		contacts.add("Sam");
		contacts.add("Betty");
		contacts.add("Lili");
		contacts.add("Sue");
		contacts.add("Sue");
		contacts.add("Dave");
		contacts.add("Sue");

		
		System.out.println("Step 15: " + contacts);
		contacts.remove("Sue");
		System.out.println("Step 16: " + contacts);
		contacts.removeAll("Sue");
		System.out.println("Step 17: " + contacts);
		
		contacts.sort();
		System.out.println("Step 18: " + contacts);
		

	}

}
