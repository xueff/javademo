package com.my;

import com.common.utils.JSoupUtils;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xuefei
 * @description jsoup
 * @date 2019/8/4
 */
public class JSoupTest {

    public static void main(String[] args) {
        try {
            Document doc = JSoupUtils.getDoc("http://product.cnmo.com/manu.html");
            Thread.sleep(3000);
            //doc.body().select("ul.tab-div.manu-tab-div.manu-tab-div1").get(0).children().get(0).children().get(0).getElementsByTag("span").get(0).text()
            Elements items =  doc.body().select("ul.tab-div.manu-tab-div.manu-tab-div1").get(0).children();
            JsonArray array = new JsonArray();
            for(Element e:items){
                String name = e.children().get(0).getElementsByTag("span").get(0).text();
                Elements phones = e.children().get(1).children();
                for(Element e2:phones){
                    String phone = e2.text();
                    array.add(phone);
                }
            }
            System.out.println(array.toString());
            System.out.println(array.size());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
