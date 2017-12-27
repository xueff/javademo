
package com.dayee.wintalent.service.position;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.xfire.spring.remoting.XFireClientFactoryBean;

import com.dayee.wintalent.framework.utils.EncryptUtils;
import com.dayee.wintalent.framework.utils.FileUtils;
import com.dayee.wintalent.service.channel.net.post.ChannelPositionResultListDocument;
import com.dayee.wintalent.service.channel.net.post.ChannelPositionResultListDocument.ChannelPositionResultList;
import com.dayee.wintalent.service.channel.net.post.ChannelPositionResultListDocument.ChannelPositionResultList.CodeImgList;

public class ChannelPositionServiceTest {

    // private static String url =
    // "http://103.37.165.201/wt/xwebservices/dianpingService?wsdl";
    //
    // private static String corpCode = "meituan";
    //
    // private static String userName = "meituana01";
    //
    // private static String pwd = "LlK@*A1s34&H%D4a";

    private static String url      = "http://localhost:8081/wt/xwebservices/channelPositionService?wsdl";

    private static String corpCode = "ygbx8";

    private static String userName = "ygbx";

//    private static String pwd      = EncryptUtils.getMD5("Z7AkJ8HFvV9C8YJ3");
    private static String pwd      = EncryptUtils.getMD5("Z7AkJ8HFvV9C8YJ3");

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

//         analyzeResume();
        // readUnreadResume();
        getChannelPunlishPostNum();
//        System.out.println("\n\n\n\n\n\n\n");
//        addAndModifyPost();

        // addAndModifyPost2();
        // loginAndOpPost();
//         opPost();

        // changeValidateCode();
    }

//    protected static void changeValidateCode() throws Exception {
//
//        ChannelPositionService service = createService();
//
////        byte[] b = service.changeValidateCode(corpCode, userName, pwd, 2,
////                                              "r50346892kpn");
//        byte[] b = service.changeValidateCode(corpCode, userName, pwd, 2,
//                "r50346892kpn");
//        try {
//            FileUtils
//                    .writeByteArrayToFile(new File(
//                                                  "d:\\MyDocuments\\temp\\resume\\test\\wintalent\\bug\\111.jpg"),
//                                          b);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    protected static void opPost() throws Exception {

        ChannelPositionService service = createService();

        // String pwd = EncryptUtils.getMD5("dayee123");
        String[] postUniqueNumber = { "2131000412356" };
        String s1 = service
                .opPost(corpCode, userName, pwd, 5, postUniqueNumber[0]);
        // try {
        // FileUtils
        // .writeStringToFile(new File(
        // "d:\\MyDocuments\\temp\\resume\\test\\wintalent\\bug\\111.xml"),
        // s1);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }

        System.out.println("结果:\n"+s1);
    }
    
protected static void getChannelPunlishPostNum() throws Exception {
        
        File file = new File(
                "d:\\MyDocuments\\work\\getpublishpostionnumber-test.xml");
        
        ChannelPositionService service = createService();
        String s1 = service.getChannelPunlishPostNum(corpCode, userName, pwd, FileUtils
                                             .readFileToString(file, "utf-8"));
        
        System.out.println("结果:\n"+s1);
    }

    protected static void addAndModifyPost() throws Exception {
        File file = new File(
                "d:\\MyDocuments\\work\\addOrModifyPosition-input-test.xml");

//         File file = new File(
//         "d:\\MyDocuments\\work\\点评\\test\\post_58_sample.xml");

        // File file = new
        // File("d:\\MyDocuments\\work\\点评\\test\\post_liepin_sample.xml");

        // File file = new File(
        // "d:\\MyDocuments\\work\\点评\\test\\post_lagou_sample.xml");

        // File file = new
        // File("d:\\MyDocuments\\work\\点评\\test\\post_ganji_sample.xml");

        // File file = new File(
        // "d:\\MyDocuments\\work\\点评\\test\\post_51job_sample.xml");

        // File file = new
        // File("d:\\MyDocuments\\work\\点评\\test\\post_dajie_sample.xml");

        // File file = new
        // File("d:\\MyDocuments\\work\\点评\\test\\post_yingjiesheng_sample.xml");

        // File file = new File(
        // "d:\\MyDocuments\\work\\点评\\test\\post_maimai_sample.xml");

//        File file = new File(
//                "d:\\MyDocuments\\work\\点评\\test\\post_test.xml");
        ChannelPositionService service = createService();

        String s1 = service.addAndModifyPost(corpCode, userName, pwd, FileUtils
                .readFileToString(file, "utf-8"));
        System.out.println(s1);

    }

    protected static void addAndModifyPost2() throws Exception {

        File file = new File(
                "d:\\MyDocuments\\work\\点评\\test\\post_ganji_sample.xml");

        ChannelPositionService service = createService();

        String s1 = service.addAndModifyPost(corpCode, userName, pwd, FileUtils
                .readFileToString(file, "utf-8"));
        System.out.println(s1);

        StringBuffer buffer = new StringBuffer(s1);
        Pattern POST_LIST_BEGIN_PATTERN = Pattern
                .compile("<(.*:)?(ChannelPositionResultList|OpChannelPositionList)( .*)?>",
                         Pattern.CASE_INSENSITIVE);
        Matcher m = POST_LIST_BEGIN_PATTERN.matcher(s1);
        if (m.find()) {
            String tag = m.group(2);
            buffer.replace(m.start(),
                           m.end(),
                           "<"     + tag
                                   + " xmlns=\"http://dayee.com/wintalent/service/channel/net/post\">");
        }

        String xmlContent = buffer.toString();

        ChannelPositionResultListDocument doc = ChannelPositionResultListDocument.Factory
                .parse(xmlContent);
        ChannelPositionResultList channelPositionResultList = doc
                .getChannelPositionResultList();
        CodeImgList codeImgList = channelPositionResultList
                .getCodeImgListArray(0);
        byte[] img = codeImgList.getCodeImg();
        try {

            FileUtils
                    .writeByteArrayToFile(new File(
                                                  "d:\\MyDocuments\\temp\\resume\\test\\wintalent\\bug\\code.jpg"),
                                          img);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void loginAndOpPost() throws Exception {

        File file = new File(
                "d:\\MyDocuments\\work\\点评\\test\\post_ganji_code_sample.xml");

        ChannelPositionService service = createService();

        String s1 = service.loginAndOpPost(corpCode, userName, pwd, 1,
                                           FileUtils.readFileToString(file,
                                                                      "utf-8"));
        System.out.println(s1);

    }

//    protected static void readUnreadResume() throws Exception {
//
//        ChannelPositionService service = createService();
//        // for (int i = 1; i < 11; i++) {
//        // String pwd = EncryptUtils.getMD5("dayee123");
//        long time1 = System.currentTimeMillis();
//        String s1 = service.readUnreadResume(corpCode, userName, pwd, 10, 11);
//        try {
//
//            FileUtils
//                    .writeStringToFile(new File(
//                                               "d:\\MyDocuments\\temp\\resume\\test\\wintalent\\bug\\111_" + 11
//                                                       + ".xml"), s1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        long time2 = System.currentTimeMillis();
//        System.out.println(time2 - time1);
//        // }
//    }

//    protected static void readUnreadCandResume() throws Exception {
//
//        ChannelPositionService service = createService();
//
//        // String pwd = EncryptUtils.getMD5("dayee123");
//
//        String s1 = service.readUnreadCandResume(corpCode, userName, pwd, 2, 1);
//        try {
//
//            FileUtils
//                    .writeStringToFile(new File(
//                                               "d:\\MyDocuments\\temp\\resume\\test\\wintalent\\bug\\111.xml"),
//                                       s1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    protected static void analyzeResume() throws Exception {
//
//        File file = new File(
//                "d:\\MyDocuments\\temp\\resume\\test\\wintalent\\bug\\何添-个人简历.pdf");
//        ChannelPositionService service = createService();
//        // String pwd = EncryptUtils.getMD5("vZcJLAdu4sgsgdny");
//        // System.out.println(pwd);
//        // String s1 = service.analyzeResume("ZTE", "ZTE", pwd, "李佳琪的简历.html",
//        // ".html",
//        // FileUtils.readFileToByteArray(file));
//
//        // ZteWebService service = (ZteWebService) bean.getObject();
//        // String pwd = EncryptUtils.getMD5("dayee123");
//        // String s1 = service.readAllUnreadResume("dayee", "dayee", pwd,
//        // new int[]{1,2}, new int[]{1});
//        long time1 = System.currentTimeMillis();
//        String s1 = service.analyzeResume(corpCode, userName, pwd,
//                                          "53311_刘锐.pdf", ".pdf",
//                                          FileUtils.readFileToByteArray(file));
//        long time2 = System.currentTimeMillis();
//        System.out.println("time="+(time2-time1));
//        
//
//        
//        // ResumeListDocument doc = ResumeListDocument.Factory.parse(s1);
//        // ResumeList resumeList = doc.getResumeList();
//        // Resume[] resumeArray = resumeList.getResumeArray();
//        // for (Resume resume : resumeArray) {
//        // ResumeContent[] resumeContentArray = resume.getResumeContentArray();
//        // for (ResumeContent rc : resumeContentArray) {
//        // OriginalContent oc = rc.getOriginalContent();
//        // String content = oc.getContent();
//        // byte[] b =Base64.decode(content);
//        // FileUtils.writeByteArrayToFile(new
//        // java.io.File("d:\\MyDocuments\\temp\\resume\\test\\wintalent\\bug\\121.html"),
//        // b);
//        // }
//        // }
//        try {
//
//            FileUtils
//                    .writeStringToFile(new File(
//                                               "d:\\MyDocuments\\temp\\resume\\test\\wintalent\\bug\\111.xml"),
//                                       s1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private static ChannelPositionService createService() throws Exception {

        XFireClientFactoryBean bean = new XFireClientFactoryBean();
        bean.setWsdlDocumentUrl(url);

        bean.setServiceClass(ChannelPositionService.class);
        bean.afterPropertiesSet();

        ChannelPositionService service = (ChannelPositionService) bean.getObject();
        return service;
    }
}
