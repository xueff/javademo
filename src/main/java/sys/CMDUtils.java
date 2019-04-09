package sys;
import org.junit.Test;
import java.io.IOException;

/**
 * java 执行cmd
 */
public class CMDUtils {
    @Test
    public void test1() throws IOException{
//直接打开应用程序--无窗口
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kD:/MTS/MTS/MTS.JobServer.V2/LogicJobs/StatisticalChargeConsumptionJob/StatisticalChargeConsumptionJob/bin/Debug/StatisticalChargeConsumptionJob.exe\"2018-12-1212:00:00\",\"2018-12-1312:00:00\"");//打开一个批处理文件
//直接打开应用程序--有窗口
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kstartD:/MTS/MTS/MTS.JobServer.V2/LogicJobs/StatisticalChargeConsumptionJob/StatisticalChargeConsumptionJob/bin/Debug/StatisticalChargeConsumptionJob.exe\"2018-12-1212:00:00\",\"2018-12-1312:00:00\"");//打开一个批处理文件

//Runtime.getRuntime().exec("D:/MTS/MTS/MTS.JobServer.V2/LogicJobs/StatisticalChargeConsumptionJob/StatisticalChargeConsumptionJob/bin/Debug/StatisticalChargeConsumptionJob.exe\"2018-12-1212:00:00\",\"2018-12-1312:00:00\"");//打开一个批处理文件
//运行jar
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe"+
//"/kstart"+
//"java-jarE:\\localRepository\\maven\\com\\xiezhu\\lanhu\\0.0.1-SNAPSHOT\\lanhu-0.0.1-SNAPSHOT.jar");//打开一个批处理文件
        Runtime.getRuntime().exec("E:\\ProgramFiles\\DingDing\\main\\current\\DingTalk.exe");//通过cmd窗口执行命令
//


///********可以通过cmd命令打开软件或者是做其他*****/
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kstartE:/酷狗/KGMusic/KuGou.exe");//通过cmd窗口执行命令
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kstartE:/php/Test/第一个html/界面.html");//通过cmd命令打开一个网页
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kmkdirC:\\Users\\liqiang\\Desktop\\java键的1");//通过cmd创建目录用两个反斜杠
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/kmkdirC:\\Users\\liqiang\\Desktop\\java键的2");//通过cmd创建目录用两个反斜杠
//Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/ccalc");//通过cmd打开计算器
    }

    @Test
    public void test2()throws IOException{
        /********可以通过cmd命令打开软件或者是做其他*****/
        Runtime.getRuntime().exec("C:/Windows/System32/cmd.exe/cosk");//通过屏幕软键盘
    }

}
