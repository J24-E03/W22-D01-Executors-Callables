package org.dcistudent;

import java.util.List;
import java.util.concurrent.*;

public class Main {
  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    Main.task1();
    Main.task2();
    Main.task3();
  }

  private static void task1() {
    System.out.printf("%s%n", "### Labs - Part 1");

    try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
      Runnable fixedThreadPool1 = new FixedThreadPool(1);
      Runnable fixedThreadPool2 = new FixedThreadPool(2);
      Runnable fixedThreadPool3 = new FixedThreadPool(3);
      Runnable fixedThreadPool4 = new FixedThreadPool(4);
      Runnable fixedThreadPool5 = new FixedThreadPool(5);
      Runnable fixedThreadPool6 = new FixedThreadPool(6);
      Runnable fixedThreadPool7 = new FixedThreadPool(7);

      executor.submit(fixedThreadPool1);
      executor.submit(fixedThreadPool2);
      executor.submit(fixedThreadPool3);
      executor.submit(fixedThreadPool4);
      executor.submit(fixedThreadPool5);
      executor.submit(fixedThreadPool6);
      executor.submit(fixedThreadPool7);

      executor.shutdown();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void task2() {
    System.out.printf("%n%s%n", "### Labs - Part 2");

    try (ExecutorService executor = Executors.newFixedThreadPool(4)) {
      List<Callable<Integer>> tasks = List.of(
          new SquareCalculator(2),
          new SquareCalculator(3),
          new SquareCalculator(5),
          new SquareCalculator(7),
          new SquareCalculator(9),
          new SquareCalculator(10)
      );

      List<Future<Integer>> futures = executor.invokeAll(tasks);

      for (Future<Integer> future : futures) {
        try {
          Integer result = future.get(1, TimeUnit.SECONDS); // can throw a TimeoutException
          System.out.println("Result: " + result);
        } catch (TimeoutException e) {
          System.err.println("Computation timed out.");
          future.cancel(true);
        } catch (ExecutionException e) {
          System.err.println("Computation failed: " + e.getCause());
        }
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Part 3: Using invokeAll() and invokeAny()
   * Task 3: Execute Multiple Tasks and Retrieve Results
   * Create three Callable tasks that return different messages after a random delay.
   * Use invokeAll() to execute all tasks and retrieve their results.
   * Use invokeAny() to return the first completed task.
   */
  private static void task3() {
    System.out.printf("%n%s%n", "### Labs - Part 3");

    try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
      List<Callable<Integer>> tasks = List.of(
          () -> 1,
          () -> 2,
          () -> 3,
          () -> 4,
          () -> 5
      );

      executor.invokeAll(tasks).forEach(future -> {
        try {
          System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
          e.printStackTrace();
          Thread.currentThread().interrupt();
        }
      });

      Integer result = executor.invokeAny(tasks);
      System.out.println("First completed task: " + result);

      executor.shutdown();
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }
}