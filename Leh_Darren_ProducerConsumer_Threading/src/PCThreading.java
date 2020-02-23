import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.TimeZone;

public class PCThreading { 
	    public static void main(String[] args) throws InterruptedException { 
	        //instantiates the producer/consumer class 
	        final PC pc = new PC(); 
	  
	        //Create producer thread 
	        Thread t1 = new Thread(new Runnable() { 
	            @Override
	            public void run(){ 
	                try{ 
	                    pc.produce(); //runs the producer method
	                } 
	                catch(InterruptedException e){ 
	                    e.printStackTrace(); 
	                } 
	            } 
	        }); 
	  
	        // Create consumer thread 
	        Thread t2 = new Thread(new Runnable() { 
	            @Override
	            public void run() { 
	                try { 
	                    try {
							pc.consume(); //runs the consumer method
						} catch (IOException e) {
							e.printStackTrace();
						} 
	                } 
	                catch(InterruptedException e) { 
	                    e.printStackTrace(); 
	                }
	            }
	        }); 
	  
	        // Start both threads 
	        t1.start(); 
	        t2.start(); 
	  
	        // t1 finishes before t2 
	        t1.join(); 
	        t2.join(); 
	    } 
	  
	    // This class uses a heap, producer (adds items to max heap 
	    // and consumer (removes items). 
	    public static class PC { 
	    	/*
        	 * Message array value representations:
        	 * 0 = Priority Value
        	 * 1 = Time Created
        	 * 2 = Time Processed
        	 * 3 = ProducerID
        	 */
        	String[][] message = new String[20][4];
        	
        	long startTime = System.currentTimeMillis();
        	
	        // Max Heap is shared by Producer and Consumer
	        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
	  
	        // Function called by producer thread 
	        public void produce() throws InterruptedException {
	            int rowNum = 0;
	            long millis;
	            int PID = 1; //producer id starts at 1
	            
	            while (true) {
	            	millis = System.currentTimeMillis(); //finds current time in milliseconds
	                synchronized (this) {
	                	
	                	if ((millis - startTime) >= 15000) //finds the current times and sees if it hit above the 15 second mark
	                		break;
	                	
	                	if (rowNum == 20) //if it hits the boundary, break
	                		break;
	                	
	                	while (maxHeap.isFull()) // if the heap is full, the producer must wait to generate more
	                        wait();
	                	
	                	message[rowNum][0] = Integer.toString(randInt()); //inserts random priority num
	                	message[rowNum][1] = String.format("%tT", millis-TimeZone.getDefault().getRawOffset()); //formats the start time
	                	message[rowNum][2] = ""; //this space is left blank for later time
	                	message[rowNum][3] = Integer.toString(PID); //generates producer id
	                	
	                    //adds priority number to the heap.
	                    maxHeap.add(Integer.parseInt(message[rowNum][0]));
	  
	                    // notifies the consumer thread that now it can start consuming 
	                    notify();
	  
	                    //sleeps the thread for a random amount of time under a second
	                    Thread.sleep(randInt());
	                }
	                
	                PID += 1; //increments to new producer
	                rowNum += 1; //increments row number
	            } 
	        } 
	  
	        // Function called by consumer thread 
	        public void consume() throws InterruptedException, IOException {
	        	String currMessage = "";
	        	long millis;
	        	File messageLog = new File("message_log.csv");
	        	PrintWriter printWriter = new PrintWriter(messageLog); //creates printwriter and adds file to it
	        	long currentTime;
	        	
	            while (true) { 
	                synchronized (this) { 
	                	currentTime = System.currentTimeMillis();
	                	
	                	if ((currentTime - startTime) >= 15000) //finds the current times and sees if it hit above the 15 second mark
	                		break;
	                	
	                    // consumer thread waits while heap is empty
	                    while (maxHeap.isEmpty()) 
	                        wait();
	  
	                    //to retrive the first job in the heap
	                    int val = (int) maxHeap.remove();
	                    
	                    for (int i = 0; i < 20; i++) {
	                    	if (val == Integer.parseInt(message[i][0])) {
	                    		millis = System.currentTimeMillis(); //finds the finish time
	                    		currMessage = message[i][1] + "," + String.format("%tT", millis-TimeZone.getDefault().getRawOffset()) + "," + message[i][0]  + ",PID" + message[i][3];
	                    		System.out.println(currMessage); //prints message to the console
	                    		
	                    		printWriter.write(currMessage + "\n"); //writes method to the file
	                    		
	                    		break; //breaks out of the for loop
	                    	}
	                    }
	                    
	                    
	                    notify(); //notifies a possible waiting producer
	  
	                    //sleep is the same as producer, under 1 second
	                    Thread.sleep(randInt()); 
	                }
	                printWriter.flush();
	            }
	            
	            printWriter.close();
	        }
	        
	        public int randInt() {
	        	return (int) (Math.random() * 1000); //generates random int within 1000
	        }
	    } 
	} 
