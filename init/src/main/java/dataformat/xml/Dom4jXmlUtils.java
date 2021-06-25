package dataformat.xml;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * xml工具类
 * @author shenql
 * @date 2019/9/26
 */
public class Dom4jXmlUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(Dom4jXmlUtils.class);

    private static Boolean DEBUG_MODEL = Boolean.FALSE;

    private static String XML_FILE_PATH = "";

    private static String XSL_FILE_PATH = "xpath.xsl";


//    public static void main(String[] args){
//        DEBUG_MODEL = Boolean.TRUE;
//        XML_FILE_PATH = "E:\\idss\\3.0\\holmes\\Bolt\\src\\test\\resources\\note.xml";
//        XSL_FILE_PATH = XmlUtils.class.getClassLoader().getResource("xpath.xsl").getPath().toString();
//        XmlResultSet fileResultSet = parse(null,    XSL_FILE_PATH,0L);
//        System.out.println("--------COLUMN----------");
//        for(Column column:fileResultSet.getRecordMetaData().getColumn()){
//            String name = ((MetadataColumn) column).getMetadata().getName();
//            name = StringUtils.removeEnd(name, "\r");
//            System.out.println(name);
//        }
//
//        System.out.println("--------SAMPLES----------");
//        List<Record> samples = fetchByRange(fileResultSet,1,null);
//        List<Record> datas = new ArrayList<>();
//        Record defaultRecord = new DefaultRecord();
//        if(!samples.isEmpty()){
//            Record record = samples.get(0);
//            for(Column column:record.getColumn()){
//                String name = column.getName();
//                name = StringUtils.removeEnd(name, "\r");
//                System.out.println(name+"-->"+column.getRawData());
//                StringColumn stringColumn = new StringColumn(column.getRawData()+" updated");
//                stringColumn.setName(name);
//                defaultRecord.addColumn(stringColumn);
//            }
//        }
//        datas.add(defaultRecord);
//
//        System.out.println("--------WRITE TO XML----------");
//        write(null,"","",datas);
//    }

    public static void main(final String[] args) {
        final Dom4jXmlUtils test = new Dom4jXmlUtils();
        try {
            test.testGeStr();
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取文件的xml对象，然后获取对应的根节点root
     */
    public void testGeStr() throws Exception {
        final Document document = DocumentHelper.parseText("<report>\n" +
                "    <vendor>绿盟科技</vendor>\n" +
                "    <product>RSAS</product>\n" +
                "    <version>6.0.2.0</version>\n" +
                "    <sysvul_version>6.0.0.1</sysvul_version>\n" +
                "    <task>\n" +
                "        <id>1</id>\n" +
                "        <name>\n" +
                "           扫描【192.168.1.1;192.168.1.2】\n" +
                "        </name>\n" +
                "        <targets>\n" +
                "           192.168.1.1;192.168.1.2\n" +
                "        </targets>\n" +
                "        <task_type>1</task_type>\n" +
                "        <vuln_template>0</vuln_template>\n" +
                "        <start_time>2014-10-01 12:00:00</start_time>\n" +
                "        <end_time>2014-10-01 12:01:00</end_time>\n" +
                "    </task>\n" +
                "    <failed_hosts>\n" +
                "        <failed_host>\n" +
                "            <ip></ip>\n" +
                "            <reason></reason>\n" +
                "        </failed_host>\n" +
                "    </failed_hosts>\n" +
                "    <targets>\n" +
                "        <target>\n" +
                "            <ip>192.168.1.1</ip>\n" +
                "            <vuln_scanned>\n" +
                "                <vuln>\n" +
                "                    <vul_id>vul_id</vul_id>\n" +
                "                    <port>port</port>\n" +
                "                    <protocol>protocol</protocol>\n" +
                "                    <mess_string>\n" +
                "                       mess_string\n" +
                "                    </mess_string>\n" +
                "                </vuln>\n" +
                "            </vuln_scanned>\n" +
                "            <vuln_detail>\n" +
                "                <vuln>\n" +
                "                    <vul_id>vul_id</vul_id>\n" +
                "                    <plugin_id>plugin_id</plugin_id>\n" +
                "                    <name>i18n_name</name>\n" +
                "                    <threat_category>plugin_id</threat_category >\n" +
                "                    <cve_id>cve_id</cve_id>\n" +
                "                    <nsfocus_id>nsfocus_id</nsfocus_id>\n" +
                "                    <bugtraq_id>bugtraq_id</bugtraq_id>\n" +
                "                    <risk_points>risk_points</risk_points>\n" +
                "                    <solution>\n" +
                "                       solution\n" +
                "                    </solution>\n" +
                "                    <description>\n" +
                "                       description\n" +
                "                    </description>\n" +
                "                </vuln>\n" +
                "            </vuln_detail>\n" +
                "            <password_results>\n" +
                "                <password_result>\n" +
                "                    <type>SMB</type>\n" +
                "                    <ip>1.1.1.1</ip>\n" +
                "                    <username>username</username>\n" +
                "                    <password>password</password>\n" +
                "                </password_result>\n" +
                "            </password_results>\n" +
                "            <appendix_info>\n" +
                "                <info>\n" +
                "                    <info_name>\n" +
                "                       info_name\n" +
                "                    </info_name>\n" +
                "                    <record_result_name>\n" +
                "                        <name>\n" +
                "                           item_name\n" +
                "                        </name>\n" +
                "                    </record_result_name>\n" +
                "                    <record_results>\n" +
                "                        <result>\n" +
                "                            <vule>\n" +
                "                               item_name\n" +
                "                            </vule>\n" +
                "                        </result>\n" +
                "                    </record_results>\n" +
                "                    <record_results/>\n" +
                "                </info>\n" +
                "            </appendix_info>\n" +
                "        </target>\n" +
                "    </targets>\n" +
                "</report>");// 获取document对象,如果文档无节点，则会抛出Exception提前结束
        final Element root = document.getRootElement();// 获取根节点
        Map<String,List<String>> columns = new LinkedHashMap<>();
        getNodes(root,columns);// 从根节点开始遍历所有节点
        System.out.println("xzx");

    }

    /**
     * 获取文件的xml对象，然后获取对应的根节点root
     */
    public void testGetRoot() throws Exception {
        final SAXReader sax = new SAXReader();// 创建一个SAXReader对象
        final File xmlFile = new File("C:\\Users\\admin\\Desktop\\scan1_202007231409 - 副本.xml");// 根据指定的路径创建file对象
        final Document document = sax.read(xmlFile);// 获取document对象,如果文档无节点，则会抛出Exception提前结束
        final Element root = document.getRootElement();// 获取根节点
        Map<String,List<String>> columns = new LinkedHashMap<>();
        getNodes(root,columns);// 从根节点开始遍历所有节点
        System.out.println("xzx");

    }

    /**
     * 从指定节点Element node开始,递归遍历其所有子节点
     */
    public void getNodes(final Element node,Map<String,List<String>> columns) {
        System.out.println("-------开始新节点-------------");

        // 当前节点的名称、文本内容和属性
        System.out.println("当前节点名称：" + node.getName());// 当前节点名称
        System.out.println("当前节点的内容：" + node.getTextTrim());// 当前节点内容
        if(StringUtils.isNotEmpty(node.getTextTrim())){
            if(!columns.containsKey(node.getPath())){
                columns.put(node.getPath(),new ArrayList<>());
            }
            columns.get(node.getPath()).add(node.getTextTrim());
        }
        final List<Attribute> listAttr = node.attributes();// 当前节点的所有属性
        for (final Attribute attr : listAttr) {// 遍历当前节点的所有属性
            final String name = attr.getName();// 属性名称
            final String value = attr.getValue();// 属性的值
            System.out.println("属性名称：" + name + "---->属性值：" + value);
        }

        // 递归遍历当前节点所有的子节点
        final List<Element> listElement = node.elements();// 所有一级子节点的list
        for (final Element e : listElement) {// 遍历所有一级子节点
            getNodes(e,columns);// 递归
        }
    }



}
