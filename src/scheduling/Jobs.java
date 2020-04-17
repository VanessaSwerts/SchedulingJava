package scheduling;

public class Jobs {

    private int id;
    private int arrivalTime;
    private int burstTime;
    private int executed;
    private int lastExecuted;

    public int getExecuted() {
        return executed;
    }

    public void setExecuted(int executed) {
        this.executed = executed;
    }

    public int getLastExecuted() {
        return lastExecuted;
    }

    public void setLastExecuted(int lastExecuted) {
        this.lastExecuted = lastExecuted;
    }

    public Jobs(int id, int arrivalTime, int burstTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }
}
