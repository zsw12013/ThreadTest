package queue.Blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//基于阻塞队列BlockingQueue的生产者、消费者模式
public class BlockingQueueDemo {
    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(1);
        Thread producerThread = new Thread(
                   new Producer(queue)
        );
        Thread consumerThread = new Thread(
                 new Consumer(queue)
         );
        producerThread.start();
        consumerThread.start();
    }
}