package mail;

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
    static String USERNAME   = "xueff.88@163.com";
    static String PASSWORD  = "ak969909aspnet";
    static String EMAILFORM  = "xueff.88@163.com";

    public static void main(String[] args) {
        MailUtils.sendMail("657413186@qq.com","test <br /> 666");
    }


    public static void sendMail(String address,String content) {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", "smtp.163.com");// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
        props.put("mail.smtp.host", "smtp.163.com"); // 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
        props.put("mail.smtp.auth", "true");// 用刚刚设置好的props对象构建一个session
        Session session = Session.getInstance(props); // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
//        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(EMAILFORM));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(address)); // 加载收件人地址
            message.setSubject("test" );// 加载标题
            MimeMultipart multipart = new MimeMultipart();// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            MimeBodyPart contentPart = new MimeBodyPart();// 设置邮件的文本内容
            contentPart.setContent(content, "text/html;charset=utf-8");
            multipart.addBodyPart(contentPart);
            message.setContent(multipart);
            message.saveChanges(); // 保存变化
            Transport transport = session.getTransport("smtp");// 连接服务器的邮箱
            transport.connect("smtp.163.com", USERNAME, PASSWORD);// 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (AddressException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }catch( Exception e) {
            e.printStackTrace();
        }
    }
}
