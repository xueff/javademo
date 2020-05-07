package fileread.FileReadUtils;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xuefei
 * @Title: ${file_name}1
 * @Package ${package_name}
 * @Description: 文件监控 **Nio WatchService**  获取当前OS平台下的文件系统监控器(文件增删改监控器)
 * @date 2018/10/2914:48
 */
public class FileWatch {

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

    private boolean isIgnore(){
        return false;
    }


    public static boolean matcher(String fileName){
        Matcher matcher = Pattern.compile(reg).matcher(fileName);
        return matcher.find();
    }
}
