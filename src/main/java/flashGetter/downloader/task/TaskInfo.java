package flashGetter.downloader.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import flashGetter.util.FileSystemIconUtil;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public class TaskInfo implements DownloadingTask, DownloadedTask, DeletedTask, Serializable{
    
    private static final Logger LOGGER = Logger.getLogger(TaskInfo.class);
    
    /*
     * unique identified ID
     */
    private final transient long taskID;
    /*
     * the lock is used to prevent more than one downloading thread
     * from bundling the same task at the same time, it would result 
     * in mess of data
     */
    private volatile boolean lock;
    
    private String url;
    private String fileSavePath;
    
    private ImageIcon fileType; 
    private String fileName;
    private long fileSize;
    
    private double downloadSpeed;
    
    private String finishTime;
    private String createTime;
    
    double progress;
    
    private String remainTime; //dynamic
    
    private long startOffset; //dynamic
    
    private TaskState taskState;
    
    public TaskInfo(long taskID, String URL, String downloadSavePath) {
        this.taskID = taskID;
        this.url = URL;
        this.fileSavePath = downloadSavePath;
    }
    
    public long getTaskID() {
        return taskID;
    }
    
    /*
     * temporary file path
     */
    private File file;
    
    @Override
    public void serializeTask() throws IOException {
        if(file == null)
            file = getTempFilePath();
        ObjectOutputStream oos =  new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(this);
        oos.flush();
        oos.close();
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
    
    /* getter
     * =======================================================================================================    
     */    
    
    
    
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
    public long getStartOffset() {
        return startOffset;
    }

    @Override
    public File getTempFilePath() {
        return new File(fileSavePath.concat(fileName).concat(".temp"));
    }
    
    @Override
    public long getFileSize() {
        return fileSize;
    }

    @Override
    public double getProgress() {
        return progress;
    }

    @Override
    public String getRemainTime() {
        return remainTime;
    }

    @Override
    public double getDownloadSpeed() {
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
        fileType = fileType != null ? fileType
                : FileSystemIconUtil.readSystemSmallIcon(fileSavePath.concat(fileName)); 
        return fileType;
    }
    
    /* setter
     * =======================================================================================================    
     */

    @Override
    public void setFileName(String fileName) {
        if(fileName == null) return;
        this.fileName = fileName;
    }
    
    @Override
    public void setFileSize(long fileSize) {
        if(fileSize == 0) return;
        this.fileSize = fileSize;
    }
   
    
    @Override
    public void moveStartOffset(long phase) {
        if(phase == 0) return;
        this.startOffset = startOffset + phase;
    }


    @Override
    public void moveProgress(double progress) {
        if(progress == 0) return;
        this.progress = progress;
    }

    @Override
    public void setRemainTime(String remainTime) {
        if(remainTime == null) return;
        this.remainTime = remainTime;
    }

    @Override
    public void setDownloadSpeed(double speed) {
        if(speed == 0) return;
        this.downloadSpeed = speed;
    }

    @Override
    public void setFinishTime(String finishTime) {
        if(finishTime == null) return;
        this.finishTime = finishTime;
    }

    @Override
    public void setCreateTime(String createTime) {
        if(createTime == null) return;
        this.createTime = createTime;
    }

    @Override
    public void setDownloadURL(String url) {
        if(url == null) return;
        this.url = url;
    }

    @Override
    public void setSavePath(String savePath) {
        if(savePath == null) return;
        this.fileSavePath = savePath;
    }

    @Override
    public void setFileType(ImageIcon fileType) {
        if(fileType == null) return;
        this.fileType = fileType;
    }

    @Override
    public void lock() {
        this.lock = true;
    }

    @Override
    public void unLock() {
        this.lock = false;
    }

    @Override
    public boolean isLock() {
        return lock;
    }

    @Override
    public void changeTaskState(TaskState state) {
        if(taskState != null && state == TaskState.TASK_BEGIN) return;
        this.taskState = state;
    }

    @Override
    public boolean stateEqual(TaskState state) {
        return taskState == state;
    }

    
    
    

}
