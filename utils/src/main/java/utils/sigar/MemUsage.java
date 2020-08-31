package utils.sigar;

import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 采集内存使用率
 * @author cj
 */
public class MemUsage {

    private static Logger log = LoggerFactory.getLogger(MemUsage.class);
    private static MemUsage INSTANCE = new MemUsage();
    private static Sigar sigar = SigarUtil.getSigar();

    private MemUsage() {

    }

    public static MemUsage getInstance() {
        return INSTANCE;
    }

    /**
     * Purpose:采集内存使用率
     *
     * @return float, 内存使用率, 小于1
     */
    public float get() {
        log.info("开始收集memory使用率");
        float memUsage = 0.0f;
        Process pro = null;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "cat /proc/meminfo";
            pro = r.exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            String line = null;
            int count = 0;
            long totalMem = 0, freeMem = 0, cached = 0;
            while ((line = in.readLine()) != null) {
                log.info(line);
                String[] memInfo = line.split("\\s+");
                if (memInfo[0].startsWith("MemTotal")) {
                    totalMem = Long.parseLong(memInfo[1]);
                }
                if (memInfo[0].startsWith("MemFree")) {
                    freeMem = Long.parseLong(memInfo[1]);
                }
                if (memInfo[0].startsWith("Cached")) {
                    cached = Long.parseLong(memInfo[1]);
                }
                if (++count == 10) {
                    break;
                }
            }
            freeMem += cached;
            memUsage = 1 - (float) freeMem / (float) totalMem;
            log.info("本节点内存使用率为: freeMem ：" + freeMem + "" + memUsage);
            in.close();
            pro.destroy();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            log.error("MemUsage发生InstantiationException. " + e.getMessage());
            log.error(sw.toString());
        }
        return memUsage;
    }

    public int getForAllSystem() {
        try {
            Mem mem = sigar.getMem();
            Double memPer = ((double) mem.getActualUsed() / mem.getTotal())*100;

            return memPer.intValue();
        } catch (Exception e) {
            return 0;
        }
    }
    public Double getSystemFree() {
        try {
            Mem mem = sigar.getMem();
            Double memPer = (double) mem.getFree() / mem.getTotal();
            log.info("Mem Free:"+mem.getFree()+"，Mem Total："+mem.getTotal());
            return memPer;
        } catch (Exception e) {
            return 0.0;
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
//    public static void main(String[] args) throws InterruptedException {
//        while(true){
//            System.out.println(MemUsage.getInstance().get());
//            Thread.sleep(5000);
//        }
//    }
}

