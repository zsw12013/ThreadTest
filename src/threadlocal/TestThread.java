package threadlocal;
public class TestThread implements Runnable {
    private SeqGen sn;

    public TestThread(SeqGen sn) {
        this.sn = sn;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            String str = Thread.currentThread().getName() + "-->"+ sn.getNextNum();
            System.out.println(str);
        }
    }
}

