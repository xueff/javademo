package javabase.interfaces;

import com.github.brainlag.nsq.exceptions.NSQException;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.concurrent.TimeoutException;

public class TaskImpl implements ITask {
    Logger logger = LoggerFactory.getLogger(TaskImpl.class);
    public String Im(){
        return "TaskImpl";
    }

    @Override
    public void getTask(String taskType) {
        //

        distributionTask();
    }

    @Override
    public void filterTask(String taskType) {

    }

    @Override
    public void distributionTask() {

    }

}
