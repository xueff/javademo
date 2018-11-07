package javabase.thread.future;

import java.util.concurrent.*;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/9/2511:43
 */
public class JavaFuture {
    public static void main(String[] args) throws Throwable, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        // Future代表了线程执行完以后的结果，可以通过future获得执行的结果
        // 但是jdk1.8之前的Future有点鸡肋，并不能实现真正的异步，需要阻塞的获取结果，或者不断的轮询
        // 通常我们希望当线程执行完一些耗时的任务后，能够自动的通知我们结果，很遗憾这在原生jdk1.8之前
        // 是不支持的，但是我们可以通过第三方的库实现真正的异步回调
        Future<String> f = executor.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("task started!");
                Thread.sleep(5000);
                System.out.println("task finished!");
                return "hello";
            }
        });

        System.out.println("主线程未阻塞哦！");
        //此处阻塞main线程
        getFuture(f);

        System.out.println("end");








    }

    private static void getFuture(Future<String> f) throws ExecutionException, InterruptedException {
        System.out.println("method--getFuture");
        for (;;){
            if( f.isDone()){
                System.out.println("结果"+f.get());
                break;
            }else{
                System.out.println("。。。");
            }

        }
    }

}
