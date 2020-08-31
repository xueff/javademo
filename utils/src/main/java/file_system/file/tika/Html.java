package file_system.file.tika;

import com.google.common.io.Files;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.html.HtmlParser;
import org.apache.tika.sax.BodyContentHandler;

import org.apache.tika.exception.TikaException;
import org.apache.tika.sax.ExpandedTitleContentHandler;
import org.apache.tika.metadata.Metadata;

/**
 * 文件 内容 抽取
 */
public class Html {
    public static String extractHtml(File file) throws IOException {
        byte[] bytes = Files.toByteArray(file);
        AutoDetectParser tikaParser = new AutoDetectParser();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        SAXTransformerFactory factory = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        TransformerHandler handler;
        try {
            handler = factory.newTransformerHandler();
        } catch (TransformerConfigurationException ex) {
            throw new IOException(ex);
        }
        handler.getTransformer().setOutputProperty(OutputKeys.METHOD, "html");
        handler.getTransformer().setOutputProperty(OutputKeys.INDENT, "yes");
        handler.getTransformer().setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        handler.setResult(new StreamResult(out));
        ExpandedTitleContentHandler handler1 = new ExpandedTitleContentHandler(handler);
        try {
            tikaParser.parse(new ByteArrayInputStream(bytes), handler1, new Metadata());
        } catch (SAXException | TikaException ex) {
            throw new IOException(ex);
        }
        return new String(out.toByteArray(), "UTF-8");
    }
    public static void main(final String[] args) throws Exception {
        // Tika默认是10*1024*1024，这里防止文件过大导致Tika报错
        BodyContentHandler handler = new BodyContentHandler(1024 * 1024 * 10);
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(new File("C:\\\\Users\\\\ffxue\\\\Desktop\\\\aa.html"));
        ParseContext pcontext = new ParseContext();

        // 解析HTML文档时应由超类AbstractParser的派生类HtmlParser实现
        HtmlParser msofficeparser = new HtmlParser();
        msofficeparser.parse(inputstream, handler, metadata, pcontext);
        // 获取HTML文档的内容
        System.out.println("HTML文档内容:" + handler.toString());

        // 获取HTML文档的元数据
        System.out.println("HTML文档元数据:");
        String[] metadataNames = metadata.names();

        for (String name : metadataNames) {
//            System.out.println(name + " : " + metadata.get(name));
            System.out.println( metadata.get(name));
        }
    }

}
