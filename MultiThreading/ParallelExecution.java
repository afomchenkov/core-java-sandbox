package MultiThreading;

class Worker extends Thread {
    private int taskId;

    public Worker(int taskId) {
        this.taskId = taskId;
    }

    public void run() {
        System.out.println("Task " + taskId + " started by " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task " + taskId + " completed by " + Thread.currentThread().getName());
    }
}


/**
 * - Ensures that all worker threads finish before the program continues.
 * - Helps in synchronizing multiple parallel tasks.
 * - Prevents the main thread from finishing before dependent work is done.
 */
public class ParallelExecution {
    public static void main(String[] args) {
        Thread t1 = new Worker(1);
        Thread t2 = new Worker(2);
        Thread t3 = new Worker(3);

        t1.start();
        t2.start();
        t3.start();

        try {
            // Main thread waits for all worker threads to finish
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All tasks completed. Main thread resumes.");
    }
}
