package javabase.thread;

import javabase.ramdon.RamdonStudy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
/**
 *
 * @author Snailclimb
 * @date 2018年9月30日
 * @Description: 需要一次性拿一个许可的情况
 */
public class SemaphoreExample {
    // 请求的数量
    private static final int threadCount = 1000000000;
    // 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
    static ExecutorService threadPool = Executors.newFixedThreadPool(50);
    public static void main(String[] args) throws InterruptedException {

        // 一次只能允许执行的线程数量。
        //final Semaphore semaphore = new Semaphore(30);

        for (int i = 0; i < threadCount; i++) {
            final int threadnum = i;
            Thread.sleep(500);
            threadPool.execute(() -> {// Lambda 表达式的运用
                try {
                    System.out.print(threadnum);
                    List<byte[]> list = new ArrayList<>();
                    for(int j=0;j<30;j++) {
                        Thread.sleep(1000 + RamdonStudy.getRamdonInt(10000));
                        // semaphore.acquire();// 获取一个许可，所以可运行线程数量为20/1=20
                        list.add(new byte[1024 * 2024]);
                    }
                    Thread.sleep(1000 + RamdonStudy.getRamdonInt(10000));
                    //test(threadnum);
                  //  semaphore.release();// 释放一个许可
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            });
            System.out.println("I`M:"+i);
        }
        threadPool.shutdown();
        System.out.println("finish");
    }

    public static void test(int threadnum) throws InterruptedException {
        Thread.sleep(1000);// 模拟请求的耗时操作
        System.out.println("threadnum:" + threadnum);
        Thread.sleep(10000);// 模拟请求的耗时操作
    }
}
