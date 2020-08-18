package output.file.filecode;

import org.junit.Test;
import output.file.file.FindFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * 普通文件编码解析
 * @version 2011-3-10 下午10:49:41
 */
public class FileCodeUtils {



    public static void main(String[] args) throws Exception {

        // 支持的编码
        Map<String , Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String , Charset>> set = map.entrySet();
        for(Map.Entry<String , Charset> entry : set){
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        List<File> list = FindFile.find("D:\\OneDrive\\我的本地同步",3);

        list.forEach(it->{
            if(it.isFile()) {
                InputStream fileStream = null;
                try {
                    fileStream = new FileInputStream(it);
                    ByteArrayOutputStream baos = FileCodeUtils.getByteArrayOutputStream(fileStream);
                    EncodingDetectUtil encodingDetectUtil = new EncodingDetectUtil();
                    // 此处不能删除，因为fileStream只能用一次
                    fileStream = new ByteArrayInputStream(baos.toByteArray());
                    String charSet = encodingDetectUtil.detectEncoding(fileStream);
                    System.out.println(it.getPath()+"自动识别编码格式charSet：" + charSet);
                    fileStream = new ByteArrayInputStream(baos.toByteArray());
                    baos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



    }


    public static ByteArrayOutputStream getByteArrayOutputStream(InputStream fileStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(3*1024*1024);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fileStream.read(buffer)) > -1) {
            baos.write(buffer, 0, len);
            if(baos.size() == 3*1024*1024){
                break;
            }
        }
        baos.flush();
        return baos;
    }


    public static String getCharSetByFileName(String fileName){
        EncodingDetectUtil encodingDetectUtil = new EncodingDetectUtil();
        ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(fileName.getBytes());
        String charSet = encodingDetectUtil.detectEncoding(tInputStringStream);
        System.out.println("自动识别编码格式charSet：" + charSet);
        return charSet;
    }


}