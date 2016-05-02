package future;

public class Client {
    public Data request(final String queryStr) {
    	 final FutureData future = new FutureData();
    	// RealData的构建很慢
    	 new Thread(){
    		 public void run() {
                 RealData realdata = new RealData(queryStr);
                 future.setRealdata(realdata);
    		 };
    		 
    	 }.start();
		return future;
    }
}
