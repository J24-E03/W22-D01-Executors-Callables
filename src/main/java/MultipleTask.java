import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class MultipleTask implements Callable<String> {
    String taskName;

    public MultipleTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(3000);
        return taskName  + " completed";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<String>> tasks = Arrays.asList(
                new MultipleTask("Task 1"),
                new MultipleTask("Task 2"),
                new MultipleTask("Task 3")
        );

            try {
                List<Future<String>> results = executorService.invokeAll(tasks); //Use invokeAll() to execute all tasks and retrieve their results.
                for (Future<String> result : results) {
                    System.out.println(result.get());
                }
                String firstCall = executorService.invokeAny(tasks); //Use invokeAny() to return the first completed task.
                System.out.println("First completed task : " + firstCall);
            } catch (InterruptedException e) {
                e.printStackTrace();
             } finally {
                executorService.shutdown();
            }

        }
    }

