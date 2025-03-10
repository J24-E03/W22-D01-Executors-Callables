package org.dci.part3;

import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;

@AllArgsConstructor
public class MessageGenerator implements Callable<String> {
    private String taskName;

    @Override
    public String call() throws Exception {
        Thread.sleep((long) (1000 + Math.random()*2000));
        return taskName + " completed";
    }
}
