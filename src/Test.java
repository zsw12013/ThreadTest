import java.util.concurrent.atomic.AtomicInteger;

public class Test {
	private static AtomicInteger counter = new AtomicInteger();
	public static int getNextId() {
	    return counter.incrementAndGet();
	}
  public static void main(String[] args) {
	  for (;;) {
		 
	}
 }
}
