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



class TestFuture {
    public static void main(String[] args) {
        //两个线程的线程池
        ExecutorService executor= Executors.newFixedThreadPool(2);
        //小红买酒任务，这里的future2代表的是小红未来发生的操作，返回小红买东西这个操作的结果
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(()-> {
            System.out.println("爸：小红你去买瓶酒！");
            try {
                System.out.println("小红出去买酒了，女孩子跑的比较慢，估计5s后才会回来...");
                Thread.sleep(5000);
                return "我买回来了！";
            } catch (InterruptedException e) {
                System.err.println("小红路上遭遇了不测");
                return "来世再见！";
            }
        },executor);

        //小明买烟任务，这里的future1代表的是小明未来买东西会发生的事，返回值是小明买东西的结果
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(()->{
            System.out.println("爸：小明你去买包烟！");
            try {
                System.out.println("小明出去买烟了，可能要3s后回来...");
                Thread.sleep(3000);
                return "我买回来了!";
            } catch (InterruptedException e) {
                System.out.println("小明路上遭遇了不测！");
                return "这是我托人带来的口信，我已经不在了。";
            }
        },executor);

        //获取小红买酒结果，从小红的操作中获取结果，把结果打印
        future2.thenAccept((e)->{System.out.println("小红说："+e);});
        //获取小明买烟的结果
        future1.thenAccept((e)->{System.out.println("小明说："+e);});

        System.out.println("爸：loading......");
        System.out.println("爸:我觉得无聊甚至去了趟厕所。");
        System.out.println("爸：loading......");
    }
}
