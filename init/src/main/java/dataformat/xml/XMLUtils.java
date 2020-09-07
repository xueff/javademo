package dataformat.xml;

import org.apache.commons.lang3.StringUtils;
import  org.w3c.dom.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

/**
 * xml工具类
 * @author shenql
 * @date 2019/9/26
 */
public class XmlUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);


    public static void main(final String[] args) {
        final XmlUtils test = new XmlUtils();
        try {
            test.testGetRoot();
        } catch (final Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 获取文件的xml对象，然后获取对应的根节点root
     */
    public void testGetRoot() throws Exception {

        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
        Document document = docBuilder.parse(new File("C:\\Users\\admin\\Desktop\\scan1_202007231409 - 副本.xml"));
        Map<String,List<String>> columns = new LinkedHashMap<>();
        doSomething("",document.getDocumentElement(),columns);
        System.out.println("xzx");

    }

    /**
     * 从指定节点Element node开始,递归遍历其所有子节点
     */
    public static void doSomething(String preName,Node node, Map<String,List<String>> columns) {
        // do something with the current node instead of System.out
        System.out.println("当前节点名称：" + preName +"/"+ node.getNodeName());
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node currentNode = nodeList.item(i);
            if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
                //calls this method for all the children which is Element
                doSomething( preName +"/"+ node.getNodeName(),currentNode,columns);
            }else {
                System.out.println("当前节点的内容：" + currentNode.getTextContent());
                if (StringUtils.isNotEmpty(currentNode.getTextContent())) {
                    if (!columns.containsKey(preName +"/"+ node.getNodeName())) {
                        columns.put(preName +"/"+ node.getNodeName(), new ArrayList<>());
                    }
                    columns.get(preName +"/"+ node.getNodeName()).add(currentNode.getTextContent());
                }
            }
        }
    }



}
