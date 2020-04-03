package output.file;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;

/**
 *
 * 文件追加内容
 * @version 2011-3-10 下午10:49:41
 */
public class FileCodeUtils {



    public static void main(String[] args) throws Exception {

        Map<String , Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String , Charset>> set = map.entrySet();
        for(Map.Entry<String , Charset> entry : set){
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }



        File file = new File("");
        InputStream fileStream = new FileInputStream(file);
        ByteArrayOutputStream baos = FileCodeUtils.getByteArrayOutputStream(fileStream);
        EncodingDetectUtil encodingDetectUtil = new EncodingDetectUtil();
        // 此处不能删除，因为fileStream只能用一次
        fileStream = new ByteArrayInputStream(baos.toByteArray());
        String charSet = encodingDetectUtil.detectEncoding(fileStream);
        System.out.println("自动识别编码格式charSet：" + charSet);
        fileStream = new ByteArrayInputStream(baos.toByteArray());
        baos.close();
    }


    public static ByteArrayOutputStream getByteArrayOutputStream(InputStream fileStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fileStream.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
        }
        baos.flush();
        return baos;
    }


}