package blockingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;


public class Producer   implements  Runnable{
    
	private  BlockingQueue<Integer>   producer;
	
	//提供一个有参构造器
	 public Producer(BlockingQueue q){
	       this.producer=q;
	 }
	 
	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(1000);  //休眠一秒,然后线程执行生产者的方法
			    producer.put(produce());   //将值放入到map中
			}
			
		} catch (Exception e) {
			
		}
		
	}
	
	public   Integer   produce() {
		int i = new Random().nextInt(1000);
		 System.out.println("当面线程名字："+Thread.currentThread().getName()+"当前线程的id："+
			       Thread.currentThread().getId()
				 +"随机数是："+i);
		return  i;
	}

	

}
