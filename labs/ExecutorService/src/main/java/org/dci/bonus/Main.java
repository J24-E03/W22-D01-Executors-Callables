package org.dci.bonus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<Integer>> results = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            results.add(executorService.submit(new SquareCalculator(i + 3)));
        }

        for (int i = 0; i < results.size(); i++) {
            try {
                Integer result = results.get(i).get(2, TimeUnit.SECONDS);
                System.out.println("Square of " + (i + 3) + ": " + result);
            } catch (TimeoutException e) {
                System.out.println("Task " + (i + 3) + " timed out!");
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Error executing task " + (i + 3) + ": " + e.getMessage());
            }
        }

        executorService.shutdown();
    }
}