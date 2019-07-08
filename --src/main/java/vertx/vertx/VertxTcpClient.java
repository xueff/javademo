package vertx.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;
import javabase.ramdon.RamdonStudy;

import java.util.UUID;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/2214:59
 */
public class VertxTcpClient implements Runnable {
    public static Vertx getVertx(){
        return Vertx.vertx();
    }

    public static void main(String[] args) throws InterruptedException {
        for(int i = 0;i<1000;i++){

            VertxTcpClient v = new VertxTcpClient();
            Thread t=new Thread(v);
            t.start();
            System.out.println("线程数："+i);
            Thread.sleep(1000);
        }
}

    @Override
    public void run() {
        Vertx vertx = Vertx.vertx();
        NetClient client = vertx.createNetClient();
        client.connect(1820,"localhost",res -> {
            String hostMac = UUID.randomUUID().toString().replace("-","").substring(0,12);
            int deviceNum = RamdonStudy.getRamdonInt(9);
            String deviceIds = "";
            for(int i=0 ;i<deviceNum;i++){
                deviceIds += UUID.randomUUID().toString().replace("-","").substring(0,18);
            }

            if (res.succeeded()) {
                System.out.println("Connected!");
                //用Socket操作数据
                NetSocket socket = res.result();
                String send = "fc00180001"+hostMac+"030102"+"0"+deviceNum+deviceIds+"010dcf91";
                System.out.println(Thread.currentThread().getName()+"send:"+send);
                for(;;) {
                    socket.write(Thread.currentThread().getName());
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Failed to connect: " + res.cause().getMessage());
            }
        });
    }
}