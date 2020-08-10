package com.common.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;

/**
 * @author xuefei
 * @description jsoup
 * @date 2019/8/4
 */
public class JSoupUtils {

    public static String getJson(String url) throws IOException {
        Connection.Response doc = Jsoup
                .connect(url)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();
        return doc.body();
    }

//    public static Document getDoc(String url) throws IOException {
//        return  Jsoup.connect(url).timeout(10000).get();
//    }
    public static Document getDocRareCharacters(String url) throws IOException {
       return Jsoup.parse(new URL(url).openStream(), "GBK", url);
    }
}
