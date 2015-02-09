package flashGetter.downloader.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.concurrent.atomic.LongAdder;

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
    
    private String downloadSpeed;
    
    private String finishTime;
    private String createTime;
    
    private DoubleAdder progress; //dynamic
    private String remainTime; //dynamic
    private LongAdder startOffset; //dynamic
    
    public TaskInfo(long taskID, String URL, String downloadSavePath) {
        this.taskID = taskID;
        this.url = URL;
        this.fileSavePath = downloadSavePath;
        this.startOffset = new LongAdder();
    }
    
    public long getTaskID() {
        return taskID;
    }
    
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
        this.progress.add(task.getProgress());
        this.remainTime = task.getRemainTime();
        this.downloadSpeed = task.getDownloadSpeed();
        this.finishTime = task.getFinishTime();
        this.createTime = task.getCreateTime();
        this.url = task.getDownloadURL();
        this.fileName = task.getFileName();
        this.fileSavePath = task.getSavePath();
        startOffset.add(task.getStartOffset());
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
        return startOffset.longValue();
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
        return progress.doubleValue();
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
        this.startOffset = startOffset;
    }


    @Override
    public void moveProgress(double progress) {
        if(progress == 0) return;
        this.progress.add(progress);
    }

    @Override
    public void setRemainTime(String remainTime) {
        if(remainTime == null) return;
        this.remainTime = remainTime;
    }

    @Override
    public void setDownloadSpeed(String speed) {
        if(speed == null) return;
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

    
    
    

}
