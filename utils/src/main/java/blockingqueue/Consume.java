package blockingqueue;

import java.util.concurrent.BlockingQueue;


public class Consume   implements  Runnable{
   
	
	private  BlockingQueue<Integer>  consume;
	//提供一个有参构造器
	public Consume(BlockingQueue<Integer>  p ) {
		super();
		this.consume = p;
	}
	
	
	@Override
	public void run() {
		
		try {
			while (true) {
			   Thread.sleep(2000);  //消费者演示两秒
			   test(consume.take());   //获取从消费者中取出的东西
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public void   test(Integer   p) {
		System.out.println("当前线程的名字："+Thread.currentThread().getName()+"当前线程的id："+
	       Thread.currentThread().getId()
				+"从消费者中拿到的数值："+p);
	}
	

}
