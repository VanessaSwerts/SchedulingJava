/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexa
 */
public class SJF {

    private List<Jobs> jobs = new ArrayList<Jobs>();
    private double avgTime = 0;
    private double totalJobs = 0;
    private double totalTime = 0;
    private double position = 0;

    public SJF(List<Jobs> jobs) {
        this.jobs = jobs;
        this.totalJobs = jobs.size();

        Collections.sort(this.jobs, firstArrived);

        this.position = this.jobs.get(0).getArrivalTime() - 1;
    }

    public void execute() {
        double total = 0;
        List<Jobs> executedJobs = new ArrayList<Jobs>();
        List<Jobs> readyQueue = new ArrayList<Jobs>();

        Jobs currentJob = null;

        for (Jobs jobs : jobs) {
            total = total + jobs.getBurstTime();
        }

        for (int i = 0; i < total; i++) {
            for (Jobs jobs : jobs) {
                if (jobs.getArrivalTime() == i) {
                    readyQueue.add(jobs);
                    if (currentJob == null) {
                        currentJob = jobs;
                    }
                }
            }

            for (Jobs jobsReady : readyQueue) {
                if (jobsReady.getBurstTime() < currentJob.getBurstTime() || currentJob.getBurstTime() == 0) {
                    currentJob = jobsReady;
                    currentJob.setExecuted(currentJob.getExecuted() + 1);
                    currentJob.setLastExecuted(i);
                }
            }
            
            try {
                System.out.println("Processo P" + currentJob.getId() + " está executando (Burst Time = " + currentJob.getBurstTime() + ")");
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SJF.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            currentJob.setBurstTime(currentJob.getBurstTime() - 1);

            if (currentJob.getBurstTime() == 0) {
                for (int j = 0; j < readyQueue.size(); j++) {
                    if (readyQueue.get(j).getId() == currentJob.getId()) {
                        readyQueue.remove(j);
                    }
                }
                executedJobs.add(currentJob);
            } 
        }

        for (Jobs executedJob : executedJobs) {
            this.totalTime = this.totalTime + executedJob.getLastExecuted() - executedJob.getArrivalTime() - (executedJob.getExecuted()-1);
        }
        
        this.avgTime = this.totalTime/this.totalJobs;
        System.out.println("TM = " + avgTime + " segundos");
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
}
