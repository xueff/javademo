package javabase.base.datastructure;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue {
    private static BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>(10);
    private static BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(10);


    public static void main(String[] args) {
        try {
            linkedBlockingQueue.put("qqq");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(linkedBlockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
