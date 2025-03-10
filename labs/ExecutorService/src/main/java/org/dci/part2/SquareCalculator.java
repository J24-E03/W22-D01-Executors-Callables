package org.dci.part2;

import lombok.AllArgsConstructor;


import java.util.concurrent.Callable;

@AllArgsConstructor
public class SquareCalculator implements Callable<Integer> {
    private Integer number;

    @Override
    public Integer call() throws Exception {
        return (int) Math.pow(number, 2);
    }
}
