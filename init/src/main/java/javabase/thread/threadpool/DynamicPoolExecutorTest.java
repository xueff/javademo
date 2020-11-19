package javabase.thread.threadpool;

import java.util.concurrent.*;

/**
 * 动态调整线程池大小
 */
public class DynamicPoolExecutorTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,
                10,
                10l, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10000));

        int count = 0;
        while (true) {
            Thread.sleep(100l);
            for (int i = 0; i < 9; i++) {
                executor.execute(() -> {
                    try {
                        Thread.sleep(1000l);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("------------core:\t" + executor.getCorePoolSize() + "\tactive:\t" + executor.getActiveCount() + "\tmax:\t" + executor.getMaximumPoolSize());
                });
            }

            count++;
            if (count == 20) {
                executor.setCorePoolSize(2);
                executor.setMaximumPoolSize(2);
                System.out.println("----------------------------------------");
            }

            if (count == 100) {
                executor.shutdown();
                System.out.println("=============================================");
                break;
            }
        }

        Thread.currentThread().join();
    }

}
