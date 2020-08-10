package com.my;

import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.common.utils.JSoupUtils;
import io.vertx.core.json.JsonArray;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

/**
 * @author xuefei
 * @description jsoup
 * @date 2019/8/4
 */
public class JSoupTest {

    /**
     * 手机型号
     */
    @Test
    public void phone_model() {
        try {
            Document doc = JSoupUtils.getDocRareCharacters("http://product.cnmo.com/manu.html");
            Thread.sleep(100);
            //doc.body().select("ul.tab-div.manu-tab-div.manu-tab-div1").get(0).children().get(0).children().get(0).getElementsByTag("span").get(0).text()
            Elements items =  doc.body().select("ul.tab-div.manu-tab-div.manu-tab-div1").get(0).children();
            JsonArray pinpai = new JsonArray();
            JsonArray xinghao = new JsonArray();
            for(Element e:items){
                String name = e.children().get(0).getElementsByTag("span").get(0).text();
                pinpai.add(name);
                Elements phones = e.children().get(1).children();
                for(Element e2:phones){
                    String phone = e2.text();
                    xinghao.add(phone);
                }
            }
            System.out.println(xinghao.toString());
            System.out.println(xinghao.size());
            System.out.println(pinpai.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**http://jb39.com
     * 疾病类型
     */
    @Test
    public void sick_type() {

        String[] aa = "".split("\n");
        List<String> list = Arrays.asList(aa);


        //按科室
        JSONObject keshiJson = new JSONObject();
        try {
            //首页
            String baseUrl = "http://jb39.com";
            Document doc = JSoupUtils.getDocRareCharacters(baseUrl);
            Thread.sleep(100);
            Elements items =  doc.body().select("ul.ul-jbks").get(0).children();
            for(Element e:items){
                String uri =  e.children().get(0).attr("href");
                String ke = e.children().get(0).text();
                if(list.contains(ke)){
                    continue;
                }
                System.out.println("    "+ke);
                Set<String> array = new LinkedHashSet();

                Document doc2 = JSoupUtils.getDocRareCharacters(baseUrl+uri);
                Thread.sleep(100);
                //常见疾病
                try {

                    Elements items2_1 =  doc2.body().select("ul.mulu-body").get(0).children();
                    for(Element e2:items2_1){
                        String jibin = e2.children().get(0).text();
                        if(jibin.length() ==6){
                            String uri2 =  e2.children().get(0).attr("href");
                            jibin = eqSixLength(baseUrl+uri2);
                        }
                        array.add(jibin);
                        System.out.println("        "+jibin);
                    }
                }catch (Exception ex){
                    if(ex instanceof IndexOutOfBoundsException){
                        continue;
                    }else {
                        ex.printStackTrace();
                    }
                }
                //其他疾病分页1
                Elements items2_2 =  doc2.body().select("ul.mulu-body2").get(0).children();
                List<String> pages = new ArrayList<>();
                for(Element e2:items2_2){
                    String jibin = e2.children().get(0).text();
                    if(jibin.length() ==6){
                        String uri2 =  e2.children().get(0).attr("href");
                        jibin = eqSixLength(baseUrl+uri2);
                    }
                    array.add(jibin);
                    System.out.println("        "+jibin);
                }
                try {

                    Elements page_e =  doc2.body().select("div.mulu-page").get(0).children().select("a");
                    for(Element e2:page_e){
                        String uri2 =  e2.attr("href");
                        pages.add(uri2);
                    }
                }catch (Exception ex){
                    if(ex instanceof IndexOutOfBoundsException){

                    }else {
                        ex.printStackTrace();
                    }
                }

                for(String url:pages){
                    Document doc3 = JSoupUtils.getDocRareCharacters(baseUrl+url);
                    Thread.sleep(100);
                    //常见疾病
                    Elements items3 =  doc3.body().select("ul.mulu-body").get(0).children();
                    for(Element e2:items3){
//                        String uri2 = e2.children().get(0).attr("href");
                        String jibin = e2.children().get(0).text();
                        if(jibin.length() ==6){
                            String uri2 =  e2.children().get(0).attr("href");
                            jibin = eqSixLength(baseUrl+uri2);
                        }
                        array.add(jibin);
                        System.out.println("        "+jibin);
                    }
                }
                keshiJson.put(ke,new ArrayList<>(array));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        FileUtil.writeUtf8String(keshiJson.toString(),"C:\\Users\\ffxue\\Desktop\\xf\\JIBINALL.json");
        System.out.println(keshiJson.toString());
    }


    private String eqSixLength(String url){
        Document doc = null;
        try {
            doc = JSoupUtils.getDocRareCharacters(url);
            String value=  doc.body().select("h1.post-title").text();
            return value;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "";
    }


    public static void main(String[] args) {
        new JSoupTest().eqSixLength("http://jb39.com/jibing/JiangMu250196.htm");
    }
}
