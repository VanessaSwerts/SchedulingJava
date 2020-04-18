/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scheduling;

import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.ExecuteView;

/**
 *
 * @author Alexa
 */
public class SJF extends Thread {

    private List<Jobs> jobs = new ArrayList<Jobs>();
    public double avgTime = 0;
    private double totalJobs = 0;
    private double totalTime = 0;

    private ExecuteView executeView;

    public SJF(List<Jobs> jobs, ExecuteView executeView) {
        this.jobs = jobs;
        this.totalJobs = jobs.size();
        this.executeView = executeView;

        Collections.sort(this.jobs, firstArrived);
    }

    @Override
    public void run() {
        try {
            double total = 0;
            List<Jobs> executedJobs = new ArrayList<Jobs>();
            List<Jobs> readyQueue = new ArrayList<Jobs>();

            Jobs currentJob = null;

            for (Jobs jobs : jobs) {
                total = total + jobs.getBurstTime();
            }

            for (int i = this.jobs.get(0).getArrivalTime(); i < total; i++) {
                for (Jobs job : jobs) {
                    if (job.getArrivalTime() == i) {
                        readyQueue.add(job);
                        if (currentJob == null) {
                            currentJob = job;
                            //System.out.println(currentJob.getId());
                        }
                    }
                }

                for (Jobs jobReady : readyQueue) {
                    if (jobReady.getBurstTime() < currentJob.getBurstTime() || currentJob.getBurstTime() == 0) {
                        currentJob = jobReady;
                        currentJob.setExecuted(currentJob.getExecuted() + 1);
                        currentJob.setLastExecuted(i);
                    }
                }

                this.print(Color.black, "Processo P" + currentJob.getId() + " está executando (Burst Time = " + currentJob.getBurstTime() + ")");

                currentJob.setBurstTime(currentJob.getBurstTime() - 1);

                if (currentJob.getBurstTime() == 0) {
                    this.print(Color.red, "Processo P" + currentJob.getId() + " foi finalizado (Burst Time = 0 segundos)");

                    for (int j = 0; j < readyQueue.size(); j++) {
                        if (readyQueue.get(j).getId() == currentJob.getId()) {
                            readyQueue.remove(j);
                        }
                    }
                    executedJobs.add(currentJob);
                }
            }

            for (Jobs executedJob : executedJobs) {
                this.totalTime = this.totalTime + executedJob.getLastExecuted() - executedJob.getArrivalTime() - (executedJob.getExecuted() - 1);
            }

            this.avgTime = this.totalTime / this.totalJobs;
            DecimalFormat df2 = new DecimalFormat("#.##");
            df2.setRoundingMode(RoundingMode.DOWN);
            this.executeView.getjLabel4().setText("TM = " + df2.format(this.avgTime) + " segundos");
            this.executeView.getScrollPane().getVerticalScrollBar().removeAdjustmentListener(this.executeView.getListener());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    private void print(Color color, String message) {
        JLabel label = new JLabel();
        label.setText(message);
        label.setForeground(color);
        this.executeView.getResultPane().add(label);
        this.executeView.getResultPane().revalidate();
        this.executeView.getResultPane().repaint();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(FCFS.class.getName()).log(Level.SEVERE, null, ex);
        }
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
