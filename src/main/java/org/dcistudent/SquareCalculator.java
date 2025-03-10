package org.dcistudent;

import java.util.concurrent.Callable;

public class SquareCalculator implements Callable<Integer> {
  private final int num;

  public SquareCalculator(int num) {
    this.num = num;
  }

  @Override
  public Integer call() {
    System.out.printf("Square of %d is %d%n", num, num * num);

    return num * num;
  }
}
