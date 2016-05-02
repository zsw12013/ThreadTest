package future.jdk_impl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
    	 //构造FutureTask
		FutureTask<String> future = new FutureTask<>(new RealData("a"));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		//执行FutureTask，相当于上例中的 client.request("a") 发送请求
        //在这里开启线程进行RealData的call()执行
		executor.submit(future);
        System.out.println("请求完毕");
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //相当于上例中得data.getContent()，取得call()方法的返回值
        //如果此时call()方法没有执行完成，则依然会等待
        System.out.println("数据 = " + future.get());

 	}
}
