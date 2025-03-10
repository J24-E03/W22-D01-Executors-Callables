package org.dci.part1;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WorkerTask implements Runnable{
    private String taskName;

    @Override
    public void run() {
        System.out.println(taskName + " executed by " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
