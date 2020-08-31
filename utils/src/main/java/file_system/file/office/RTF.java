package file_system.file.office;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyleContext;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RTF {
    /**
     * @description 从rtf文件中读取内容，使用java 内置函数对rtf 进行解析
     * @param filePath
     * @return
     */
    public static String getTextFromRtf(String filePath) {
        String result = null;
        File file = new File(filePath);
        try {
            DefaultStyledDocument styledDoc = new DefaultStyledDocument((new StyleContext()));
            // 创建文件输入流
            InputStream streamReader = new FileInputStream(file);
            new RTFEditorKit().read(streamReader, styledDoc, 0);
            //以 ISO-8859-1的编码形式获取字节byte[], 并以 GBK 的编码形式生成字符串
            result = new String(styledDoc.getText(0, styledDoc.getLength()).getBytes("ISO8859-1"),"GBK");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        String result = getTextFromRtf("C:\\Users\\ffxue\\Desktop\\代理申请表.rtf");
        System.out.println(result);
    }



}
