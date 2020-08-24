import cn.hutool.core.lang.Validator;
import cn.hutool.http.HttpUtil;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xuefei
 * @description 11
 * @date 2019/8/8
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        Iterator<String> it = list.iterator();
        for (int i = 0;i< list.size(); i++){
            System.out.print(list.size());
            list.add("ss");
            System.out.print(list.size());
        }
    }

    public static String getIP() {

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
                return get;
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

}
