package com.my.cp;

import io.vertx.core.json.JsonObject;
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
public class CPUtils {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println(getSSQ());
    }

    public static Map<String,String> getSSQ() throws IOException, InterruptedException {

        Map<String,String> map = new HashMap<>();

        int shuliang = 300; //30 50 100 200 300
        String url = "https://www.9188.com/ssq/zst/jbhl_"+shuliang+".html";
        Thread.sleep(1000);
        Document doc = Jsoup.connect(url).get();
        Thread.sleep(1000);
        Elements body = doc.getElementsByClass("cm_zst_tbody");
        Elements eachs = body.select("tr");
        int rows = eachs.size();
        System.out.println("数量："+rows);
        for (Element element : eachs) {
            StringBuffer res = new StringBuffer();
            Elements qishu = element.getElementsByClass("cm_zst_yellow");

            if(null != qishu.text() && qishu.text().length()>0) {
//                res.append(qishu.text() + ":");
                Elements reds = element.getElementsByClass("cm_zst_redball");
                for (Element red : reds) {
                    res.append(red.text() + ",");
                }
                Elements blues = element.getElementsByClass("cm_zst_blueball");
                for (Element blue : blues) {
                    res.append(blue.text() + ",");
                }
                res = res.deleteCharAt(res.length()-1);
                map.put(qishu.text(),res.toString());
            }
        }

        return map;
    }


    public static Map<String,String> getDLT() throws IOException, InterruptedException {

        Map<String,String> map = new HashMap<>();

        int shuliang = 300; //30 50 100 200 300
        String url = "https://www.9188.com/dlt/zst/jbqh_"+shuliang+".html";
        Thread.sleep(1000);
        Document doc = Jsoup.connect(url).get();
        Thread.sleep(1000);
        Elements body = doc.getElementsByClass("cm_zst_tbody");
        Elements eachs = body.select("tr");
        int rows = eachs.size();
        System.out.println("数量："+rows);
        for (Element element : eachs) {
            StringBuffer res = new StringBuffer();
            Elements qishu = element.getElementsByClass("cm_zst_yellow");

            if(null != qishu.text() && qishu.text().length()>0) {
//                res.append(qishu.text() + ":");
                Elements reds = element.getElementsByClass("cm_zst_redball");
                for (Element red : reds) {
                    res.append(red.text() + ",");
                }
//                System.out.print(" | ");
                Elements blues = element.getElementsByClass("cm_zst_blueball");
                for (Element blue : blues) {
                    res.append(blue.text() + ",");
                }
                res = res.deleteCharAt(res.length()-1);
                map.put(qishu.text(),res.toString());
            }
        }

        return map;
    }


}

