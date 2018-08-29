public class Process implements Comparable<Process> {

    private static int count = 0;

    private int priority;
    public double time;
    public int server;
    public PState state;
    private int id;

    public Process(int priority , double time) {

        this.priority = priority;
        this.time = time;
        this.server = -1;
        this.state = PState.arrival;
        this.id = count++;
    }

    public int getPriority() {
        return priority;
    }

    public double getTime() {
        return time;
    }

    public int getServer() {
        return server;
    }

    public PState getState() {
        return state;
    }

    public int getId() {
        return id;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public void setServer(int server) {
        this.server = server;
    }

    public void setState(PState state) {
        this.state = state;
    }


    @Override
    public int compareTo(Process process) {
        return Double.compare(time, process.time);
    }
}

enum PState {
    arrival , departure
}