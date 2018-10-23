package net.http.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author xuefei
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/9/2016:25
 * <dependency>
 *     <groupId>org.apache.httpcomponents</groupId>
 *     <artifactId>httpclient</artifactId>
 *     <version>4.5.3</version>
 * </dependency>
 * <dependency>
 *     <groupId>org.apache.httpcomponents</groupId>
 *     <artifactId>httpmime</artifactId>
 *     <version>4.5.3</version>
 * </dependency>
 */
public class HttpClientMy {
    public static byte[] getMethod(String url)
    {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(600)
                .setSocketTimeout(600)
                .setConnectionRequestTimeout(600)
                .build();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
            if (response.getStatusLine().getStatusCode() == 200) {
                org.apache.http.HttpEntity resEntity = response.getEntity();
                byte[] message = EntityUtils.toByteArray(resEntity);
                return message;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String postMethod(String url,boolean isSSL,Map<String,String> map,String charset)
    {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try{
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            //设置参数
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Entry<String,String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(),elem.getValue()));
            }
            if(list.size() > 0){
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,charset);
                httpPost.setEntity(entity);
            }
            HttpResponse response = httpClient.execute(httpPost);
            if(response != null){
                org.apache.http.HttpEntity resEntity = response.getEntity();
                if(resEntity != null){
                    result = EntityUtils.toString(resEntity,charset);
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return result;

    }

    public void multipartFormPost(byte[] byteArr){
        //1:创建一个httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Charset charset = Charset.forName("UTF-8");//设置编码
        try {
            //2：创建http的发送方式对象，是GET还是post
            HttpPost httppost = new HttpPost("http://localhost:8080/myhome/mypage/upOutRentHourse.do");

            //3：创建要发送的实体，就是key-value的这种结构，借助于这个类，可以实现文件和参数同时上传，很简单的。
            org.apache.http.HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addTextBody("hotelID","")
                    .addTextBody("roomNo", "")
                    .addPart("file",  new ByteArrayBody(byteArr,ContentType.DEFAULT_BINARY,""))
                    .build();


            httppost.setEntity(reqEntity);

            //4：执行httppost对象，从而获得信息
            HttpResponse response = httpclient.execute(httppost);
            org.apache.http.HttpEntity resEntity = response.getEntity();

            //获得返回来的信息，转化为字符串string
            String resString = EntityUtils.toString(resEntity);
            System.out.println(resString);

        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try { httpclient.getConnectionManager().shutdown(); } catch (Exception ignore) {}
        }
    }
}
