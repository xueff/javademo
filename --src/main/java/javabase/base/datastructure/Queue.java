package javabase.base.datastructure;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue {
    private BlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>(10);
    private BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(10);
}
