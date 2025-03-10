import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkerTask implements Runnable {
    int taskCount;

    public WorkerTask(int taskCount) {
        this.taskCount = taskCount;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ ": is executing task " + taskCount);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 7; i++) { // Submit 7 tasks to the executor
            executor.execute(new WorkerTask(i));
        }
        executor.shutdown();
    }
}
