package org.dci.part3;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        invokeAll();
        invokeAny();
    }

    private static void invokeAll() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = Arrays.asList(
                new MessageGenerator("Task 1"),
                new MessageGenerator("Task 2"),
                new MessageGenerator("Task 3")
        );

        try {
            List<Future<String>> messages = executorService.invokeAll(tasks);

            for (Future<String> future : messages) {
                System.out.println(future.get());
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }

    private static void invokeAny() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = Arrays.asList(
                new MessageGenerator("Task 1"),
                new MessageGenerator("Task 2"),
                new MessageGenerator("Task 3")
        );

        try {
            String message = executorService.invokeAny(tasks);
            System.out.println("First completed result: " + message);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
    }
}
