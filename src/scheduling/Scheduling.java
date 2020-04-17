package scheduling;

import java.util.ArrayList;
import java.util.List;

public class Scheduling {
    public static void main(String[] args) {
        
        // Ex 1 - FCFS
        List<Jobs> jobsQueue1 = new ArrayList<Jobs> ();
        Jobs job1 = new Jobs(1, 1, 10);
        Jobs job2 = new Jobs(2, 2, 1);
        Jobs job3 = new Jobs(3, 3, 2);
        Jobs job4 = new Jobs(4, 4, 1);
        Jobs job5 = new Jobs(5, 5, 5);
        
        jobsQueue1.add(job1);
        jobsQueue1.add(job2);
        jobsQueue1.add(job3);
        jobsQueue1.add(job4);
        jobsQueue1.add(job5);
        
        System.out.println("Teste com FCFS - Não Preemptivo");
        FCFS fcfs = new FCFS(jobsQueue1);
        fcfs.execute();
        
        // Ex 4 - SJF
        List<Jobs> jobsQueue2 = new ArrayList<Jobs> ();
        Jobs job6 = new Jobs(1, 0, 8);
        Jobs job7 = new Jobs(2, 1, 4);
        Jobs job8 = new Jobs(3, 2, 9);
        Jobs job9 = new Jobs(4, 3, 5);
        
        jobsQueue2.add(job6);
        jobsQueue2.add(job7);
        jobsQueue2.add(job8);
        jobsQueue2.add(job9);
        System.out.println("\nTeste com SJF - Preemptivo");
        SJF fjs = new SJF(jobsQueue2);
        fjs.execute();
    }

}
