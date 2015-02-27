package flashGetter.downloader;

import flashGetter.downloader.task.Task;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public interface DownloadedOperation {
    
    public void deleteTask(long... taskIDs);
    
    public void addManagerListener(ManagerListener listener);

    public void fireTaskInfo(Task event);
    
    public void offerFinishedTask(long taskID);
    
}
