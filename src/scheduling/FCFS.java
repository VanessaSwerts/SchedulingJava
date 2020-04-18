package scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FCFS {
    private List<Jobs> readyQueue = new ArrayList<Jobs>();
    public double avgTime = 0;
    private double totalJobs = 0;
    private double totalTime = 0;
    private double position = 0;

    public FCFS(List<Jobs> readyQueue) {
        this.readyQueue = readyQueue;
        this.totalJobs = readyQueue.size();

        Collections.sort(this.readyQueue, firstArrived);

        this.position = this.readyQueue.get(0).getArrivalTime();
    }

    public static Comparator<Jobs> firstArrived = new Comparator<Jobs>() {
        @Override
        public int compare(Jobs job1, Jobs job2) {
            if (job1.getArrivalTime() > job2.getArrivalTime()) {
                return 1;
            } else if (job1.getArrivalTime() < job2.getArrivalTime()) {
                return -1;
            } else {
                return 0;
            }
        }
    };

    public void execute() {
        for (Jobs jobs : readyQueue) {
            totalTime = totalTime + position;
            position = position + jobs.getBurstTime();

//            try {
//                System.out.println("Processo P" + jobs.getId() + " está executando (Burst Time = " + jobs.getBurstTime() + " segundos)");
//                Thread.sleep(jobs.getBurstTime() * 1000);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(FCFS.class.getName()).log(Level.SEVERE, null, ex);
//            }
        }

        this.avgTime = this.totalTime / this.totalJobs;
        System.out.println("TM = " + this.avgTime + " segundos");
    }
}
