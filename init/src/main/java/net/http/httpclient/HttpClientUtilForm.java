package net.http.httpclient;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtilForm {
    static Logger logger = LoggerFactory.getLogger(HttpClientUtilForm.class);
    private static final String HTTP = "http";
    private static final String HTTPS = "https";
    private static SSLConnectionSocketFactory sslsf = null;
    private static PoolingHttpClientConnectionManager cm = null;
    private static SSLContextBuilder builder = null;
    public static CloseableHttpClient httpClient = null;
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
            cm.setMaxTotal(20);//max connection
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CloseableHttpClient getHttpClient() {
        if (httpClient == null){
            httpClient = HttpClients.custom()
                    .setSSLSocketFactory(sslsf)
                    .setConnectionManager(cm)
                    .setConnectionManagerShared(true)
                    .setMaxConnTotal(20)//连接池最大连接数
                    .setDefaultRequestConfig(
                            RequestConfig.custom()
                                    .setConnectTimeout(10000)
                                    .setSocketTimeout(10000)//请求超时时间
                                    .build()
                    )
                    .build();
        }
        return httpClient;
    }

    public static void main(String[] args) {
       HttpClientUtilForm.httpGet("https://172.16.0.34:8084//1575256282801/default.zip");
    }

    /**
     * 发送http get请求
     */
    public static String httpGet(String url){
        return request(new HttpGet(url));
    }

    /**
     * 发送 http put 请求
     * @param url
     * @return
     */
    public static String putForm(String url,Map<String, String> param){
        HttpPut httpput = new HttpPut(url);
        //             设置请求参数
        if (!param.isEmpty()) {
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            for (Map.Entry<String, String> entry : param.entrySet()) {
                formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            httpput.setEntity(urlEncodedFormEntity);
        }
        return request(httpput);
    }
    /**
     * 发送http delete请求
     */
    public static String httpDelete(String url){
        return request(new HttpDelete(url));
    }

    private static String request(HttpUriRequest request){
        CloseableHttpClient closeableHttpClient = getHttpClient();
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = closeableHttpClient.execute(request);
            HttpEntity entity = httpResponse.getEntity();
            return EntityUtils.toString(entity, "utf-8");

        } catch (Exception e) {
            e.printStackTrace();
            try {
                closeableHttpClient.close();
                httpClient = null;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }finally{
            try {
                httpResponse.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";

    }

}