package javabase.io.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpSocketClient {
    public static void main(String[] args) {
        Socket s = null;
        BufferedReader br  = null;
        try {
            Socket socket = new Socket("127.0.0.1",8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.print("hello");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(s != null){
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
