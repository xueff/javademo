package file_system.file.tika;

import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.xml.XMLParser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BigFile {
    public static void main(String[] args) {
//        parseFile(new File("Sessions.xml"));

    }
    public static void q222(String[] args) {
        Path outputFile = Paths.get("output.txt"); // Paths.get() if not using Java 11
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(Files.newOutputStream(outputFile));
            BodyContentHandler content = new BodyContentHandler(printWriter);
            Path inputFile = Paths.get("Sessions.xml");
            TikaInputStream inputStream = TikaInputStream.get(inputFile);

            AutoDetectParser parser = new AutoDetectParser();
            Metadata meta = new Metadata();
            ParseContext context = new ParseContext();

            parser.parse(inputStream, content, meta, context);
            System.out.println(content.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String parseXMLFile(File file){
        Parser parser = new AutoDetectParser();
        InputStream input = null;
        try{
            BodyContentHandler handler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            FileInputStream inputstream = new FileInputStream(new File("Sessions.xml"));
            ParseContext pcontext = new ParseContext();

            //Xml parser
            XMLParser xmlparser = new XMLParser();
            xmlparser.parse(inputstream, handler, metadata, pcontext);
            System.out.println("Contents of the document:" + handler.toString());
            System.out.println("Metadata of the document:");
            String[] metadataNames = metadata.names();

            for(String name : metadataNames) {
                System.out.println(name + ": " + metadata.get(name));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(input!=null)input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    @Test
    public void write() {
        long st = System.currentTimeMillis();
        TikaWriter printWriter = null;
        try {
            printWriter = new TikaWriter();
            BodyContentHandler content = new BodyContentHandler(printWriter);
            Path inputFile = Paths.get("Sessions.xml");
            TikaInputStream inputStream = TikaInputStream.get(inputFile);

            AutoDetectParser parser = new AutoDetectParser();
            Metadata metadata = new Metadata();
            ParseContext context = new ParseContext();

            parser.parse(inputStream, content, metadata, context);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - st);
    }
}
