package queue.Blocking;
import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep((int) (Math.random() * 3000));
                System.out.println("Consumer 消费： "+ queue.take()); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}