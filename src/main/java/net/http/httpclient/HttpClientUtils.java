package net.http.httpclient;

import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.*;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/11/916:19
 */
public class HttpClientUtils {
    public  static CloseableHttpClient httpClient = null;
    public  static HttpClientContext context = null;
    public  static CookieStore cookieStore = null;
    public  static RequestConfig requestConfig = null;

    static {
        init();
    }

    private static void init() {
//保存cookie
//        CookieStore cookieStore = new BasicCookieStore();
//        httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
        context = HttpClientContext.create();
        cookieStore = new BasicCookieStore();
        // 配置超时时间（连接服务端超时1秒，请求数据返回超时2秒）
        requestConfig = RequestConfig.custom().setConnectTimeout(120000).setSocketTimeout(60000)
                .setConnectionRequestTimeout(60000).build();
        // 设置默认跳转以及存储cookie
        httpClient = HttpClientBuilder.create()
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .setRedirectStrategy(new DefaultRedirectStrategy()).setDefaultRequestConfig(requestConfig)
                .setDefaultCookieStore(cookieStore).build();
    }
    
}
