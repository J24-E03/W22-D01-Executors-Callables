import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        task1();
        System.out.println();

        task2And4();
        System.out.println();

        task3();
    }

    private static void task1() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            for (int i = 0; i < 7; i++) {
                executorService.execute(new WorkerTask(i + 1));
            }
        }
    }

    private static void task2And4() {
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {
            for (int i = 3; i < 10; i += 3) {
                System.out.println("Square of " + i + ": " + executorService.submit(new SquareCalculator(i)).get(2, TimeUnit.SECONDS));
            }
        } catch (ExecutionException | InterruptedException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private static void task3() {
        List<Callable<String>> callableList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            callableList.add(new ReturnMessage(i + 1));
        }

        try (ExecutorService executorService = Executors.newFixedThreadPool(20)) {
            executorService.invokeAll(callableList).forEach(stringFuture -> {
                try {
                    System.out.println(stringFuture.get());
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });

            System.out.println("First completed result: " + executorService.invokeAny(callableList));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

record WorkerTask(int task) implements Runnable {
    @Override
    public void run() {
        System.out.println("Task " + this.task + " executed by " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

record SquareCalculator(int number) implements Callable<Integer> {
    @Override
    public Integer call() {
        return this.number * this.number;
    }
}

record ReturnMessage(int taskNumber) implements Callable<String> {
    @Override
    public String call() {
        try {
            Thread.sleep((long) (Math.random() * 5000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Task " + this.taskNumber + " completed!";
    }
}
