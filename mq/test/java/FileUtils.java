import java.io.*;

/**
 *
 * 文件追加内容
 * @version 2011-3-10 下午10:49:41
 */
public class FileUtils {



    public static void main(String[] args) {
        File dir = new File("D:/out/aa/bb/");
        if (!dir.mkdirs()) {

        }
    }

    /**
     * 向文件追加内容
     */
    public static void createOrAppendFile(String content,String fileName){
        if(content == null || content.length() ==0){
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
            File file = new File("/opt/perssion/");
            File file2 = new File("/opt/perssion/water/aa/");
            System.out.println(file.length());
            if(!file2.exists()) {
                file2.mkdirs();
            }
            if (file2.createNewFile()){
                System.out.println("File is created!");
                Runtime.getRuntime().exec("chmod -R 777 "+file2.getAbsolutePath());
//                file.setExecutable(true);//设置可执行权限
//                file.setReadable(true);//设置可读权限
//                file.setWritable(true);//设置可写权限
            }else{
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
  }
    public static void moveFile() {
        try {
            File file = new File("/opt/a.txt/");
            File file2 = new File("/opt/aa/a.txt");
            try {
                if (file.renameTo(file2)) {
                    Runtime.getRuntime().exec("chmod -R 777 "+file2.getAbsolutePath());
                    System.out.println("文件移动成功！");
                } else {
                    System.out.println("文件移动失败2！");
                }
            }catch(Exception e) {
                System.out.println("文件移动出现异常3！");
            }
            System.out.println(file.length());
            if(!file2.exists()) {
                file2.mkdirs();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
  }


}