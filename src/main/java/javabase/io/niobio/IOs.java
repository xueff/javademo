package javabase.io.niobio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOs {

    public void serverBio(){
        ServerSocket server = null;
        Socket socket = null;
        InputStream in = null;
        OutputStream ou = null;
        try {
            server = new ServerSocket(8080);
            System.out.println("8080端口开启");
            socket = server.accept();
            in = socket.getInputStream();
           byte[] buffer = new byte[1024];
           int len = 0;
           //读取客户端数据t
            while((len = in.read(buffer))>0){
                System.out.println(new String(buffer,0,len));
            }
            //向客户端写数据
            ou = socket.getOutputStream();
            ou.write("get".getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void server2(){
        ServerSocket server = null;
        System.out.println("8080端口开启");
        try {
            while(true){
                Socket socket = server.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void serverBio2(){
        ServerSocket server = null;
        ExecutorService executorService = Executors.newFixedThreadPool(60);

        System.out.println("8080端口开启");
        try {
            while(true){
                Socket socket = server.accept();
                executorService.execute(new ServerHandler(socket));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
//    有效控制线程的数量，控制socket节奏
    public void serverBio3(){
        ServerSocket server = null;
        Map<Socket,String> map = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(60);

        System.out.println("8080端口开启");
        try {
            while(true){
                Socket socket = server.accept();
                //先不创建线程
                map.put(socket,"connected!");
                executorService.execute(new ServerHandler(socket));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

class ServerHandler implements Runnable{
    //维护一个socket成员变量，记录传来的socket
    private Socket socket;

    public ServerHandler(Socket socket){
        this.socket = socket;
    }
    //当前程序要执行的任务
    @Override
    public void run(){
        InputStream in = null;
        OutputStream ou = null;
        try {
            in = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            //读取客户端数据t
            while((len = in.read(buffer))>0){
                System.out.println(new String(buffer,0,len));
            }
            //向客户端写数据
            ou = socket.getOutputStream();
            ou.write("get".getBytes());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
