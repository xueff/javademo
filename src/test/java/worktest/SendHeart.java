package worktest;

import net.socket.SocketClient;
import utils.CommonUtils;

import java.io.OutputStream;
import java.net.Socket;

public class SendHeart implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while(true){
            for(;i<3000;i++){
                SendHeart v = new SendHeart();
                Thread t=new Thread(v);
                t.start();
                System.out.println("线程数："+i);
//                if(i<30)
//                    Thread.sleep(5000);
//
//                    Thread.sleep(500+ RamdonStudy.getRamdonInt(1300));
//                Thread.sleep(RamdonStudy.getRamdonInt(300));
                if(i%15 == 0)
                    Thread.sleep(2);
//                if(i==300)
//                    Thread.sleep(80*1000);
//                if(i%500==0)
//                    Thread.sleep(1000);
//                if(i>=2478) {
//                    Thread.sleep(200);
                }
            }
            //Thread.sleep(1000);
        }

    @Override
    public void run() {
        try {
            String num = Thread.currentThread().getName().split("-")[1];
            String hostMac = "AAAAAAAA";
            if(num.length()==1)
                hostMac +="000"+num;
            else if(num.length()==2)
                hostMac +="00"+num;
            else if(num.length()==3)
                hostMac +="0"+num;
            else if(num.length()==4)
                hostMac += num;


            Socket s = new Socket("localhost", 1820);

            OutputStream os = s.getOutputStream();


//            int baseLength = 34;
//
//            String hostMac = UUID.randomUUID().toString().replace("-","").substring(0,12);
//            int deviceNum = RamdonStudy.getRamdonInt(9);
//            if(deviceNum==0) deviceNum =1;
//            int endLength = 34 + 24*deviceNum;
//
//
//            String deviceIds = "";
//            String cf = "";
//            for(int i=1 ;i<=deviceNum;i++){
//                String deviceId = "";
//                deviceIds += UUID.randomUUID().toString().replace("-","").substring(0,18);
//                deviceIds += "010d"+UUID.randomUUID().toString().replace("-","").substring(0,2);
//
//            }
//              String send = "fc00180001"+hostMac+"030102"+"0"+deviceNum+deviceIds+"91";
//            System.out.println(Thread.currentThread().getName()+"send:"+send);



            byte[] send = CommonUtils.hexString2Bytes("fc00a80001"+hostMac+
                    "0301020d00124b000dc6238708010c0000124b00198e32430800020000124b00163a572308010f0000124b0018db94110901010000124b0014d3a9510a01000000124b0014d3a9510901000000124b0014d3a9510801000000124b001659703c08010d0000124b001996823408010e0000124b0017298fff0b01000000124b0017298fff0a01000000124b0017298fff0901000000124b0017298fff080100009e");
            System.out.println("send:"+hostMac);
            for(int i=0;i<500000;i++) {
                System.out.println(hostMac);
                os.write(send);
                os.flush();
//                InputStream is = s.getInputStream();
//                BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                String line = null;
//                while ((line = br.readLine()) != null) {
//                    System.out.println("client get :" + line);
//                }
//
//                br.close();
//                is.close();
                try {
                    Thread.sleep(30*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }




//            os.close();
//            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
