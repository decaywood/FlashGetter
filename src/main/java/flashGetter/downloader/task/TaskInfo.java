package flashGetter.downloader.task;

import java.io.Serializable;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public class TaskInfo implements DownloadingTask, DownloadedTask, DeletedTask, Serializable{
    
    private static final Logger LOGGER = Logger.getLogger(TaskInfo.class);
    
    private final transient long taskID;
    
    private String url;
    private String fileSavePath;
    
    private ImageIcon fileType; 
    private String fileName;
    private long fileSize;
    private String progress;
    private String remainTime;
    private String downloadSpeed;
    
    private String finishTime;
    private String createTime;
    
    private long startOffset;
    
    public TaskInfo(long taskID, String URL, String downloadSavePath) {
        this.taskID = taskID;
        this.url = URL;
        this.fileSavePath = downloadSavePath;
    }
    
    public long getTaskID() {
        return taskID;
    }
    
    
    @Override
    public String getDownloadURL() {
        return url;
    }
    @Override
    public String getSavePath() {
        return fileSavePath;
    }
    
   
    
    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        
        if(this.fileName != null) return;
        this.fileName = fileName;
        
    }
    
    

    @Override
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public long getStartOffset() {
        return startOffset;
    }

    @Override
    public void setStartOffset(long startOffset) {
        double prog = startOffset / fileSize;
        this.startOffset = startOffset;
        this.progress = String.valueOf(prog);
    }

    @Override
    public String getTempFilePath() {
        return fileSavePath.concat(fileName).concat(".temp");
    }
    
    @Override
    public long getFileSize() {
        return fileSize;
    }

    @Override
    public void copyInfo(DownloadingTask task) {
        this.fileType = task.getFileType();
        this.progress = task.getProgress();
        this.remainTime = task.getRemainTime();
        this.downloadSpeed = task.getDownloadSpeed();
        this.finishTime = task.getFinishTime();
        this.createTime = task.getCreateTime();
        this.url = task.getDownloadURL();
        this.fileName = task.getFileName();
        this.fileSavePath = task.getSavePath();
        this.startOffset = task.getStartOffset();
        this.fileSize = task.getFileSize();
    }

    @Override
    public String getProgress() {
        return progress;
    }

    @Override
    public String getRemainTime() {
        return remainTime;
    }

    @Override
    public String getDownloadSpeed() {
        return downloadSpeed;
    }

    @Override
    public String getFinishTime() {
        return finishTime;
    }

    @Override
    public String getCreateTime() {
        return createTime;
    }

    @Override
    public ImageIcon getFileType() {
        return fileType;
    }

    @Override
    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    @Override
    public void setDownloadSpeed(String speed) {
        this.downloadSpeed = speed;
    }

    @Override
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public void setDownloadURL(String url) {
        this.url = url;
    }

    @Override
    public void setSavePath(String savePath) {
        this.fileSavePath = savePath;
    }

    @Override
    public void setFileType(ImageIcon fileType) {
        this.fileType = fileType;
    }

    
    
    

}
