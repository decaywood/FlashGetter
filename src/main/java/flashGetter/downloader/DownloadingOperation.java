package flashGetter.downloader;

import flashGetter.downloader.task.TaskInfo;

/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public interface DownloadingOperation {

    public void createTask(String downloadAddr, String savePath);
    
    public void startTask(Long[] taskNumber);
    
    public void pauseTask(Long[] taskNumber);
    
    public void deleteTask(Long[] taskNumber);
    
}
