package output.file.csv;

import java.io.*;

public class CSVUtils {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        writeFile();
    }

    private static void writeFile() throws FileNotFoundException, UnsupportedEncodingException {

        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        String charSet = "UTF-8";
        writer = new OutputStreamWriter(new FileOutputStream("C:\\Users\\admin\\Desktop\\ocyangjiatao\\aa.csv",false), charSet);
        bw = new BufferedWriter(writer);
        StringBuffer sb = new StringBuffer();
        sb.append(String.join(",", "sj菜单可能我"));
        sb.append("\n");

        try {
            //中文正常显示  utf-8bom
            bw.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB,(byte) 0xBF }));
            //写入文件
            bw.write(sb.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
