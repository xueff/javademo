import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.SOAPHeaderElement;

import javax.xml.namespace.QName;

/**
 * @author xuefei
 * @description 11
 * @date 2019/8/8
 */
public class TestWebservice {

    public static void main(String[] args) {
        try {

            Service service =new Service();
            Call call=(Call)service.createCall();
            call.setTargetEndpointAddress(new java.net.URL("http://10.221.138.228:7879/ESB_SmsRemind_dpl/services/ReceiveService"));
//            call.setTargetEndpointAddress(new java.net.URL("http://localhost:8088/mockReceiveServiceHttpBinding"));
//            call.setOperationName("acceptMessageXml");//方法名
            call.setOperationName(new QName("http://nm.cmcc/esb/services/BS_OA_SMS_SmsRemind","acceptMessageXml"));
//            call.setSOAPActionURI("http://nm.cmcc/esb/services/BS_OA_SMS_SmsRemind" );

            //构造SOAP Header
            SOAPHeaderElement she = new SOAPHeaderElement(new QName("AuthenticationToken"));
            she.addChildElement("Systemid").setValue("DSJGK");
            she.addChildElement("Username").setValue("admin");
            she.addChildElement("Password").setValue("admin");
            she.addChildElement("AppID").setValue("DSJGK");
            call.addHeader(she);

            String xml = "<receivermessages><sender></sender><recipients>zhangsan;lisi</recipients><title>title</title><istruename>1</istruename><AppID>DSJGK</AppID    >"+
                    "<sendrule>1</sendrule><priority>1</priority><sendtype>2</sendtype>"+
                    "<senddate>2012-12-12 12:02:12</senddate><appidentity>DSJGK</appidentity><categoryidentity>DSJGKsub</categoryidentity><appmsgid>12121212121212</appmsgid></receivermessages>";
            System.out.println(""+xml);
            String returnStr = (String) call.invoke(new Object[]{xml});
            ////////////////////////////成功才能打印/////////////////////////////////////
            // 获取SOAP
            MessageContext msgContext = call.getMessageContext();
            // 请求消息
            Message reqMsg = msgContext.getRequestMessage();
            System.out.println(reqMsg.getSOAPPartAsString());
            // 响应消息
            Message respMsg = msgContext.getResponseMessage();
            System.out.println(respMsg.getSOAPPartAsString());
            ////////////////////////////成功才能打印/////////////////////////////////////
            System.out.println(returnStr);
        } catch (Exception e) {
            System.out.println("消息发送失败");
            e.printStackTrace();
        }

    }

}
