package MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Worker implements Runnable {
    private int taskId;

    public Worker(int taskId) {
        this.taskId = taskId;
    }

    @Override
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
 * - Thread Pooling: More efficient than creating new threads for every task.
 * - Better Resource Management: Prevents excessive thread creation, reducing memory usage.
 * - Automatic Thread Reuse: Reuses threads instead of creating/destroying them frequently.
 * - Graceful Shutdown Handling: shutdown() and awaitTermination() allow controlled thread stopping.
 */
public class ThreadPoolExample {
    public static void main(String[] args) {
        // Creating a fixed thread pool of 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 5; i++) {
            executor.submit(new Worker(i));
        }

        // Initiating shutdown after submitting tasks
        executor.shutdown();

        try {
            // Wait for all tasks to complete (max wait time: 10 seconds)
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // Force shutdown if tasks exceed time limit
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All tasks completed. Main thread resumes.");
    }
}
