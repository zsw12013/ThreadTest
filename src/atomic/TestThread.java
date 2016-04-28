package atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {
	public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        Lock lock = new ReentrantLock(false);
        Runnable t1 = new MyTRunnable("张三", 2000, lock);
        Runnable t2 = new MyTRunnable("李四", 3600, lock);
        Runnable t3 = new MyTRunnable("王五", 2700, lock);
        Runnable t4 = new MyTRunnable("老张", 600, lock);
        Runnable t5 = new MyTRunnable("老牛", 1300, lock);
        Runnable t6 = new MyTRunnable("胖子", 800, lock);
        // 执行各个线程
        pool.execute(t1);
        pool.execute(t2);
        pool.execute(t3);
        pool.execute(t4);
        pool.execute(t5);
        pool.execute(t6);
        // 关闭线程池
        pool.shutdown();
    }
}

class MyTRunnable implements Runnable {
    // 原子量，每个线程都可以自由操作
    private static AtomicLong aLong = new AtomicLong(10000);
    private String name; // 操作人
    private int x; // 操作数额
    private Lock lock;

    MyTRunnable(String name, int x, Lock lock) {
        this.name = name;
        this.x = x;
        this.lock = lock;
    }

    public void run() {
        lock.lock();
        System.out.println(name + "执行了" + x + "，当前余额：" + aLong.addAndGet(x));
        lock.unlock();
    }

}
	