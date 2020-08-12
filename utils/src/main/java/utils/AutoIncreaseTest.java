package utils;

import java.io.File;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


/**
 * 自增判断
 */
public class AutoIncreaseTest {


	public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Queue<Integer> queue=new ArrayBlockingQueue <Integer>(1000000);

        try {
           for(int i=0;i<=10000;i++){
               final int t = i;
                executorService.execute(()->{
                    queue.add(t);
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }

//        executorService.shutdown();
        try {
            Thread.sleep(1000);

            System.out.println(queue.size());
            int aesc = 0;
            int desc = 0;
            int lasesum = 0;
            while (queue.size()>0){
                int sum = 0;
                for(int i=0;i<2&&queue.size()>0;i++){
                    sum += queue.poll();
                }
                if(lasesum<=sum/2) {

                    aesc +=1;
                }else {
                    desc +=1;
                }
                lasesum = sum/2;
            }
            System.out.println("aesc:"+aesc);
            System.out.println("desc:"+desc);
            System.out.println("size:"+queue.size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }


}
