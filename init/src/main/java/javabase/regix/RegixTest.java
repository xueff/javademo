package javabase.regix;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
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

/**
 * flag:
 * Pattern.CANON_EQ:当且仅当两个字符的"正规分解(canonical decomposition)"都完全相同的情况下，才认定匹配
 * Pattern.CASE_INSENSITIVE:忽略大小写
 *Pattern.COMMENTS： * (?x)	在这种模式下，匹配时会忽略(正则表达式里的)空格字符(注：不是指表达式里的"//s"，而是指表达式里的空格，tab，回车之类)。注释从#开始，一直到这行结束。可以通过嵌入式的标志来启用Unix行模式
 * Pattern.MULTILINE： * (?m)	在这种模式下，'^'和'$'分别匹配一行的开始和结束。此外，'^'仍然匹配字符串的开始，'$'也匹配字符串的结束。默认情况下，这两个表达式仅仅匹配字符串的开始和结束
 *Pattern.UNICODE_CASE： * (?u)	在这个模式下，如果你还启用了CASE_INSENSITIVE标志，那么它会对Unicode字符进行大小写不明感的匹配。默认情况下，大小写不明感的匹配只适用于US-ASCII字符集。
 *
 *Pattern.UNIX_LINES： * (?d)	在这个模式下，只有'/n'才被认作一行的中止，并且与'.'，'^'，以及'$'进行匹配。
 *
 */
public class RegixTest {



    /**
     * //      Pattern p=Pattern.compile("([a-z]+)(\\d+)");
     *         //Matcher m=p.matcher("aaa2223bb");
     *         //m.find();   //匹配aaa2223
     *         //m.groupCount();   //返回2,因为有2组
     *         //m.start(1);   //返回0 返回第一组匹配到的子字符串在字符串中的索引号
     *         //m.start(2);   //返回3
     *         //m.end(1);   //返回3 返回第一组匹配到的子字符串的最后一个字符在字符串中的索引位置.
     *         //m.end(2);   //返回7
     *         //m.group(1);   //返回aaa,返回第一组匹配到的子字符串
     *         //m.group(2);   //返回2223,返回第二组匹配到的子字符串
     */
    @Test
    public void findAddress(){

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



}
