package javabase.thread.future;

import com.google.common.util.concurrent.*;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/9/2511:46
 */
public class GuavaFuture {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(1);

        // 使用guava提供的MoreExecutors工具类包装原始的线程池
        ListeningExecutorService listeningExecutor = MoreExecutors.listeningDecorator(executor);
        //向线程池中提交一个任务后，将会返回一个可监听的Future，该Future由Guava框架提供

            ListenableFuture<String> lf = listeningExecutor.submit(new Callable<String>() {

                @Override
                public String call() throws Exception {
                    Random r = new Random();
                    int flag = r.nextInt(100);
                    System.out.println("task started!"+flag);
                    //模拟耗时操作
                    Thread.sleep(3000);
                    System.out.println("task finished!"+flag);
                    return "hello";
                }
            });
            //添加回调，回调由executor中的线程触发，但也可以指定一个新的线程
            Futures.addCallback(lf, new FutureCallback<String>() {

                //耗时任务执行失败后回调该方法
                @Override
                public void onFailure(Throwable t) {
                    System.out.println("failure");
                }

                //耗时任务执行成功后回调该方法
                @Override
                public void onSuccess(String s) {
                    System.out.println("success " + s);
                }
            });
        //主线程可以继续做其他的工作
        System.out.println("main thread is running");
    }
}
