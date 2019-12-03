package blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
        类作用：演示thread的生产者和消费者模式。
        （1）通过平衡生产者的生产能力和消费者的消费能力来提升整个系统的运行效率，
        这是生产者消费者模型最重要的作用。
        （2）解耦，这是生产者消费者模型附带的作用，
         解耦意味着生产者和消费者之间的联系少，联系越少越可以独自发展而不需要收到相互的制约。
 */
public class testMain {

	public static void main(String[] args) {
	      
		BlockingQueue<Integer> queue=new ArrayBlockingQueue<Integer>(100);   //队列的长度为100
		Producer producer = new  Producer(queue);
		Consume consume1 = new Consume(queue);
		Consume consume2 = new Consume(queue);
		
		
		//启动一个新的线程需要使用start()方法，而不是run()方法，后者只能启动原有的线程
		new Thread(producer).start();
		new Thread(consume1).start();
		new Thread(consume2).start();

	}

}
