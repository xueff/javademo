package net.webservice;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.namespace.QName;
import java.util.List;


/**
 * IntelliJ IDEA
 */
public class HeBeiWS {
    //wsdl文件路径
    //TODO test请求打印
    private String wsUrl = "C:\\Users\\admin\\Desktop\\1.wsdl";
    private String passwd = "11";
    private String userName = "11";
    private String channel = "11";


    public static void main(String[] args) {
        new HeBeiWS().validateTicket("");

    }


    /**
     * ticket校验合法
     * @param ticket
     * @return
     */
    public String validateTicket(String ticket) {
        String resMsg = null;
        try {
            System.out.print("ticket="+ticket+",wsUrl="+wsUrl+";");
            resMsg = invokeLogin(ticket, wsUrl, "validateTicket");
           System.out.print("webservice返回:"+resMsg);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("webservice接口错误。");
            resMsg = "webservice接口错误。";
        }
      return  resMsg;
    }


    /**
     * 请求入口：根据接口入参、地址URL、接口方法名，请求接口
     * @param xml
     * @param url
     * @param method
     * @return
     * @throws Exception
     */
    public String invokeLogin(String xml, String url, String method) throws Exception {
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(url);

        client.getOutInterceptors().add(new ClientLoginInterceptor(userName,passwd,channel));
        Object[] objects2 = client.invoke(method, xml);
        return objects2[0].toString();
    }
}


/**
 * 添加XML校验头
 */
class ClientLoginInterceptor extends AbstractPhaseInterceptor<SoapMessage> {

    private String username;
    private String password;
    private String channel;

    public ClientLoginInterceptor(String username, String password,String channel) {
        super(Phase.PREPARE_SEND);
        this.username = username;
        this.password = password;
        this.channel = channel;
    }

    public ClientLoginInterceptor() {
        super(Phase.PREPARE_SEND);

    }

    @Override
    public void handleMessage(SoapMessage soap)  {
        List<Header> headers = soap.getHeaders();
        Document doc = DOMUtils.createDocument();
        //定义接口地址URL
        Element auth = doc.createElementNS("http://authorizationserver.venustech.com.cn", "Venus4AService");
        //接口身份认证的帐号/密码
        Element username = doc.createElement("username");
        Element password = doc.createElement("password");
        if(this.channel!=null){
            Element channel = doc.createElement("channel");
            channel.setTextContent(this.channel);
            auth.appendChild(channel);
        }
        username.setTextContent(this.username);
        password.setTextContent(this.password);
        auth.appendChild(username);
        auth.appendChild(password);
        //指定接口命名空间
        headers.add(0, new Header(new QName("Venus4AService"), auth));
    }
}


