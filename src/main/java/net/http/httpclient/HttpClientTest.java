package net.http.httpclient;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/9/219:25
 */
public class HttpClientTest{

    private String url = "https://192.168.1.101/";
    private String charset = "utf-8";
    private HttpClientMy httpClientUtil = null;

    public HttpClientTest(){
        httpClientUtil = new HttpClientMy();
    }

    public void test(){
        String httpOrgCreateTest = url + "httpOrg/create";
        Map<String,String> createMap = new HashMap<String,String>();
        createMap.put("authuser","*****");
        createMap.put("authpass","*****");
        createMap.put("orgkey","****");
        createMap.put("orgname","****");
        String httpOrgCreateTestRtn = httpClientUtil.postMethod(httpOrgCreateTest,true,createMap,charset);
        System.out.println("result:"+httpOrgCreateTestRtn);
    }

    public static void main(String[] args){
        HttpClientTest main = new HttpClientTest();
        main.test();
    }
}
