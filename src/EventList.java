import java.util.PriorityQueue;

public class EventList {

    private PriorityQueue<Process> list;

    public EventList() {
        list = new PriorityQueue<Process>();
    }

    public Process poll () {
        // This method retrieves and removes the head of this list, or returns null if this queue is empty.
        if (list.size() > 0)
            return list.poll();
        else
            System.out.println("Event list is empty");
        return null;
    }

    public Process peek() {
        // This method retrieves, but does not remove, the head of this list, or returns null if this queue is empty.

        if(list.size() > 0)
            return list.peek();
        else
            System.out.println("Event list is empty");
        return null;
    }

    public boolean add(Process p){
        // This method inserts the specified element into this list.
        return list.add(p);
    }

}