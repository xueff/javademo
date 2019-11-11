package zmoney.gp;

import io.vertx.core.json.JsonObject;
import net.http.httpclient.HttpsUtils;
import output.file.FileUtils;


public class DongFangCaiFu {
    static int page = 1;
    static int pageSize = 100;
    static String fs = "m:0+t:6,m:0+t:13,m:0+t:80";
    //表格栏位   f12:code F14名称
    static String fields = "f12,f14";

    static String 深证 = "http://55.push2.eastmoney.com/api/qt/clist/get?fs="+"m:0+t:6,m:0+t:13,m:0+t:80"+"&pz="+pageSize+"&fields=f12,f14"+"&pn=";
    static String 上证 = "http://70.push2.eastmoney.com/api/qt/clist/get?fs="+"m:1+t:2"+"&pz="+pageSize+"&fields=f12,f14"+"&pn=";

    public static void main(String[] args) {
        // st=1

        for(int i=0;i<1000;i++) {
            String h = HttpsUtils.get(上证 + (i+1));
            System.out.println("i="+i+"============"+h);
            JsonObject json = new JsonObject(h);
            JsonObject maps = json.getJsonObject("data").getJsonObject("diff");
            if(maps.size()<=0) break;;
            StringBuffer st = new StringBuffer();
            maps.forEach(it->{
                JsonObject o = (JsonObject)it.getValue();
                    st.append(",").append(o.getString("f12")).append("  ").append(o.getString("f14")).append("\n");
            });


            FileUtils.createOrAppendFile(st.toString(),"D://gp.txt");
        }
    }
}
