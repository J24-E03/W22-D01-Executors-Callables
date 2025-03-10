import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SquareCalculator implements Callable<Integer> {
    int number;

    public SquareCalculator(int number) {
        this.number = number;
    }
    @Override
    public Integer call() throws Exception {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return number * number;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
       Future<Integer> future1 = executorService.submit(new SquareCalculator(4));
       Future<Integer> future2 = executorService.submit(new SquareCalculator(7));
       Future<Integer> future3 = executorService.submit(new SquareCalculator(3));
       try {
           System.out.println("Square of 4: " + future1.get()); //Retrieves and prints result
           System.out.println("Square of 7: " + future2.get());
           System.out.println("Square of 3: " + future3.get());
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           executorService.shutdown();
       }

    }
}
