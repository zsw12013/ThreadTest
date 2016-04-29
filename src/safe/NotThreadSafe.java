package safe;


public class NotThreadSafe {
	StringBuilder builder = new StringBuilder();
	 //如果两个线程同时调用同一个NotThreadSafe实例上的add()方法，就会有竞态条件问题。 
	public void add(String text) {
		this.builder.append(text);
	}

	public static void main(String[] args) {
		
		//线程不安的
		NotThreadSafe sharedInstance = new NotThreadSafe();
		new Thread(new MyRunnable(sharedInstance)).start();
		new Thread(new MyRunnable(sharedInstance)).start();
		//线程安全的
		//new Thread(new MyRunnable(new NotThreadSafe())).start();
		//new Thread(new MyRunnable(new NotThreadSafe())).start();
	}
}
	class MyRunnable implements Runnable {
		NotThreadSafe instance = null;
		
		public MyRunnable(NotThreadSafe instance) {
			this.instance = instance;
		}
         
		@Override
		public void run() {
			this.instance.add("some text");
		}
	}
