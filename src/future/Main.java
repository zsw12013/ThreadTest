package future;

public class Main {
   public static void main(String[] args) {   
	   Client  client = new Client();
	   Data data = client.request("a");
       System.out.println("请求完毕");
           try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         //使用真实的数据
           System.out.println("数据 = " + data.getResult());
   }
}
