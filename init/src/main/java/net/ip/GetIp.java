package net.ip;

import cn.hutool.core.lang.Validator;
import cn.hutool.http.HttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class GetIp {
    public static String getRemoteHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    /**
     * 获取服务器IP地址
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getPublicIP() {
        String res = HttpUtil.get("http://icanhazip.com");
        res = res.trim().replace("\n","");
        if(!Validator.isIpv4(res)){
            URL url = null;
            URLConnection urlconn = null;
            BufferedReader br = null;
            try {
                url = new URL("http://icanhazip.com");//爬取的网站是百度搜索ip时排名第一的那个
                urlconn = url.openConnection();
                br = new BufferedReader(new InputStreamReader(
                        urlconn.getInputStream()));
                String buf = "";
                String get= "";
                while ((buf = br.readLine()) != null) {
                    get+=buf;
                }
                int where,end;
                for(where=0;where<get.length()&&get.charAt(where)!='[';where++);
                for(end=where;end<get.length()&&get.charAt(end)!=']';end++);
                get=get.substring(where+1,end);

                res =  Validator.isIpv4(get.trim())?get:null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(getRemoteHost());
    }

}
