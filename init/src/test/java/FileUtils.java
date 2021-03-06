import org.apache.commons.lang.StringUtils;

import java.io.*;

/**
 *
 * 文件追加内容
 * @version 2011-3-10 下午10:49:41
 */
public class FileUtils {


    public static void main(String[] args)   {

        filePermission();
    }

    /**
     * 向文件追加内容
     */
    public static void appendFile(String content,String fileName){
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
    /**
     *
     * @param cfg
     * @return
     */
    public static String readLine(File cfg) {
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
            File file = new File("/opt/data/新建文本文档2.txt");
            if (file.createNewFile()){
                System.out.println("File is created!");
                Runtime.getRuntime().exec(" sudo chattr -V +i /opt/data/新建文本文档1.txt");
//                file_system.file.setExecutable(false);//设置可执行权限
//                file_system.file.setReadable(true);//设置可读权限
//                file_system.file.setWritable(true);//设置可写权限
//                System.out.println("is execute allow : " + file_system.file.canExecute());
//                System.out.println("is read allow : " + file_system.file.canRead());
//                System.out.println("is write allow : " + file_system.file.canWrite());
//                System.out.println("is delete : " + file_system.file.delete());

            }else{
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
  }
}
