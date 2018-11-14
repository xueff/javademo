package net.http.httpclient;

import org.apache.commons.collections.MapUtils;
import org.apache.http.*;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 持同一session的HttpClient工具类
 * @date 2018/11/910:04
 */
public class HttpClientBauduTest {
    private static String cookie = "Cookie:BAIDUID=B026398CF55B13A7B982E9175E2969AD:FG=1; BIDUPSID=B026398CF55B13A7B982E9175E2969AD; PSTM=1528711491; PANWEB=1; MCITY=-224%3A; STOKEN=d8936a603929af0c1cda16dab80bedf90f60a40a946d83cdb172d783185357b6; SCRC=18b13dd1d7d9f967891d20a87e994eb0; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; delPer=0; H_PS_PSSID=22778_1447_21088_20697_26350; PSINO=6; cflag=15%3A3; Hm_lvt_7a3960b6f067eb0085b7f96ff5e660b0=1541557237,1541728741,1541749506,1541750460; PANPSC=13798048157591184379%3AWaz2A%2F7j1vV0OT6TDER6y3vBcZceSPnww3PA7LzFeaIkGiJzAoFozSj3G8DSYFfd%2FhsCeIvq9iDL%2Ff4hk5kZPvn19cO6oVh4v2Arrvb%2BpkTSsiiu0H2zkv3Y09EHLFo3ZVZwc%2F2afcw%2BMIHUP9XFjJoHlHyGm%2BkQNY8Ls0pwmFUHujlSA%2BgxQbBxzfcLsdyQ; BDCLND=CvQkfY9rtqww0sPhneHz7IMcPJtvOvi%2FJFzwxOp794E%3D; Hm_lpvt_7a3960b6f067eb0085b7f96ff5e660b0=1541750602";

    private static String domain = ".baidu.com";

    public static void saveCookie(){
        String[] cookies = cookie.substring(7).split(";");
        for(int i=0;i<cookies.length;i++){
            String[] each = cookies[i].split("=");
            HttpClientKeepSession.addCookie(each[0].trim(),each[1].trim(),domain,"/");
        }
    }

    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;
    static {
        try {
            builder = new SSLContextBuilder();
            // 全部信任 不做身份鉴定
            builder.loadTrustMaterial(null, new TrustStrategy() {
                @Override
                public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
                    return true;
                }
            });
            sslsf = new SSLConnectionSocketFactory(builder.build(), new String[]{"SSLv2Hello", "SSLv3", "TLSv1", "TLSv1.2"}, null, NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register(HTTP, new PlainConnectionSocketFactory())
                    .register(HTTPS, sslsf)
                    .build();
            cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(200);//max connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static Map<String,String> getHerder(HttpGet httpGet){

        String header = "Accept:application/json, text/javascript, */*; q=0.01\n" +
                "Accept-Encoding:gzip, deflate, br\n" +
                "Accept-Language:zh-CN,zh;q=0.9\n" +
                "Cache-Control:no-cache\n" +
                "Connection:keep-alive\n" +
                "Cookie:BAIDUID=B026398CF55B13A7B982E9175E2969AD:FG=1; BIDUPSID=B026398CF55B13A7B982E9175E2969AD; PSTM=1528711491; PANWEB=1; MCITY=-224%3A; STOKEN=d8936a603929af0c1cda16dab80bedf90f60a40a946d83cdb172d783185357b6; SCRC=18b13dd1d7d9f967891d20a87e994eb0; BDORZ=B490B5EBF6F3CD402E515D22BCDA1598; Hm_lvt_7a3960b6f067eb0085b7f96ff5e660b0=1541557237,1541728741,1541749506,1541750460; PANPSC=13798048157591184379%3AWaz2A%2F7j1vV0OT6TDER6y3vBcZceSPnww3PA7LzFeaIkGiJzAoFozSj3G8DSYFfd%2FhsCeIvq9iDL%2Ff4hk5kZPvn19cO6oVh4v2Arrvb%2BpkTSsiiu0H2zkv3Y09EHLFo3ZVZwc%2F2afcw%2BMIHUP9XFjJoHlHyGm%2BkQNY8Ls0pwmFUHujlSA%2BgxQbBxzfcLsdyQ; BDCLND=CvQkfY9rtqww0sPhneHz7IMcPJtvOvi%2FJFzwxOp794E%3D; H_PS_PSSID=22778_1447_21088_26350; delPer=0; PSINO=3; Hm_lpvt_7a3960b6f067eb0085b7f96ff5e660b0=1541753192\n" +
                "Host:pan.baidu.com\n" +
                "Pragma:no-cache\n" +
                "Referer:https://pan.baidu.com/s/1QHH_nWNAKjwsTr_NKtpa0Q\n" +
                "User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
            String[] headers = header.split("\\n");
            Map<String,String> headerMap = new HashMap<>();
        for(int i=0;i<headers.length;i++){
            int each = headers[i].indexOf(":");
            httpGet.addHeader(headers[i].substring(0,each),headers[i].substring(each+1));
            headerMap.put(headers[i].substring(0,each),headers[i].substring(each+1));
        }
//        System.out.println(headerMap);
        return headerMap;
    }

    public static void main(String[] args) throws IOException {
        String urlpre ="https://pan.baidu.com/share/list?uk=1590217019&shareid=1350927573&order=server_filename&desc=0&showempty=0&web=1&page=1&num=100&dir=";
        String urlnext = "&t=0.7027388114291906&channel=chunlei&web=1&app_id=250528&bdstoken=null&logid=MTU0MTc1MDY0MTA2ODAuMDE1MTk4MjM4NzU2OTUyNDc2&clienttype=0";
        String url = "";
        try {
            String dir = java.net.URLEncoder.encode("/2018/JAVA高级/VIP专享",   "utf-8");
            url += urlpre+dir+urlnext;
            System.out.println(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        HttpGet httpGet = new HttpGet(url);
        //============方法一=====================
        getHerder(httpGet);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf)
                .setConnectionManager(cm)
                .setConnectionManagerShared(true)
                .build();
        // 创建http请求(get方式)
        //CloseableHttpResponse response = httpclient.execute(httpGet);

        //============方法②=====================
        getHerder(httpGet);
        CookieStore cookieStore = new BasicCookieStore();
        httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        // 创建http请求(get方式)
//        CloseableHttpResponse response = httpclient.execute(httpGet);
//        System.out.println(EntityUtils.toString(response.getEntity()));
//        System.out.println(cookieStore.getCookies());

        //===================方法三========================
        saveCookie();
         String result=EntityUtils.toString( HttpClientKeepSession.get(url).getEntity());
        System.out.println(result);



//        try {
//            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                System.out.println("Response content length: " + entity.getContentLength());
//                System.out.println(EntityUtils.toString(entity));
//                EntityUtils.consume(entity);
//            }
//        } finally {
//            response.close();
//        }




        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
