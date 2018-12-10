package net.socket;
import javabase.ramdon.RamdonStudy;
import utils.CommonUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SocketClient{

    public static void main(String[] args) throws InterruptedException, IOException {
        Socket client = new Socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
        // 建立TCP连接，虚拟机的地址为192.168.194.129
        // Nginx监听的端口设置为8080
        client.connect(inetSocketAddress, 1000);
        String request = "POST /hander/it HTTP/1.1\r\n"+
                "Host: 127.0.0.1:8080\r\n\r\n"+
                "{\"hostMac\":\"28f366b6d6c7\",\"deviceId\":\"00124b0018d8454708\",\"cmdName\":\"tv_TurnOn\"}"
                ;
        PrintWriter pWriter = new PrintWriter(client.getOutputStream(),true);
        // 这里使用println()方法是因为 HTTP 协议在报头和报文之间，有一行空行
        // 如果少了这一行空行，服务器是无法解析报文的，会出现 400 错误
        pWriter.println(request);
        client.close();

        Thread.sleep(1000);
        Thread.sleep(10000);

    }
}

