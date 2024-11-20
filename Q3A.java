import java.lang.management.*;
import java.util.*;

public class Q3A {
    
    // Resource1 and Resource2 (simulating shared resources)
    static class Resource1 {}
    static class Resource2 {}

    // Create two resources
    static final Resource1 resource1 = new Resource1();
    static final Resource2 resource2 = new Resource2();
    
    // Thread 1 locks Resource1 and then tries to lock Resource2
    static class Thread1 extends Thread {
        public void run() {
            synchronized (resource1) {
                System.out.println("Thread 1: Locked Resource1");
                
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                synchronized (resource2) {
                    System.out.println("Thread 1: Locked Resource2");
                }
            }
        }
    }

    // Thread 2 locks Resource2 and then tries to lock Resource1
    static class Thread2 extends Thread {
        public void run() {
            synchronized (resource2) {
                System.out.println("Thread 2: Locked Resource2");
                
                try { Thread.sleep(100); } catch (InterruptedException e) {}
                
                synchronized (resource1) {
                    System.out.println("Thread 2: Locked Resource1");
                }
            }
        }
    }

    // Method to detect deadlock using ThreadMXBean
    public static void detectDeadlock() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        
        // Get a list of all thread IDs
        long[] deadlockedThreadIds = threadMXBean.findDeadlockedThreads();
        
        if (deadlockedThreadIds != null && deadlockedThreadIds.length > 0) {
            // Deadlock detected, print information and terminate program gracefully
            System.out.println("Deadlock detected! The following threads are deadlocked:");
            for (long threadId : deadlockedThreadIds) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(threadId);
                System.out.println("Thread " + threadInfo.getThreadName() + " is deadlocked");
            }
            System.exit(0);  // Gracefully terminate the program
        }
    }

    public static void main(String[] args) {
        // Create and start threads
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        
        t1.start();
        t2.start();
        
        // Monitor and detect deadlock dynamically
        while (true) {
            try {
                Thread.sleep(500);  // Sleep for a while before checking for deadlock
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            detectDeadlock();
        }
    }
}
