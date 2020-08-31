package utils.sigar;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * 采集磁盘使用率
 * @author cj
 */
public class DiskUsage {

    private static final Logger LOGGER = LoggerFactory.getLogger(DiskUsage.class);

    private static DiskUsage INSTANCE = new DiskUsage();
    private static Sigar sigar = SigarUtil.getSigar();
    private DiskUsage() {

    }
    public static DiskUsage getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        System.out.println(new DiskUsage().getForAllSystem());
    }

    public int getForAllSystem() {
        try {

            FileSystem[] fsList = sigar.getFileSystemList();
            long total = 0L;
            long used = 0L;
            for (int i = 0; i < fsList.length; i++) {
                FileSystem fs = fsList[i];
                FileSystemUsage usage;
                 usage = sigar.getFileSystemUsage(fs.getDirName());
                switch (fs.getType()) {
                    case 2:
                        total+=usage.getTotal();
                        used+=usage.getUsed();
                        break;
                    default: break;
                }
            }
            if (total!=0){
                return (int)(used*100/total);
            }else {
                return 0;
            }
        } catch (Exception e) {
//            LOGGER.error("", e);
            return 0;
        }
    }

    public static long getDirectionaryLeftMB() {
        try {

            FileSystemUsage usage = sigar.getFileSystemUsage(new File("./").getAbsolutePath());
            return usage.getFree()/1024;
        } catch (Exception e) {
            return 0;
        }
    }
}
