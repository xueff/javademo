import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.XmlUtil;
import cn.hutool.http.HttpUtil;
import io.vertx.core.json.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;

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
        Document d =  XmlUtil.parseXml("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "\n" +
                "<report> \n" +
                "  <vendor>绿盟科技</vendor>  \n" +
                "  <product>RSAS</product>  \n" +
                "  <version>6.0.2.0</version>  \n" +
                "  <sysvul_version>6.0.0.1</sysvul_version>  \n" +
                "  <task> \n" +
                "    <id>1</id>  \n" +
                "    <name>扫描【192.168.1.1;192.168.1.2&gt;</name>  \n" +
                "    <targets>192.168.1.1;192.168.1.2</targets>  \n" +
                "    <task_type>1</task_type>  \n" +
                "    <vuln_template>0</vuln_template>  \n" +
                "    <start_time>2014-10-01 12:00:00</start_time>  \n" +
                "    <end_time>2014-10-01 12:01:00</end_time> \n" +
                "  </task>  \n" +
                "  <failed_hosts> \n" +
                "    <failed_host> \n" +
                "      <ip>111</ip>  \n" +
                "      <reason/> \n" +
                "    </failed_host> \n" +
                "  </failed_hosts>  \n" +
                "  <targets> \n" +
                "    <target> \n" +
                "      <ip>192.168.1.1</ip>  \n" +
                "      <vuln_scanned> \n" +
                "        <vuln> \n" +
                "          <vul_id>vul_id</vul_id>  \n" +
                "          <port>port</port>  \n" +
                "          <protocol>protocol</protocol>  \n" +
                "          <mess_string>mess_string</mess_string> \n" +
                "        </vuln> \n" +
                "      </vuln_scanned>  \n" +
                "      <vuln_detail> \n" +
                "        <vuln> \n" +
                "          <vul_id>vul_id</vul_id>  \n" +
                "          <plugin_id>plugin_id</plugin_id>  \n" +
                "          <name>i18n_name</name>  \n" +
                "          <threat_category>plugin_id</threat_category>  \n" +
                "          <cve_id>cve_id</cve_id>  \n" +
                "          <nsfocus_id>nsfocus_id</nsfocus_id>  \n" +
                "          <bugtraq_id>bugtraq_id</bugtraq_id>  \n" +
                "          <risk_points>risk_points</risk_points>  \n" +
                "          <solution>solution</solution>  \n" +
                "          <description>description</description> \n" +
                "        </vuln> \n" +
                "      </vuln_detail>  \n" +
                "      <!--口令猜测结果-->  \n" +
                "      <password_results> \n" +
                "        <password_result> \n" +
                "          <type>SMB</type>  \n" +
                "          <ip>1.1.1.1</ip>  \n" +
                "          <username>username</username>  \n" +
                "          <password>password</password> \n" +
                "        </password_result> \n" +
                "      </password_results>  \n" +
                "      <appendix_info> \n" +
                "        <info> \n" +
                "          <info_name>info_name</info_name>  \n" +
                "          <record_result_name> \n" +
                "            <name>item_name</name> \n" +
                "          </record_result_name>  \n" +
                "          <record_results> \n" +
                "            <result> \n" +
                "              <vule>item_name</vule> \n" +
                "            </result> \n" +
                "          </record_results>  \n" +
                "          <record_results/> \n" +
                "        </info> \n" +
                "      </appendix_info> \n" +
                "    </target> \n" +
                "  </targets> \n" +
                "</report>\n");


        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        list.add("a");
        List<String> it = list.subList(0,2);
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
