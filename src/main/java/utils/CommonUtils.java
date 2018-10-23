package utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.util.List;
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
}
