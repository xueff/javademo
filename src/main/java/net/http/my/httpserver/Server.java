package net.http.my.httpserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/9/3017:19
 */
public class Server {
    private static final String ENTER = "\r\n";
    private static final String SPACE = " ";
    public static void main(String[] args) {
        Server server = new Server();
        //1、创建一个服务器端并开启
        server.start();
    }
    public void start(){
        try {
            ServerSocket ss = new ServerSocket(9999);
            //2、接收来自浏览器的请求
            while (true) {
                this.recevie(ss);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void recevie(ServerSocket ss){
        BufferedReader br = null;
        try {
            Socket client = ss.accept();
            //3、将来自浏览器的信息打印出来
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            StringBuilder httpRequest = new StringBuilder();
            String meg = null;
            while(!(meg = br.readLine().trim()).equals("")){
                httpRequest.append(meg);
                httpRequest.append("\r\n");
            }
            System.out.println(httpRequest);
            this.httpResponse(client);

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void httpResponse(Socket client){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            StringBuilder contextText = new StringBuilder();
            contextText.append("<html><head></head><body>This is my page</body></html>");
            StringBuilder sb = new StringBuilder();
            /*通用头域begin*/
            sb.append("HTTP/1.1").append(SPACE).append("200").append(SPACE).append("OK").append(ENTER);
            sb.append("Server:myServer").append(SPACE).append("0.0.1v").append(ENTER);
            sb.append("Date:Sat,"+SPACE).append(new Date()).append(ENTER);
            sb.append("Content-Type:application/json;charset=UTF-8").append(ENTER);
            sb.append("Content-Length:").append(contextText.toString().getBytes().length).append(ENTER);
            /*通用头域end*/
            sb.append(ENTER);//空一行
            sb.append(contextText);//正文部分
            System.out.println(sb.toString());
            bw.write(sb.toString());//写会
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
//            try {
//                bw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}

