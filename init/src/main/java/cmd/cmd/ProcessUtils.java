package cmd.cmd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessUtils {

    public static void main(String[] args) {
        String message="";
        BufferedReader br= null;
        Process process = null;
        try {
            String shPath= "/home/tomcat_ws/bin/start_tomcatws.sh";   //tomecat启动脚本路径
            System.out.println("=============启动脚本路径："+shPath);
            process = Runtime.getRuntime().exec(shPath.toString());
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();
            System.out.println("=============启动脚本-执行结果："+result);
            if(process != null){
                int extValue = process.waitFor(); //返回码 0(正常执行完结果0) 表示正常退出 1表示异常退出
                if(0 == extValue){
                    System.out.println("=============启动脚本-执行完毕！");
                }else{
                    System.out.println("=============启动脚本-执行异常！");
                    message="启动脚本执行异常";
                }
            }
        }catch (IOException e) {
            // TODO Auto-generated catch block
            message= "启动脚本执行异常";
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            message= "启动脚本异常退出 ";
            e.printStackTrace();
        }finally {
            try {
                br.close();
                process.destroy();
            } catch (Exception e) {
                System.out.println("流或process销毁异常");
            }
        }
    }
}
