package threadlocal;
public class Test {
    public static void main(String[] args) {
        SeqGen sn = new SeqGen();
        TestThread tt1 = new TestThread(sn);
        TestThread tt2 = new TestThread(sn);
        TestThread tt3 = new TestThread(sn);
        Thread t1 = new Thread(tt1);
        Thread t2 = new Thread(tt2);
        Thread t3 = new Thread(tt3);
        t1.start();
        t2.start();
        t3.start();
    }
}