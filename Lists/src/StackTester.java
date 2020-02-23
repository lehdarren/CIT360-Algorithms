
public class StackTester {

	public static void main(String[] args) {
		LStack<Integer>  stack;
		
		stack = new LStack();
		
		stack.push(5);
		stack.push(9);
		stack.push(2);
		stack.push(6);
		stack.push(67);
		stack.push(523);
		stack.push(15);
		
		
		while(!stack.isEmpty())
			try {
				System.out.println(stack.peek());
				System.out.println(stack.size());
				System.out.println(stack.pop());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
