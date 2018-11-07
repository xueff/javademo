package javabase.thread.thread.concurrent;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/9/2111:43
 */
public class FutureMore implements Runnable {
    static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            final int index = i;
            cachedThreadPool.execute(new Runnable() {

                @Override
                public void run() {

                    System.out.println(Thread.currentThread().getName()+":"+index);
                    try {
                        Thread.sleep(index * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+"=ok:"+index);
                }
            });
        }
    }
}

class main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureMore t = new FutureMore();
        FutureMore t1 = new FutureMore();
        FutureMore t2 = new FutureMore();
        FutureMore t3 = new FutureMore();
        FutureMore t4 = new FutureMore();
        FutureMore t5 = new FutureMore();
        FutureMore t6 = new FutureMore();
        FutureMore t7 = new FutureMore();
        new Thread(t).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();
        new Thread(t5).start();
        new Thread(t6).start();
        new Thread(t7).start();

        boolean isShut;
        for(;;){
            isShut = FutureMore.cachedThreadPool.isShutdown();
            System.out.println(new Date());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(isShut)
                break;
        }

    }
}
