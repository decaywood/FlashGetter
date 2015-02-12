package flashGetter.downloader;

import flashGetter.downloader.task.Task;

/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public interface DownloadingOperation {

    public void createTask(String downloadAddr, String savePath);
    
    public void startTask(long... taskIDs);
    
    public void pauseTask(long... taskIDs);
    
    public void deleteTask(long... taskIDs);
    
    public void finishTask(long... taskIDs);
    
    public void addManagerListener(ManagerListener listener);
    
    public void fireTaskInfo(Task event);
    
}
