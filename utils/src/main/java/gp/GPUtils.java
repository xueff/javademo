package gp;

import com.common.utils.JSoupUtils;
import net.sf.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/10/239:15
 */
public class GPUtils {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(getShangHai());
    }



    public static Map<String,String> getShangHai() throws IOException, InterruptedException {

        Map<String,String> map = new HashMap<>();

        String url = "http://70.push2.eastmoney.com/api/qt/clist/get?pn=4&pz=20&fs=m:1+t:2&fields=f12,f13,f14";

        String res = JSoupUtils.getJson(url);



        return map;
    }





}

