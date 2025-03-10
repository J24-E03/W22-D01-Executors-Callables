package org.dcistudent;

import java.time.Duration;

public class FixedThreadPool implements Runnable {
  private final int id;

  public FixedThreadPool(int id) {
    this.id = id;
  }

  @Override
  public void run() {
    Thread.currentThread().setName("FixedThreadPool ID: " + id);
    System.out.println(Thread.currentThread().getName() + " is running.");
    try {
      Thread.sleep(Duration.ofSeconds(1));
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
