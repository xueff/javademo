package mail;

import cn.hutool.core.date.DateUtil;
import com.sun.mail.util.MailSSLSocketFactory;
import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.search.FlagTerm;
import java.util.Date;
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

    /**
     * 读取腾讯企业邮箱的邮件
     */
    @Test
    public void getMessage() {

        try{

            //创建一个有具体连接信息的Properties对象
            Properties prop = new Properties();
            prop.setProperty("mail.debug", "true");
            prop.setProperty("mail.store.protocol", "pop3");
            prop.setProperty("mail.pop3.host", "smtp.exmail.qq.com");

            Session session = Session.getInstance(prop);
            Store store = session.getStore();
            store.connect("", "");

            Folder folder = store.getFolder("inbox");
            folder.open(Folder.READ_ONLY);

            Message[] messages = folder.getMessages();

            for(int i=0;i<messages.length;i++){

                Date date = messages[i].getSentDate();
                String subject = messages[i].getSubject();
                String from = (messages[i].getFrom()[0]).toString();


                System.out.println("\n\n\n\n-----------------start------------------------");
                System.out.println(DateUtil.format(date,"yyyy-MM-dd"));
                System.out.println("第 "+ (i+1) + " 封邮件的发件人地址---->>>"+from);
                System.out.println("第 "+ (i+1) + " 封邮件的主题--->>>"+subject);
                System.out.println("第 "+ (i+1) + " 封邮件的内容---->>>");


                Object o = messages[i].getContent();
                if (o instanceof Multipart) {
                    Multipart mp = (Multipart)o;
                    for (int j = 0; j < mp.getCount(); j++) {
                        BodyPart b = mp.getBodyPart(j);

                        // Loop if the content type is multipart then get the content that is in that part,
                        // make it the new container and restart the loop in that part of the message.
                        if (b.getContentType().contains("multipart")) {
                            mp = (Multipart)b.getContent();
                            j = -1;
                            continue;
                        }
                        if(!b.getContentType().contains("text/html")) {
                            continue;
                        }

                        Object o2 = b.getContent();
                        if (o2 instanceof String) {
                               System.out.print(o2);
                        }
                    }
                }


                System.out.println("--------------------end---------------------\n\n\n\n");
            }

        }catch(Exception e){

            e.printStackTrace();
        }
    }
    /**
     * qq邮箱接收邮件
     */
    @Test
    public void getMessageQQ() {

        try{

            //创建一个有具体连接信息的Properties对象
            Properties props = new Properties();
            props.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");//ssl加密,jdk1.8无法使用
            props.setProperty("mail.imap.socketFactory.fallback", "false");
            props.setProperty("mail.transport.protocol", "imap"); // 使用的协议
            props.setProperty("mail.imap.port", "993");
            props.setProperty("mail.imap.socketFactory.port", "993");

            Session session = Session.getDefaultInstance(props);
//   session.setDebug(true);
            Store store = session.getStore("imap");
            store.connect("imap.qq.com","xxxx@qq.com", "xxxx");//登录认证

            Folder folder = store.getFolder("inbox");//获取用户的邮件账户
            folder.open(Folder.READ_WRITE);//设置对邮件账户的访问权限

            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN),false);//false代表未读，true代表已读

//   Message[] messages = folder.getMessages();
            Message[] messages = folder.search(ft);

            for(int i=0;i<messages.length;i++){

                String subject = messages[i].getSubject();
                String from = (messages[i].getFrom()[0]).toString();
                String content = messages[i].getContent().toString();

                System.out.println("第 "+ (i+1) + " 封邮件的发件人地址---->>>"+from);
                System.out.println("第 "+ (i+1) + " 封邮件的主题--->>>"+subject);
                System.out.println("第 "+ (i+1) + " 封邮件的内容---->>>"+content);
                System.out.println("-----------------------------------------");

                messages[i].setFlag(Flags.Flag.SEEN, false);//imap读取后邮件状态变为已读
            }

            folder.close(false);
            store.close();
        }catch(Exception e){

            e.printStackTrace();
        }
    }

}
