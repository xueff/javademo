package utils;

import sun.security.util.Resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtils {
    //可读jar
    public Properties read() throws UnsupportedEncodingException {
        Properties p = new Properties();
        InputStream in = Resources.class.getResourceAsStream("");
        InputStreamReader ins = new InputStreamReader(in, "UTF-8");
        try {
            p.load(ins);
        } catch (IOException e) {
            throw new RuntimeException("系统内部错误。");
        }
        String userOriPassword = p.getProperty("");
        return p;
    }
}
