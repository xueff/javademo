package vertx;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/12/310:55
 */
public class VertxTcpServer {
    public static void main(String[] args) {
        NetServer server = Vertx.vertx().createNetServer();
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                System.out.println("I received some bytes: " + buffer.toString());
                socket.write("some data");
            });
            socket.write("some data");
            //异常的处理器
            socket.exceptionHandler(except->{
                //TODO  有worker掉线
                except.printStackTrace();
//                logger.error("有worker掉线");
            });
//            //管道关闭处理
            socket.closeHandler(close->{
//                logger.error("socket.closeHandler");
            });
        });
        server.listen(8080, "localhost", res -> {
            if (res.succeeded()) {
                System.out.println("Server is now listening on actual port: " + server.actualPort());
            } else {
                System.out.println("Failed to bind!");
            }


        });

    }


}
