package MultiThreading;

/**
 * When to use join()
 *
 * - When you need to ensure a thread completes before continuing (e.g., loading resources before processing).
 * - When you want to synchronize multiple threads in a controlled manner.
 * - When implementing parallel tasks and aggregating results after all threads finish.
 */

class MyThread extends Thread {
    public void run() {
        System.out.println(Thread.currentThread().getName() + " started.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " finished.");
    }
}


public class JoinExample {
    public static void main(String[] args) {
        Thread t1 = new MyThread();
        Thread t2 = new MyThread();

        t1.start();
        t2.start();

        try {
            t1.join(); // Main thread waits for t1 to finish
            t2.join(); // Main thread waits for t2 to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread resumes after t1 and t2 finish.");
    }
}
