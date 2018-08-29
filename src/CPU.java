public class CPU {

    double totalResponseTime ;
    boolean busy;

    public CPU() {
        this.totalResponseTime = 0;
        this.busy = false;
    }


    public double getTotalResponseTime() {
        return totalResponseTime;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setTotalResponseTime(double totalResponseTime) {
        this.totalResponseTime = totalResponseTime;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }
}
