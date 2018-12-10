package utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/1913:35
 */
public class CommonUtils {
    public static void printAsJson(String before,Object object,String end){
        String str = before==null?StringUtils.EMPTY:before;
        if(object instanceof List)
            str += JSONArray.fromObject(object).toString();
        else if(object instanceof Set)
            str += JSONArray.fromObject(object).toString();
        else
            str += JSONObject.fromObject(object).toString();
        System.out.println(str+(end==null?StringUtils.EMPTY:before));
    }

    public static void printAsJson(Object object){
        printAsJson(StringUtils.EMPTY,object,StringUtils.EMPTY);
    }

    /**
     *
     * @Title: objectToMap
     * @Description: TODO(bean转换为Map)
     * @return Map<?,?>    返回类型
     * @param obj
     * @return
     */
    public static Map<?, ?> objectToMap(Object obj) {
        if(obj == null) {
            return null;
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }


    /**
     * 16进制字符串转换byte数组
     *
     * @param hexstr
     *            String 16进制字符串
     * @return byte[] byte数组
     */
    public static byte[] hexString2Bytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }
    private static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }
}
