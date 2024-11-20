import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Q3B {

    // Shared ArrayList for threads to operate on
    private static List<Integer> sharedList = new ArrayList<>(); 

    // Thread 1: Iterates through the list and prints elements
    static class Thread1 extends Thread {
        public void run() {
            try {
                // Iterate through the list and print each element
                for (Integer item : sharedList) {
                    System.out.println("Thread 1: " + item);
                    Thread.sleep(100);  // Sleep to allow Thread 2 to add elements concurrently
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Thread 2: Adds elements to the list concurrently
    static class Thread2 extends Thread {
        public void run() {
            try {
                // Add elements to the list
                for (int i = 0; i < 5; i++) {
                    sharedList.add(i);
                    System.out.println("Thread 2 added: " + i);
                    Thread.sleep(50);  // Sleep to allow Thread 1 to iterate concurrently
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // Demonstrating the issue with a standard ArrayList
        System.out.println("Using standard ArrayList (will cause ConcurrentModificationException):");
        sharedList = new ArrayList<>(); // Reinitialize for the standard ArrayList case

        // Initial elements
        sharedList.add(0);
        sharedList.add(1);
        sharedList.add(2);
        sharedList.add(3);
        sharedList.add(4);

        Thread t1 = new Thread1();
        Thread t2 = new Thread2();

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Resolution with CopyOnWriteArrayList
        System.out.println("\nUsing CopyOnWriteArrayList (no ConcurrentModificationException):");
        sharedList = new CopyOnWriteArrayList<>(); // Using CopyOnWriteArrayList

        // Initial elements
        sharedList.add(0);
        sharedList.add(1);
        sharedList.add(2);
        sharedList.add(3);
        sharedList.add(4);

        t1 = new Thread1();
        t2 = new Thread2();

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
