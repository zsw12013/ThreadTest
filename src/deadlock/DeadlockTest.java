package deadlock;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DeadlockTest {
   public static String th1 = "Dao";
   public static String th2 = "Cha";
   public static SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd hh:mm:ss");

   public static void main(String[] args) {
      XiaoQiang1 XiaoQiang1 = new XiaoQiang1();
      new Thread(XiaoQiang1).start();
      XiaoMing1 XiaoMing1 = new XiaoMing1();
      new Thread(XiaoMing1).start();
   }
}

class XiaoQiang1 implements Runnable{
    public void run() {
      try {
         System.out.println(DeadlockTest.sdf.format(new Date()).toString() + " XiaoQiang1 starts asking for Dao and Cha......");
         while(true){
            synchronized (DeadlockTest.th1) {
               System.out.println(DeadlockTest.sdf.format(new Date()).toString() + " XiaoQiang1 gets Dao!");
               Thread.sleep(3000); // 此处休眠不释放锁(模拟对资源Dao的占用);
               synchronized (DeadlockTest.th2) {
                  System.out.println(DeadlockTest.sdf.format(new Date()).toString() + " XiaoQiang1 gets Cha!");
                  Thread.sleep(10 * 1000); // 线程休眠不释放锁，让线程占用对象锁的时间久一点;
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}

class XiaoMing1 implements Runnable{
   public void run() {
      try {
         System.out.println(DeadlockTest.sdf.format(new Date()).toString() + " XiaoMing1 starts asking for Dao and Cha......");
         while(true){
            synchronized (DeadlockTest.th2) {
               System.out.println(DeadlockTest.sdf.format(new Date()).toString() + " XiaoMing1 gets Cha!");
               Thread.sleep(3000); // /  此处休眠不释放锁(模拟对资源Cha的占用);
               synchronized (DeadlockTest.th1) {
                  System.out.println(DeadlockTest.sdf.format(new Date()).toString() + " XiaoMing1 gets Dao!");
                  Thread.sleep(10 * 1000); // 线程休眠不释放锁，让线程占用对象锁的时间久一点;
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}