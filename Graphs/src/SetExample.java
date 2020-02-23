import java.util.HashSet;
import java.util.Set;

public class SetExample {
	public static void main(String[] args) {
		Set<Integer> mySet = new HashSet<Integer>();
	
		mySet.add(4);
		mySet.add(3);
		mySet.add(13);
		mySet.add(9);
		
		System.out.println(mySet);
		
		mySet.remove(9);
		
		System.out.println(mySet);
		
		if(mySet.contains(7))
			System.out.println("Is there");
		else
			System.out.println("Is not there");
		
		
	}
	
	
}
