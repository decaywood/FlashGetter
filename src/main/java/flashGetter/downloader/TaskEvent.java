package flashGetter.downloader;

/**
 * @author decaywood
 * 
 * 2015年2月9日
 * 
 */
public class TaskEvent {
    
    private TaskEventType taskEventType;
    private long TaskID;
    
    public static enum TaskEventType {
        INFORMATION_UPDATE,
        DOWNLOADING_FINISHED
    }
    
    public void setTaskEventType(TaskEventType taskEventType) {
        this.taskEventType = taskEventType;
    }
    
    public void setTaskID(long taskID) {
        TaskID = taskID;
    }
    
    public long getTaskID() {
        return TaskID;
    }
    
    public boolean typeEqual(TaskEventType type){
        return taskEventType == type;
    }

}
