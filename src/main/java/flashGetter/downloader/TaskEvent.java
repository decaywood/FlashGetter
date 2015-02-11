package flashGetter.downloader;

import flashGetter.downloader.DownloadManager.TaskEventType;
import flashGetter.downloader.task.Task.TaskState;


/**
 * @author decaywood
 * 
 * 2015年2月9日
 * 
 */
public class TaskEvent {
    
    private TaskState taskEventType;
    private long TaskID;
    
   
    
    public void setTaskEventType(TaskState taskEventType) {
        this.taskEventType = taskEventType;
    }
    
    public void setTaskID(long taskID) {
        TaskID = taskID;
    }
    
    public long getTaskID() {
        return TaskID;
    }
    
    public boolean typeEqual(TaskState type){
        return taskEventType == type;
    }

}
