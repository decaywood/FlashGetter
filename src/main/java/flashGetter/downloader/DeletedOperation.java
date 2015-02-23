package flashGetter.downloader;

import flashGetter.downloader.task.Task;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public interface DeletedOperation {
    
    public void offerDeletedTask(long... taskIDs);
    
    public void recoverTask(long... taskIDs);
    
    public void removeTask(long... taskIDs);
    
    public void removeAllTask();
    
    public void addManagerListener(ManagerListener listener);

    public void fireTaskInfo(Task event);
}
