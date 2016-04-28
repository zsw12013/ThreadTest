package queue.Blocking;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        for (int product = 1; product <= 10; product++) {
            try {
                Thread.sleep((int) Math.random() * 3000);
                queue.put(product);
                System.out.println("Producer 生产： " + product);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}