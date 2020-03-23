package utils;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import sun.security.util.Resources;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class PropertiesUtils {
    //可读jar
    public Properties read() throws UnsupportedEncodingException {
        Properties p = new Properties();
        //A :
        // InputStream in = new BufferedInputStream(new FileInputStream(filepath));
        //B:
        InputStream in = Resources.class.getResourceAsStream("资源name");
        InputStreamReader ins = new InputStreamReader(in, "UTF-8");
        try {
            p.load(ins);
        } catch (IOException e) {
            throw new RuntimeException("系统内部错误。");
        }
        String userOriPassword = p.getProperty("");
        return p;
    }




    private static PropertiesConfiguration properites;

    public static PropertiesConfiguration getPropertiesConfig(){
        if(properites == null) {
            try {
                properites = new Configurations().properties("application.properties");
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }
        return properites;
    }


    /**
     * application.properties文件 utf-8  乱码
     */
    private static final Properties PROPERTIES = new Properties();
    public void getUtf8Properties(){
        if ( PROPERTIES.size() == 0 ){
            try {
                InputStream in = null;
                FileOutputStream oFile = null;
                in = new BufferedInputStream(new FileInputStream("config/application.properties"));
                PROPERTIES.load(new InputStreamReader(in, "utf-8"));
                for (String key : PROPERTIES.stringPropertyNames()) {
                    System.out.println(key + ":" + PROPERTIES.getProperty(key));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
