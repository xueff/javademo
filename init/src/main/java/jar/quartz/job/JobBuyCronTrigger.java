package jar.quartz.job;


import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import jar.quartz.job.task.DeviceHeartJob;
import javabase.interfaces.ITask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 通过接口获取所有job
 * 使用表达式 开启定时任务
 */
public class JobBuyCronTrigger implements Cloneable {
    static Logger logger = LoggerFactory.getLogger(JobBuyCronTrigger.class);
    public static SchedulerFactory schedulerFactory = null;
    public static Scheduler scheduler = null;
    public static void startJob() throws InterruptedException, SchedulerException {
        if(scheduler != null){
            scheduler.shutdown();
        }
        // 1、创建调度器Scheduler
        if(schedulerFactory == null){
            schedulerFactory = new StdSchedulerFactory();
        }

        //TODO jar读取实现类
        List<Class> list = JobBuyCronTrigger.getITaskImplClass();
        if(list.size() == 0) {
            list.add(DeviceHeartJob.class);
        }

        logger.info("定时任务列表"+list);
        for(Class c : list){
            scheduler = schedulerFactory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(c)
                    .withIdentity(c.getSimpleName(), "master").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(c.getSimpleName(), "master")
                    .startNow()//立即生效
                    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
                    .build();//一直执行
            scheduler.scheduleJob(jobDetail, trigger);
        }
        scheduler.start();
    }

    public static void close() throws SchedulerException {
        if(scheduler != null){
            scheduler.shutdown(true);
        }
    }


    @SuppressWarnings("unchecked")
    public static List<Class> getITaskImplClass(){
        Class c = ITask.class;
        List returnClassList = new ArrayList<Class>();
        try {
            List<Class> allClass = getClasses("com.xiezhu.monitor.job.task");
            //判断是否是一个接口
            for(int i = 0; i < allClass.size(); i++){
                if(c.isAssignableFrom(allClass.get(i))){
                    if(!c.equals(allClass.get(i))){
                        returnClassList.add(allClass.get(i));
                    }
                }
            }
        } catch (Exception e) {
        }
        return returnClassList;
    }

    private static List<Class> getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace(".", "/");
        logger.info("path"+path);
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while(resources.hasMoreElements()){
            URL resource = resources.nextElement();
            String newPath = resource.getFile().replace("%20", " ");
            System.out.println("-----"+newPath);
            dirs.add(new File(newPath));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for(File directory:dirs){
            classes.addAll(findClass(directory, packageName));
        }
        return classes;
    }

    private static  List<Class> findClass(File directory, String packageName)
            throws ClassNotFoundException{
        List<Class> classes = new ArrayList<Class>();
        if(!directory.exists()){
            return classes;
        }
        File[] files = directory.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                assert !file.getName().contains(".");
                classes.addAll(findClass(file, packageName+"."+file.getName()));
            }else if(file.getName().endsWith(".class")){
                classes.add(Class.forName(packageName+"."+file.getName().substring(0,file.getName().length()-6)));
            }
        }
        return classes;
    }



    public static void main(String[] args) throws InterruptedException, SchedulerException {
        startJob();
    }
}
