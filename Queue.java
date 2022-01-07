// Queue with two stacks. Implement a queue with two stacks so that each queue operations takes a
// constant amortized number of stack operations. Hint: If you push elements onto a stack and then 
// pop them all, they appear in reverse order. If you repeat this process, they're now back in order.
//import java.io.*;
import java.util.*;

class Queue {
    Stack<Integer> stack1;
    Stack<Integer> stack2;
    public Queue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }

    // Push element x to the back of queue.
    public void enqueue(int x) {
        stack1.push(x);
    }
    
    //To transfer the elements from stack1 to stack2
    public void stack1to_2_Transfer(){
        if(stack2.isEmpty()){
            while(!stack1.isEmpty())
                stack2.push(stack1.pop());
        }
    }

    // Removes the element from in front of queue.
    //for condition where stack 2 is empty, first move operation is done, so the time complexity will be O(N)
    public int dequeue() {
        if(empty())throw new NoSuchElementException("Queue underflow");
        stack1to_2_Transfer();
        return stack2.pop();

    }

    // Get the front element.
    //for condition where stack 2 is empty, first move operation is done, so the time complexity will be O(N)
    public int peek() {
        if(empty())throw new NoSuchElementException("queue underflow");
        stack1to_2_Transfer();
        return stack2.peek();
    }

    
    // Return whether the queue is empty.
    public boolean empty() {
        if(size()==0){return true;}
        return false;
    }

    // Return the number of elements in queue.
    public int size() { return stack1.size()+stack2.size();}
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Queue queue = new Queue();
        int queries = Integer.parseInt(scan.nextLine());
        for(int i = 0; i < queries; i++) {
            String input = scan.nextLine();
            if (input.charAt(0) == '1') {
                String[] tokens = input.split(" ");
                queue.enqueue(Integer.parseInt(tokens[1]));
            } else if (input.charAt(0) == '2') {
                queue.dequeue();
            } else if (input.charAt(0) == '3') {
                System.out.println(queue.peek());
            } 
        }
        scan.close();
    }
}
