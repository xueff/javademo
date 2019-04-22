package javabase.interfaces;

public interface ITask {
    /**
     * 查询任务
     * @param taskType
     */
    void getTask(String taskType);

    /**
     * 分发任务
     */
    void distributionTask();
}
