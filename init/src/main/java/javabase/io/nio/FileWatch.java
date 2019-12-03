package javabase.io.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;

/**
 * @author xuefei
 * @Title: ${file_name}1
 * @Package ${package_name}
 * @Description:  **Nio WatchService**  获取当前OS平台下的文件系统监控器(文件增删改监控器)
 * @date 2018/10/2914:48
 */
public class FileWatch {

    @Test
    public void fileWatch() throws IOException, InterruptedException {
        WatchService watcher = FileSystems.getDefault().newWatchService();
        //有多字符串时，拼接成一个路径
        Paths.get("D:\\push").register(watcher,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY);

        while (true) {
            WatchKey key = watcher.take();
            for (WatchEvent<?> event: key.pollEvents()) {
                System.out.println(event.context() + " comes to " + event.kind());
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }

    }
}
