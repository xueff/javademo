package datasource.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * SMB和CIFS协议文件挂载
 */
public class MountUtil {
    private static Logger log = LoggerFactory.getLogger(MountUtil.class);
    private String ip;
    private String userName;
    private String password;
    public String dir;
    public String version;
    private int port;
    private int protocolType;

    public MountUtil(String ip, String userName, String password, String dir, int port, int protocolType, String version) {
        this.ip = ip;
        this.userName = userName;
        this.password = password;
        this.dir = dir;
        this.port = port;
        this.protocolType = protocolType;
        this.version = version;
    }

    /**
     * @Author li cong
     * @Description 更新的数据源，执行挂载命令
     * @Date 2019/11/26 4:34 下午
     */
    public boolean execute() {
        boolean result = false;
        try {
            //先创建目录
            createDiretory();
            //验证目录是否为挂载目录
            int mountPointResult = checkMountPoint();
            //是挂载目录
            if (mountPointResult == 0) {
                result = true;
            } else if (mountPointResult == 1) {
                String command = getMountCommand();
                Runtime runtime = Runtime.getRuntime();
                Long start = System.currentTimeMillis();
                Process process = runtime.exec(command);
                //等待子线程线程执行完毕再进行执行
                int i = process.waitFor();
                log.info("waitFor"+i);
                //接收执行完成后，获得的返回值
                i = process.exitValue();
                log.info("exitValue"+i);
                log.info("执行时间：" + (System.currentTimeMillis() - start));
                if (i == 0) {
                    log.info("执行完成");
                    //判断里面有没有文件
                    File file = new File(dir);
                    if (file.exists()) {
                        log.info("获取到文件数量:" + file.listFiles().length);
                        result = true;
                    } else {
                        log.info("文件目录不存在");
                    }
                } else {
                    log.info("执行失败，需要root用户执行，返回值：" + i);
                    //log.info("---process.getInputStream()---");
                    //readProcessInfo(process.getInputStream(),System.out);
                    log.info("---process.getErrorStream()---");
                    readProcessInfo(process.getErrorStream(), System.out);
                    readProcessInfo(process.getInputStream(), System.out);
                }
                process.destroy();

            } else {
                result = false;
            }

        } catch (Exception e) {
            log.info(e.getMessage());
        }
        return result;
    }

    /**
     * @Author li cong
     * @Description 根据执行线程的用户和协议类型设置执行命令
     * @Date 2019/11/26 4:33 下午
     */
    private String getMountCommand() {
        String currentUser = System.getProperty("user.name");
        log.info("protocolType:" + protocolType + ",currentUser:" + currentUser + ",mountirectory:" + dir);
        String command = "";
        String vs = "";
        if (this.version != null && !this.version.isEmpty()) {
            vs = ",vers=" + this.version;
        }
        if (currentUser.equals("root")) {
            if (protocolType == 3) {
                command = "sudo mount " + ip + " " + dir + " -o username=" + userName + ",password=" + password + vs + ",port=" + port;
            } else if (protocolType == 4) {
                command = "sudo mount -t cifs -o username=" + userName + ",password=" + password + vs + ",port=" + port + " " + ip + " " + dir;
            }
        } else {
            if (protocolType == 3) {
                command = "sudo mount " + ip + " " + dir + " -o username=" + userName + ",password=" + password + vs + ",port=" + port;

            } else if (protocolType == 4) {
                command = "sudo mount -t cifs -o username=" + userName + ",password=" + password + vs + ",port=" + port + " " + ip + " " + dir;
            }
        }
        log.info("mount command:"+command);
        return command;
    }


    /**
     * 创建目录
     */
    private void createDiretory() {
        //判断目录是否存在，如果不存在进行创建
        File file = new File(dir);
        String currentUser = System.getProperty("user.name");
        if (!file.exists()) {
            String mkdir = "";
            if (currentUser.equals("root")) {
                mkdir = "mkdir -p " + dir;
            } else {
                mkdir = "sudo mkdir -p " + dir;
            }
            log.info("创建目录的命令：" + mkdir);
            commonExecute(mkdir);
        } else {
            log.info("已存在：" + dir);
            log.info("protocolType:" + protocolType + ",currentUser:" + currentUser + ",mountirectory:" + dir);

        }
    }

    /**
     * @Author li cong
     * @Description
     * @Date 2019/11/28 11:02 上午
     * @Param
     */
    private String getUmountCommand() {
        String currentUser = System.getProperty("user.name");
        log.info("protocolType:" + protocolType + ",currentUser:" + currentUser);
        String command = "";
        if (currentUser.equals("root")) {
            command = "sudo  umount -f " + dir;
        } else {
            command = "sudo umount -f " + dir;
        }
        log.info("umonut Command：" + command);
        return command;
    }

    /**
     * 验证目录是否为挂载目录
     *
     * @return
     */
    private String getMountPoint() {
        String currentUser = System.getProperty("user.name");
        log.info("protocolType:" + protocolType + ",currentUser:" + currentUser);
        String command = "";
        if (currentUser.equals("root")) {
            command = "sudo mountpoint " + dir;
        } else {
            command = "sudo mountpoint " + dir;
        }
        log.info("验证目录命令：" + command);
        return command;
    }


    /**
     * @Author li cong
     * @Description 验证目录是否为挂载目录
     * @Date 2019/11/28 11:10 上午
     * @Param
     */
    public int checkMountPoint() {
        int result = -1;
        try {
            String command = getMountPoint();
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(command);
            Thread.sleep(1000);
            int i = process.waitFor();
            log.info("waitFor:"+i);
            i = process.exitValue();
            log.info("exitValue:"+i);
            if (i == 0) {
                String content = readProcessInfo(process.getInputStream());
                log.info("查挂载目录返回结果:" + content);
                if (content.equals(dir + " is a mountpoint") || content.equals(dir + " 是一个挂载点")) {
                    result = 0;
                    log.info("该目录是挂载目录!");
                } else {
                    content = readProcessInfo(process.getErrorStream());
                    result = 1;
                    log.info("该目录不是挂载目录!"+content);
                }
            } else {
                String content = readProcessInfo(process.getInputStream());
                log.info("检查挂载目录执行错误代码" + i+":"+content);
                content = readProcessInfo(process.getErrorStream());
                log.info("检查挂载目录执行失败代码" + i+":"+content);
                result = 1;
            }
            process.destroy();
        } catch (Exception e) {
            log.info("检查挂载目录出现异常了!");
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return result;
    }

    /**
     * 卸载目录
     *
     * @return
     */
    public boolean unmountExecute() {
        int result = checkMountPoint();
        if (result == 0) {
            //执行卸载目录
            String umountCommand = getUmountCommand();
            commonExecute(umountCommand);
            return true;

        } else {
            return false;
//            response.setErrMsg("该目录不是挂载目录!");
        }
    }

    /**
     * @Author li cong
     * @Description 共用命令
     * @Date 2019/11/26 4:31 下午
     */
    public String commonExecute(String command) {
        log.info("执行命令：" + command);
        try {
            Runtime runtime = Runtime.getRuntime();
            Long start = System.currentTimeMillis();
            Process process = runtime.exec(command);
            int i = process.waitFor();
//            int i = process.exitValue();

            log.info("执行时间：" + (System.currentTimeMillis() - start));
            process.destroy();
            if (i == 0) {
                log.info("执行完成");
                //判断里面有没有文件
                return "Success";
            } else {
                log.info("执行失败，失败代码" + i);
                return "false";
            }
        } catch (Exception e) {
//            testConnectionResponse.setErrMsg("连接不可用!");
            return "false";
        }
    }

    /**
     * @Author li cong
     * @Description
     * @Date 2019/11/26 4:41 下午
     */
    private static void readProcessInfo(InputStream inputStream, PrintStream out) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String lines = "";
            while ((line = reader.readLine()) != null) {
                out.println(line);
                lines += line;
            }
            log.error("readProcessInfo "+lines);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String readProcessInfo(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
