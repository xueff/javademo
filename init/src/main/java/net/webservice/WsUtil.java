//package net.webservice;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.axis.Message;
//import org.apache.axis.MessageContext;
//import org.apache.axis.client.Call;
//import org.apache.axis.client.Service;
//import org.apache.axis.message.SOAPHeaderElement;
//
//import javax.xml.namespace.QName;
//import java.net.URL;
//
///**
// * IntelliJ IDEA
// */
//public class WsUtil {
//
//
//    public static void main(String[] args) {
//        new WsUtil().wsCall("http://localhost:8088/ESB_SmsRemind_dpl/services/ReceiveService?wsdl","acceptMessageXml","AuthenticationToken","systemId","username","pwd","xml");
//    }
//    public String wsCall(String url, String method, String qName, String systemId, String username, String password, String xml) {
//        try {
//            Service service = new Service();
//            Call call = (Call) service.createCall();
//            call.setTargetEndpointAddress(new URL(url));
//            call.setOperationName(method);
//            SOAPHeaderElement she = new SOAPHeaderElement(new QName(qName));
//            she.addChildElement("systemid").setValue(systemId);
//            she.addChildElement("username").setValue(username);
//            she.addChildElement("password").setValue(password);
//
//
//
//            call.addHeader(she);
//            String returnStr = (String) call.invoke(new Object[]{xml});
//            log.info("return+++++++++++"+returnStr);
//            int indexBegin = returnStr.indexOf("<result>");
//            int indexEnd = returnStr.indexOf("</result>");
//
//            log.info("================================");
//            MessageContext messageContext = call.getMessageContext();
//            Message reqMsg = messageContext.getRequestMessage();
//            System.out.println(reqMsg.getSOAPPartAsString());
//            return returnStr;
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            log.error("消息发送失败");
//            e.printStackTrace();
//            return "-1";
//        }
//    }
//}
