package shell;

import javabase.file.path.GetClassPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 运行shell脚本
 */
public class ShellTest {

    public static void main(String[] args) throws Exception {
        ShellTest.runStartShell();
    }

    private synchronized static void runStartShell() throws Exception {
        //启动hive
        StringBuilder pidCmd = new StringBuilder(GetClassPath.getClassPath());
        //脚本
        pidCmd.append("restartHive.sh");
        System.out.println("shell路径：" + pidCmd.toString());
        Process step = null;
        try {
            String[] cmds = {"sh", "-c", pidCmd.toString()};
            step = Runtime.getRuntime().exec(cmds);
            //读取标准输出流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(step.getInputStream()));
            String lineo;
            while ((lineo = bufferedReader.readLine()) != null) {
                System.out.println("=="+lineo);
            }
            bufferedReader.close();
            //读取标准错误流
            BufferedReader brError = new BufferedReader(new InputStreamReader(step.getErrorStream()));
            String errline = null;
            while ((errline = brError.readLine()) != null) {
                System.out.println("||"+errline);
            }
            brError.close();
            int rs = step.waitFor();
            if (rs != 0) {
                InputStream inputStream = step.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line = "shell execute error!\n";
                String errorMsg = "";
                while (line != null) {
                    errorMsg = errorMsg.concat("\n").concat(line);
                    line = reader.readLine();
                }
                System.out.println("||||"+errorMsg);
                reader.close();
                inputStream.close();
                throw new Exception(errorMsg);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
