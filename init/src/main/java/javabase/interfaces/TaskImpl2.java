package javabase.interfaces;

import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

public class TaskImpl2 extends TaskImpl {
    Logger logger = LoggerFactory.getLogger(TaskImpl2.class);
    @Override
    public String Im(){
        return "TaskImpl2";
    }

}
