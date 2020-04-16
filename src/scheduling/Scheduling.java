package scheduling;

import java.util.ArrayList;
import java.util.List;

public class Scheduling {
    public static void main(String[] args) {
        
        //Ex 1
        List<Jobs> jobsQueue = new ArrayList<Jobs> ();
        Jobs job1 = new Jobs(1, 0, 24);
        Jobs job2 = new Jobs(2, 1, 3);
        Jobs job3 = new Jobs(3, 2, 3);
        
        jobsQueue.add(job1);
        jobsQueue.add(job2);
        jobsQueue.add(job3);        
        
    }

}
