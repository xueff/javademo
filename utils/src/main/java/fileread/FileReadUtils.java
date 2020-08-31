package fileread;

import org.junit.Test;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.*;

import static file_system.file.FileWatch.matcher;

/**
 * @author xuefei
 *txt csv log非压缩包文件读取
 */
public class FileReadUtils {

    public static String fileIgnores = ".tmp,.TMP";
    public static String fileFocuss = ".xml,.java";
    // ".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG)$"
    public static String reg = ".+(REPLACE_STRING)$";

    @Test
    public void fileWatch() throws IOException, InterruptedException {
        if(fileFocuss.length()>0){
            reg = reg.replace("REPLACE_STRING",fileFocuss.replace(",","|"));
        }
        if(fileIgnores.length()>0){
            reg = reg.replace("REPLACE_STRING",fileIgnores.replace(",","|"));
        }
        System.out.println(reg);
        WatchService watcher = FileSystems.getDefault().newWatchService();
        //有多字符串时，拼接成一个路径
        Paths.get("D:\\push").register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        while (true) {
            WatchKey key = watcher.take();
            for (WatchEvent<?> event: key.pollEvents()) {
                //TODO 有的工具一次操作，会多次操作文件
                if(matcher(event.context().toString())) {
                    System.out.println(event.context() + " comes to " + event.kind());
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }

    }

    public static void main(String[] args) throws Exception {

        File file = new File("D:\\project\\idr-holmes\\holmes\\logs\\holmes.2020-04-03.0.log");
        InputStream fileStream = null;
        try {
            fileStream = new FileInputStream(file);
            ByteArrayOutputStream baos = getByteArrayOutputStream(fileStream);
            // 此处不能删除，因为fileStream只能用一次
            fileStream = new ByteArrayInputStream(baos.toByteArray());
            baos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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





    public void fileCopyWithFileChannel(File fromFile, File toFile) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        FileChannel fileChannelInput = null;
        FileChannel fileChannelOutput = null;
        try {
            fileInputStream = new FileInputStream(fromFile);
            fileOutputStream = new FileOutputStream(toFile);
            //得到fileInputStream的文件通道
            fileChannelInput = fileInputStream.getChannel();
            //得到fileOutputStream的文件通道
            fileChannelOutput = fileOutputStream.getChannel();
            //将fileChannelInput通道的数据，写入到fileChannelOutput通道
            fileChannelInput.transferTo(0, fileChannelInput.size(), fileChannelOutput);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
                if (fileChannelInput != null)
                    fileChannelInput.close();
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (fileChannelOutput != null)
                    fileChannelOutput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
