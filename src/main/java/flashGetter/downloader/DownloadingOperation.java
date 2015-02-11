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
    
    public void startTask(Long... taskIDs);
    
    public void pauseTask(Long... taskIDs);
    
    public void deleteTask(Long... taskIDs);
    
    public void finishTask(Long... taskIDs);
    
    public void addManagerListener(ManagerListener listener);
    
    public void fireTaskInfo(Task event);
    
}
