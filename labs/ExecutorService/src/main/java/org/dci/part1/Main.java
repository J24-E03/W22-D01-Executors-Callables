package org.dci.part1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 7; i++) {
            executorService.execute(new WorkerTask("Task " + (i + 1)));
        }

        executorService.shutdown();

    }
}
