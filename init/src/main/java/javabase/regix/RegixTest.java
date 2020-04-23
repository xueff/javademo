package javabase.regix;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/9/3010:14
 */
public class RegixTest {

    @Test
    public void checkSpecialChars(){
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher("as*ds");
        if(m.find())
            System.out.println("房间号不能有特殊字符");
    }
    /**
     * 查找所有一段匹配的
     */
    @Test
    public void find(){
//        查找除最后一位不同匹配的
        //## 为分隔符
        String keys = "/.dev/47b9ab80559cce18bb3217129278f9f5/28f366b6dc86@00124a00163a7a3a0a" +
                "##" +
                "/.dev/47b9ab80559cce18bb3217129278f9f5/28f366b6dc86@00124a00163a7a3a0b" +
                "##" +
                "/.dev/47b9ab80559cce18bb3217129278f9f5/28f366b6dc86@00124a00163a7a3a0c" +
                "##" +
                "/.dev/47b9ab80559cce18bb3217129278f9f5/28f366b6dc86@00124a00163a7ahjh" +
                "##" +
                "/.dev/47b9ab80559cce18bb3217129278f9f5/28f366b6dc86@00124a00163a7a3a0e";

        for (String item : keys.split("##")){
            Pattern p = Pattern.compile(item.substring(0,item.length()-1), Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(keys);
            int count = 0;//匹配数量
            while (m.find()) {
                System.out.println( "查找匹配的七点:"+m.start());
                System.out.println( "获取完整匹配:"+keys.substring(m.start(),m.start()+item.length()));

                count++;
            }
            System.out.println("匹配数量"+count);
        }
    }
    /**
     * replace uuid
     */
    @Test
    public void replaceUUID(){
//        查找除最后一位不同匹配的
        //## 为分隔符
        String keys = "[\"15\",\"20\",\"37\",\"63\",\"6b27af9c-6f24-485d-8637-83a5ba932680\",\"79\",\"86\",\"90\",\"aa097e8e-a83d-474c-9a98-dda8b395589b\"]";

        keys = keys.replaceAll("^\"*\"$","");
        System.out.print(keys);
    }



}
