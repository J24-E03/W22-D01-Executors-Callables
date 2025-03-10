package org.dci.bonus;

import lombok.AllArgsConstructor;

import java.util.concurrent.Callable;

@AllArgsConstructor
public class SquareCalculator implements Callable<Integer> {
    private Integer number;

    @Override
    public Integer call() throws Exception {
        Thread.sleep((long) (1000 + Math.random() * 2000));
        return (int) Math.pow(number, 2);
    }
}
