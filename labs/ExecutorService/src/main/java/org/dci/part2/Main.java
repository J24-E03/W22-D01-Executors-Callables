package org.dci.part2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<Integer>> results = new ArrayList<>();
        int[] numbers = {4, 7, 3};

        for (int number : numbers) {
            results.add(executorService.submit(new SquareCalculator(number)));
        }

        for (int i = 0; i < results.size(); i++) {
            try {
                System.out.println("Square of " + numbers[i] + ": " + results.get(i).get());
            } catch (Exception e) {
                System.err.println("Error computing square: " + e.getMessage());
            }
        }

        executorService.shutdown();
    }
}
