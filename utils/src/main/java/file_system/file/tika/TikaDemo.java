package file_system.file.tika;

import org.apache.tika.Tika;
import org.apache.tika.detect.AutoDetectReader;
import org.apache.tika.detect.Detector;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TikaDemo {

    //TODO 如何写到 /dev/null ??
    @Test
    public void getMetadaByBigFile() {
        long st = System.currentTimeMillis();
        TikaWriter printWriter = null;
        try {
            printWriter = new TikaWriter();
            BodyContentHandler content = new BodyContentHandler(printWriter);
            Path inputFile = Paths.get("r.html");
            TikaInputStream inputStream = TikaInputStream.get(inputFile);

            AutoDetectParser parser = new AutoDetectParser();
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();

            parser.parse(inputStream, content, metadata, context);
            for(String name :  metadata.names()) {
                System.out.println(name + ": " + metadata.get(name));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - st);
    }


    @Test
    public void getMetada(){
        String file = "handbook-sdd.pdf";
        Metadata metadata =getMetadaInfo(file);
        for(String name : metadata.names()) {
            System.out.println(name + ": " + metadata.get(name));
        }
        System.out.println("=======================================");
//        System.out.println(getfiletype(file));
        System.out.println("=======================================");
        System.out.println(getrealfiletype(file));

    }

    /**
     * 准 详细：2067ms  很慢
     * @param file
     * @return
     */
    public Metadata getMetadaInfo(String file){
        long st = System.currentTimeMillis();
        Metadata metadata = new Metadata();   //empty metadata object
        try {
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler();

            FileInputStream inputstream = new FileInputStream(file);
            ParseContext context = new ParseContext();
            parser.parse(inputstream, handler, metadata, context);
            metadata.names();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - st);
        return  metadata;
    }

    /**
     * 不准：快 37ms
     * @param file
     * @return
     */
    public String getfiletype(String file){
        long st = System.currentTimeMillis();
        File f = new File(file);
        AutoDetectReader dr = null;
        Tika tika = new Tika();  //创建一个Tika类
        //利用Tika的detect方法检测文件的实际类型
        System.out.println("filetype:"+tika.detect(file));
        String type =  tika.detect(file);
        System.out.println(System.currentTimeMillis() - st);
        return type;
    }

    /**
     * 准：快 47ms
     * @param file
     * @return
     */
    public String getrealfiletype(String file) {
        long st = System.currentTimeMillis();
        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            is = new FileInputStream(new File(file));
            bis = new BufferedInputStream(is);
            AutoDetectParser parser = new AutoDetectParser();
            Detector detector = parser.getDetector();
            Metadata md = new Metadata();
            md.add(Metadata.RESOURCE_NAME_KEY, file);
            MediaType mediaType = detector.detect(bis, md);
            System.out.println(System.currentTimeMillis() - st);
            return mediaType.toString();//返回的就是文件类型
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
