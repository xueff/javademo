package javabase.thread.threadtest;

import net.http.httpclient.HttpsUtils;
import nsq.NsqClient;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTest implements Runnable{
    static AtomicInteger at = new AtomicInteger();
    static String str = "fc00a80001e0b94d880e6a0301020d00124b000dc6238708010c0000124b00198e32430800020000124b00163a572308010f0000124b0018db94110901010000124b0014d3a9510a01000000124b0014d3a9510901000000124b0014d3a9510801000000124b001659703c08010d0000124b001996823408010e0000124b0017298fff0b01000000124b0017298fff0a01000000124b0017298fff0901000000124b0017298fff08010000";
    static CountDownLatch count = new CountDownLatch(3000);
    static volatile long st = 0L;
    public static void main(String[] args) throws InterruptedException {
        NsqClient.getInstance();
        st = System.currentTimeMillis();
        System.out.println("开始..."+st);
            for(int i=1;i<=3;i++) {
                ThreadTest v = new ThreadTest();
                Thread t = new Thread(v);
                t.start();
                Thread.sleep(1);
            }
            System.out.println("count结果..."+ count.getCount());
            count.await();
            System.out.println("count结果..."+ count.getCount());
            Thread.sleep(1500);
            System.out.println("子线程已经执行完毕:"+(System.currentTimeMillis()-st));
            System.out.println("继续执行主线程");
            return;
    }



    @Override
    public void run() {
        try {
            Socket client = new Socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
            // 建立TCP连接，虚拟机的地址为192.168.194.129
            // Nginx监听的端口设置为8080
            client.connect(inetSocketAddress, 1000);
            String request = "POST /hander/it HTTP/1.1\r\n"+
                    "Host: 127.0.0.1:8080\r\n\r\n"+
                    "{\"hostMac\":\"28f366b6d6c7\",\"deviceId\":\"00124b0018d8454708\",\"cmdName\":\"tv_TurnOn\"}";
            PrintWriter pWriter = new PrintWriter(client.getOutputStream(),true);
            // 这里使用println()方法是因为 HTTP 协议在报头和报文之间，有一行空行
            // 如果少了这一行空行，服务器是无法解析报文的，会出现 400 错误
            pWriter.println(request);
            String tem;
            // 这里要注意二进制字节流转换为字符流编码要使用UTF-8
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream(), "utf-8"));
            while((tem = bufferedReader.readLine())!=null) {
                System.out.println(tem);
            }
            client.close();

            count.countDown();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error:"+Thread.currentThread().getName()+":"+(System.currentTimeMillis()-st));
            return;
        }
        //System.out.println(Thread.currentThread().getName()+":"+(System.currentTimeMillis()-st));
    }

    @Test
    public static void check(byte[] bytes){
        String str = new String(bytes);
        if(str.equals(st))
            at.addAndGet(1);
        System.out.println(at);
    }
}