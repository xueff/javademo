package jar.quartz.job.task;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import javabase.interfaces.TaskImpl;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


/**
 *
 */
@DisallowConcurrentExecution
public class DeviceHeartJob extends TaskImpl implements Job {
    Logger logger = LoggerFactory.getLogger(DeviceHeartJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        getTask("DeviceHeartJob");
        //....
    }
//    @Override
//    public List<MonitorModel> getTask(String taskType) {
//       return super.getTask(taskType);
//    }
//
//    @Override
//    public List<MonitorModel> filterTask(List<MonitorModel>  jobs) {
//        return super.filterTask(jobs);
//    }
//
//    @Override
//    public List<SendJobModel> distributionTask(List<MonitorModel>  jobs) {
//        List<SendJobModel> taskList = new ArrayList<>();
//        try {
//
//            List<String> list = WorkersInfo.getWorkersIds();
//            jobs.forEach(it->{
//                taskList.add(new SendJobModel(it.getTriggerId(),System.currentTimeMillis(),WorkersInfo.getAndUpdateWorkerStart(list)));
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return taskList;
//    }
}
