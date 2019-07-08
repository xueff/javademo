package plug.serviceregistry;

import io.vertx.core.json.JsonObject;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Date;
import java.util.UUID;

/**
 * client 端
 */
public class SocketClient{
    static String id = "client:"+ UUID.randomUUID().toString();

    public static void main(String[] args) throws InterruptedException, IOException {
        Socket client = new Socket("127.0.0.1",2379);
        client.setKeepAlive(true);
        client.setSoTimeout(10);
        JsonObject json = new JsonObject().put("clientId", id);
        for(;;) {
            json .put("heart", new Date().getTime())
                 .put("heartTime",10000);
            try {
                PrintWriter pWriter = new PrintWriter(client.getOutputStream(), true);
                pWriter.println(json.toString());
                Thread.sleep(10000);
                try{
                    client.sendUrgentData(0xff); //服务端是否掉线
                }catch(Exception ex){
                    System.out.println("与master失联。。");
//                    reconnect();
                }
                System.out.println(client.isBound());
                System.out.println(client.isClosed());
                System.out.println(client.isConnected());
                System.out.println(client.isInputShutdown());
                System.out.println(client.isOutputShutdown());
            }catch (Exception e){

                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
//        client.close();
    }
}

