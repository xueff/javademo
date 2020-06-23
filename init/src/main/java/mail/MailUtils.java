package mail;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/11/19:52
 */
public class MailUtils {
    static String USERNAME   = "6@qq.com";
    static String PASSWORD  = "";
    static String EMAILFORM  = "6@qq.com";

    public static void main(String[] args) {
        MailUtils.sendMail("1@qq.com","test <br /> 1111");
    }


//    public static void sendMail(String address,String content) {
//        Properties props = new Properties();
//        props.setProperty("mail.smtp.host", "smtp.qq.com");// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
//        props.put("mail.smtp.host", "smtp.qq.com"); // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
//        props.put("mail.smtp.auth", "true");// 用刚刚设置好的props对象构建一个session
//        Session session = Session.getInstance(props); // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
////        session.setDebug(true);
//        MimeMessage message = new MimeMessage(session);
//        try {
//            message.setFrom(new InternetAddress(EMAILFORM));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(address)); // 加载收件人地址
//            message.setSubject("test" );// 加载标题
//            MimeMultipart multipart = new MimeMultipart();// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
//            MimeBodyPart contentPart = new MimeBodyPart();// 设置邮件的文本内容
//            contentPart.setContent(content, "text/html;charset=utf-8");
//            multipart.addBodyPart(contentPart);
//            message.setContent(multipart);
//            message.saveChanges(); // 保存变化
//            Transport transport = session.getTransport("smtp");// 连接服务器的邮箱
//            transport.connect("smtp.qq.com", USERNAME, PASSWORD);// 把邮件发送出去
//            transport.sendMessage(message, message.getAllRecipients());
//            transport.close();
//
//        } catch (AddressException e) {
//            e.printStackTrace();
//        } catch (NoSuchProviderException e) {
//            e.printStackTrace();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }catch( Exception e) {
//            e.printStackTrace();
//        }
//    }


    /**
     * 发件人邮箱须配置权限
     * @param address
     * @param content
     */
    public static void sendMail(String address,String content) {
        try{
            String host = "smtp.qq.com";
            Properties prop = new Properties();
            prop.setProperty("mail.host", host);
            prop.setProperty("mail.transport.protocol", "smtp");
            prop.setProperty("mail.smtp.auth", "true");

            // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);

            //1、创建定义整个应用程序所需的环境信息的 Session 对象
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮件用户名、授权码
                    return new PasswordAuthentication(USERNAME, "jqiyppgrdaevbfbe");
                }
            });

            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);

            //2、通过session得到transport对象
            Transport ts = session.getTransport();

            //3、使用邮箱的用户名和授权码连上邮件服务器
            ts.connect(host, USERNAME, "jqiyppgrdaevbfbe");

            //4、创建邮件
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME)); //发件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(address)); //收件人
            message.setSubject("用户注册邮件"); //邮件的标题

            String info = "恭喜您注册成功，您的用户名：" + USERNAME + ",您的密码：" + PASSWORD + "，请妥善保管，如有问题请联系网站客服!!";

            message.setContent(info, "text/html;charset=UTF-8");
            message.saveChanges();

            //发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
