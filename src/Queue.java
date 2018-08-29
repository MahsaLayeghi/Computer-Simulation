import java.util.LinkedList;

public class Queue {

    private LinkedList<Object> queue ;
    private int maxLength ;

    public Queue() {
        this.queue = new LinkedList<Object>();
        maxLength = 0;
    }

    public void enqueue(Object item) {
        queue.addLast(item);
        if(queue.size() > maxLength)
            maxLength = queue.size();
    }

    public int getMaxLength() {
        return maxLength;
    }


    public Object peek(){

        return queue.peek();
    }


    public Object dequeue() {
        if(queue.size() > 0)
            return queue.poll();
        else
            System.out.println("Queue is empty");
        return null;
    }

    public boolean hasItems() {
        return !queue.isEmpty();
    }

    public void remove(Object item){
        queue.remove(item);
    }

    public int size() {
        return queue.size();
    }

    public void clear(){
        queue.clear();
    }



}
