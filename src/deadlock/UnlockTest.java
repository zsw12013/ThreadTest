package deadlock;
 import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
//http://www.jianshu.com/p/747c51ad2246
public class UnlockTest {
   public static String th1 = "Dao";
   public static String th2 = "Cha";
   public static final Semaphore s1 = new Semaphore(1);
   public static final Semaphore s2 = new Semaphore(1);
   public static SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd hh:mm:ss");

   public static void main(String[] args) {
      XiaoQiang xiaoQiang = new XiaoQiang();
      new Thread(xiaoQiang).start();
      XiaoMing xiaoMing = new XiaoMing();
      new Thread(xiaoMing).start();
   }
}

class XiaoQiang implements Runnable{
    public void run() {
      try {
         System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoQiang starts asking for Dao and Cha......");
         while(true){
            if(UnlockTest.s1.tryAcquire(1, TimeUnit.SECONDS)) {
               System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoQiang gets Dao!");
               Thread.sleep(1000); // 休眠1秒(模拟等待下一个餐具或者等待1秒再取);
               if(UnlockTest.s2.tryAcquire(1, TimeUnit.SECONDS)) {
                  System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoQiang gets Cha!");
                  System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoQiang happy eating!(30 seconds)");
                  Thread.sleep(30 * 1000); // 线程休眠30秒(模拟进餐);
               } else {
                   System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoMing fails to get Dao!");
               }
            } else {
                System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoMing fails to get Cha!");
            }
            System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoQiang releases Dao or Cha which has been obtained!");
            UnlockTest.s1.release(); // 释放一个许可，将可用的许可数加1(模拟交出Dao的占用权);;
            UnlockTest.s2.release(); // 释放一个许可，将可用的许可数加1(模拟交出Cha的占用权);;
            System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoQiang occupies nothing......waiting 10sec!");
            Thread.sleep(5 * 1000);//休眠5秒(模拟XiaoQiang交出餐具占用权后等待5秒再去获取餐具)。
            }
      } catch (Exception e) {
         e.printStackTrace();
      }    
   }
}

class XiaoMing implements Runnable{
   public void run() {
      try {
         System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoMing starts asking for Dao and Cha......");
         while(true){
             if(UnlockTest.s2.tryAcquire(1, TimeUnit.SECONDS)) {
               System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoMing gets Cha!");
               Thread.sleep(3000); // 休眠3秒(模拟等待下一个餐具或者等待3秒再取);
               if(UnlockTest.s1.tryAcquire(1, TimeUnit.SECONDS)) {

                  System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoMing gets Dao!");
                  System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoMing happy eating!(30 seconds)");
                  Thread.sleep(30 * 1000); // 线程休眠30秒，模拟进餐;
               } else {
                   System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoMing fails to get Dao!");
               }
            } else {
                System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoMing fails to get Cha!");
            }
             System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoMing releases Dao or Cha which has been obtained!");
             UnlockTest.s2.release(); // 释放一个许可，将可用的许可数加1(模拟交出Cha的占用权);;
             UnlockTest.s1.release(); // 释放一个许可，将可用的许可数加1(模拟交出Dao的占用权);;
             System.out.println(UnlockTest.sdf.format(new Date()).toString() + " XiaoMing occupies nothing......waiting 20sec!");
             Thread.sleep(20 * 1000);//休眠20秒(模拟XiaoMing交出餐具占用权后等待20秒再去获取餐具)。
             }
       } catch (Exception e) {
          e.printStackTrace();
       }    
   } 
}