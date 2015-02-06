package flashGetter.downloader.task;

import javax.swing.ImageIcon;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public class TaskInfo implements DownloadingTask, DownloadedTask, DeletedTask{
    
    private final long taskID;
    private TaskThread task;
    
    String url;
    String fileSavePath;
    
    ImageIcon fileType;
    String fileName;
    String fileSize;
    String progress;
    String remainTime;
    String downloadSpeed;
    
    String finishTime;
    String createTime;
    
    public TaskInfo(long taskID) {
        this.taskID = taskID;
    }
    
    public void setTask(TaskThread task) {
        this.task = task;
    }
    
    public void setFileSavePath(String fileSavePath) {
        this.fileSavePath = fileSavePath;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Override
    public String getDownloadURL() {
        return url;
    }
    @Override
    public String getSavePath() {
        return fileSavePath;
    }

}
