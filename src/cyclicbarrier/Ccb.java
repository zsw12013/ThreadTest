package cyclicbarrier;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Ccb {
   public static void main(String[] args) {
	   ExecutorService service = Executors.newCachedThreadPool();
	   final CyclicBarrier cb = new CyclicBarrier(3);
	   for (int i = 0; i < 3; i++) {
		    Runnable runnable = new Runnable() {
		        public void run() {
		            try {
 		                Thread.sleep((long) (Math.random() * 10000));
		                System.out.println("线程"+ Thread.currentThread().getName()
		                        + "即将到达集合地点1，当前已有" + cb.getNumberWaiting()
		                        + "个已经到达，正在等候");
		                cb.await();

		                Thread.sleep((long) (Math.random() * 10000));
		                System.out.println("线程"
		                        + Thread.currentThread().getName()
		                        + "即将到达集合地点2，当前已有" + cb.getNumberWaiting()
		                        + "个已经到达，正在等候");
		                cb.await();
		                Thread.sleep((long) (Math.random() * 10000));
		                System.out.println("线程"
		                        + Thread.currentThread().getName()
		                        + "即将到达集合地点3，当前已有" + cb.getNumberWaiting()
		                        + "个已经到达，正在等候");
		                cb.await();
		            } catch (Exception e) {
		                e.printStackTrace();
		            }
		        }
		    };
		    service.execute(runnable);

		}
	   
	   
	   
	   System.out.println("111111111111111111111111111111111111111111");
		service.shutdown();
		
		
}
}
