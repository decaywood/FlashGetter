package flashGetter.downloader.task;

/**
 * @author decaywood
 * 
 * 2015年2月10日
 * 
 */
public interface Task {
    
    public static enum TaskState{
        TASK_BEGIN,
        TASK_FINISHED,
        TASK_UPDATE
    }

    public long getTaskID();
    
}
