package utils.sigar;

import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 采集CPU使用率
 * @author cj
 */
public class CpuUsage {

    private static Logger log = LoggerFactory.getLogger(CpuUsage.class);
    private static CpuUsage INSTANCE = new CpuUsage();
    private static Sigar sigar = SigarUtil.getSigar();
    private CpuUsage() {

    }

    public static CpuUsage getInstance() {
        return INSTANCE;
    }

    public int getForAllSystem() {
        try {

            double cpu = sigar.getCpuPerc().getCombined()*100;
            return (int)cpu;


        } catch (Exception e) {
            return 0;
        }

    }


}
