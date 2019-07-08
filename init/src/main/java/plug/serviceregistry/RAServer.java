package plug.serviceregistry;

import io.netty.channel.AbstractChannel;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.core.net.NetServer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuefei
 * @Title: NetServer注册中心
 * @Description: 注册中心：  socket server
 * @date 2018/12/310:55
 */
public class RAServer {
    static Map<String, JsonObject> fileMap = new HashMap<>();
    public static void main(String[] args) {
        NetServer server = Vertx.vertx().createNetServer();
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                try{
                    if(buffer != null){
                        JsonObject json = new JsonObject(buffer.toString());
                        String clientId = json.getString("clientId");
                        Long heart = json.getLong("heart");
                        Long heartTime = json.getLong("heartTime");

                        if(!fileMap.containsKey(clientId)){
                            fileMap.put(clientId,new JsonObject());
                        }

                        JsonObject status = fileMap.get(clientId);

                        status.put("useTime",status.getLong("useTime")+(heart-status.getLong("heart")-heartTime))
                                .put("heart",heart);



                    }
                }catch (Exception e){}
                System.out.println("I received some bytes: " + buffer.toString());

            });

            //异常的处理器
            socket.exceptionHandler(except->{
                except.printStackTrace();
                //连接断开处理
                System.out.println("actualPort"+server.actualPort());
            });
            //管道关闭处理
            socket.closeHandler(hander-> {
                System.out.println("close hander");
            });
        });
        server.listen(1880, "localhost", res -> {
            if (res.succeeded()) {
                System.out.println("Server is now listening on actual port: " + server.actualPort());
            } else {
                System.out.println("Failed to bind!");
            }
        });
    }

    private JsonObject baseJ(){
        JsonObject json = new JsonObject();
        json .put("heart",0L)
            .put("useTime",0L);   //两次心跳相减
        return json;
    }


}
