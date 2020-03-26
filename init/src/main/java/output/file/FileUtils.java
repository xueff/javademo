package output.file;

import org.apache.commons.lang.StringUtils;

import java.io.*;

/**
 *
 * 文件追加内容
 * @version 2011-3-10 下午10:49:41
 */
public class FileUtils {



    public static void main(String[] args) throws Exception {
        FileUtils.getFileCode("C:\\Users\\admin\\Desktop\\ocyangjiatao\\新建文本文档.txt");
    }

    /**
     * 向文件追加内容
     */
    public static void createOrAppendFile(String content,String fileName){
        if(StringUtils.isEmpty(content)){
            return;
        }
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, true);
            writer.write(content);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if(writer != null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param cfg
     * @return
     */
    public static String readFile(File cfg) {
        StringBuilder cxt = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(cfg), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                cxt.append(line);
            }
            bufferedReader.close();
            inputStreamReader.close();

        } catch (Exception e) {
            System.out.println("读取文件失败");
        }

        return cxt.toString();
    }



    public static void rename(){
        File file = new File("d://text22.txt");
        File file2 = new File("d://text2.txt");
        try{
            if(file2.exists()){
                file2.delete();
            }
            boolean success = file.renameTo(new File("d://text23.txt"));
            System.out.println(success);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void createFileForce(String path){
        File file = new File(path);
        if(file.exists()) {
            return;
        }

        file.getParentFile().mkdirs();
        file.getParentFile().mkdirs();
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void filePermission() {
        try {
            File file = new File("D:/log/operate_20191112_1.log");
            System.out.println(file.length());
            if (file.createNewFile()){
                System.out.println("File is created!");
                //Runtime.getRuntime().exec("chmod 777 /home/test3.txt");
                file.setExecutable(true);//设置可执行权限
                file.setReadable(true);//设置可读权限
                file.setWritable(true);//设置可写权限
                System.out.println("is execute allow : " + file.canExecute());
                System.out.println("is read allow : " + file.canRead());
                System.out.println("is write allow : " + file.canWrite());
            }else{
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
  }

    /**
     * 文件编码读取
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getFileCode(String fileName) throws Exception {
        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(fileName));
        int p = (bin.read() << 8) + bin.read();
        bin.close();
        String code = null;

        switch (p) {
            case 0xefbb:
                code = "UTF-8";
                break;
            case 0xfffe:
                code = "Unicode";
                break;
            case 0xfeff:
                code = "UTF-16BE";
                break;
            case 0x5c75:
                code = "ANSI|ASCII" ;
                break ;
            default:
                code = "GBK";
        }
        System.out.println(code);

        return code;
    }
}