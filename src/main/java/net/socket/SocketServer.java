package net.socket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer{
    private static BufferedReader br=null;
    private static PrintWriter pw=null;
    private static ServerSocket ss;
    private static Socket s;

    public void start(){
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress("192.168.121.1",80));
            System.out.println("服务器正常启动。。。");
            while(true) {
                //从请求队列中取出一个连接
                Socket client = serverSocket.accept();
                // 处理这次连接
                HttpRequestHandler request = new HttpRequestHandler(client);
                request.handle();

                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    private class HandlerThread implements Runnable {
        private Socket socket;
        public HandlerThread(Socket client) {
            socket = client;
            new Thread(this).start();
        }

        @Override
        public void run() {
            try {
                // 读取客户端数据
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String clientInputStr = input.readLine();//这里要注意和客户端输出流的写方法对应,否则会抛 EOFException
                // 处理客户端数据
                System.out.println("客户端发过来的内容:" + clientInputStr);

                // 向客户端回复信息
                PrintStream out = new PrintStream(socket.getOutputStream());
                System.out.print("请输入:\t");
                // 发送键盘输入的一行
                String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
                out.println(s);

                out.close();
                input.close();
            } catch (Exception e) {
                System.out.println("服务器 run 异常: " + e.getMessage());
            } finally {
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e) {
                        socket = null;
                        System.out.println("服务端 finally 异常:" + e.getMessage());
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new SocketServer().start();
    }
}

class HttpRequestHandler {
    private Socket socket;

    public HttpRequestHandler(Socket socket) {
        this.socket = socket;
    }

    public void handle() throws IOException {
        socket.getOutputStream().
                write(("HTTP/1.1 200 OK\r\n" +  //响应头第一行
                        "Content-Type: text/html; charset=utf-8\r\n" +  //简单放一个头部信息
                        "\r\n" +  //这个空行是来分隔请求头与请求体的
                        html()+"\r\n").getBytes());
    }



    private String html(){
        return "<!DOCTYPE html>\n" +
                "<html lang=\"zh-cn\">\n" +
                " <head>\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <title>XieZhu - LuCI</title>\n" +
                "  <!--[if lt IE 9]><script src=\"/luci-static/bootstrap/html5.js\"></script><![endif]-->\n" +
                "  <meta name=\"viewport\" content=\"initial-scale=1.0\">\n" +
                "  <link rel=\"stylesheet\" href=\"/luci-static/bootstrap/cascade.css\">\n" +
                "  <link rel=\"stylesheet\" media=\"only screen and (max-device-width: 854px)\" href=\"/luci-static/bootstrap/mobile.css\" type=\"text/css\" />\n" +
                "  <link rel=\"shortcut icon\" href=\"/luci-static/bootstrap/favicon.ico\">\n" +
                "  <script src=\"/luci-static/resources/xhr.js\"></script>\n" +
                " </head>\n" +
                "\n" +
                " <body class=\"lang_zh-cn\">\n" +
                "  <header>\n" +
                "   <div class=\"fill\">\n" +
                "    <div class=\"container\">\n" +
                "     <a class=\"brand\" href=\"#\">XieZhu</a>\n" +
                "     <ul class=\"nav\">\n" +
                "\t</ul>\n" +
                "\n" +
                "\t\n" +
                "    </div>\n" +
                "   </div>\n" +
                "  </header><div id=\"maincontent\" class=\"container\">\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "<form method=\"post\" action=\"/cgi-bin/luci\">\n" +
                "\t<div class=\"cbi-map\">\n" +
                "\t\t<h2><a id=\"content\" name=\"content\">需要授权</a></h2>\n" +
                "\t\t<div class=\"cbi-map-descr\">\n" +
                "\t\t\t请输入用户名和密码。</div>\n" +
                "\t\t<fieldset class=\"cbi-section\"><fieldset class=\"cbi-section-node\">\n" +
                "\t\t\t<div class=\"cbi-value\">\n" +
                "\t\t\t\t<label class=\"cbi-value-title\">用户名</label>\n" +
                "\t\t\t\t<div class=\"cbi-value-field\">\n" +
                "\t\t\t\t\t<input class=\"cbi-input-user\" type=\"text\" name=\"luci_username\" value=\"root\" />\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t\t<div class=\"cbi-value cbi-value-last\">\n" +
                "\t\t\t\t<label class=\"cbi-value-title\">密码</label>\n" +
                "\t\t\t\t<div class=\"cbi-value-field\">\n" +
                "\t\t\t\t\t<input class=\"cbi-input-password\" type=\"password\" name=\"luci_password\" />\n" +
                "\t\t\t\t</div>\n" +
                "\t\t\t</div>\n" +
                "\t\t</fieldset></fieldset>\n" +
                "\t</div>\n" +
                "\n" +
                "\t<div>\n" +
                "\t\t<input type=\"submit\" value=\"登录\" class=\"cbi-button cbi-button-apply\" />\n" +
                "\t\t<input type=\"reset\" value=\"复位\" class=\"cbi-button cbi-button-reset\" />\n" +
                "\t</div>\n" +
                "</form>\n" +
                "<script type=\"text/javascript\">//<![CDATA[\n" +
                "\tvar input = document.getElementsByName('luci_password')[0];\n" +
                "\tif (input)\n" +
                "\t\tinput.focus();\n" +
                "//]]></script>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "   <footer>\n" +
                "    <a href=\"https://github.com/openwrt/luci\">Powered by LuCI for-15.05 branch (git-18.097.75958-5537d68)</a> / OpenWrt Chaos Calmer 15.05 unknown\n" +
                "    \n" +
                "   </footer>\n" +
                "   </div>\n" +
                "  </div>\n" +
                " </body>\n" +
                "</html>\n" +
                "\n" +
                "\n";
    }
}
