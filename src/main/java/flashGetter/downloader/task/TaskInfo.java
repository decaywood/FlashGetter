package flashGetter.downloader.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
    
    private String SerializeName;
    
    
    private String url;
    private String fileSavePath;
    
    private ImageIcon fileType;
    private String fileName;
    private String fileSize;
    private String progress;
    private String remainTime;
    private String downloadSpeed;
    
    private String finishTime;
    private String createTime;
    
    private long startOffset;
    private long endOffset;
    
    public TaskInfo(long taskID, String URL, String downloadSavePath) {
        this.taskID = taskID;
        this.url = URL;
        this.fileSavePath = downloadSavePath;
    }
    
    public long getTaskID() {
        return taskID;
    }
    
    
    private void readTask(){
        
        if(SerializeName == null) return;
        
        String taskPath = fileSavePath.concat(SerializeName);
        File file = new File(taskPath);
        
        if(file.exists()){
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
                TaskInfo info = (TaskInfo) objectInputStream.readObject();
                objectInputStream.close();
                copyInfo(info);
            } catch (ClassNotFoundException e) {
                LOGGER.info("Task Not Found!", e);
            } catch (FileNotFoundException e) {
                LOGGER.info("File Not Found!", e);
            } catch (IOException e) {
                LOGGER.info("IO problem!", e);
            }  
        }  
        
    }
    
    private void copyInfo(TaskInfo info){
        
        this.SerializeName = info.SerializeName;
        this.url = info.url;
        this.fileSavePath = info.fileSavePath;
        
        this.fileType = info.fileType;
        this.fileName = info.fileName;
        this.fileSize = info.fileSize;
        this.progress = info.progress;
        this.remainTime = info.remainTime;
        this.downloadSpeed = info.downloadSpeed;
        
        this.finishTime = info.finishTime;
        this.createTime = info.createTime;
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
    public long getStartOffset() {
        return startOffset;
    }
    
    @Override
    public long getEndOffset() {
        return endOffset;
    }
    
    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        
        if(this.fileName != null) return;
        
        this.fileName = fileName;
        this.SerializeName = fileName.concat(".temp");
        readTask();
        
    }
    
    @Override
    public void setStartOffset(long startOffset) {
        this.startOffset = startOffset;
    }
    
    @Override
    public void setEndOffset(long endOffset) {
        this.endOffset = endOffset;
    }

    @Override
    public void setFileSize(long fileSize) {
        this.fileSize = String.valueOf(fileSize);
    }
    

}
