package flashGetter.downloader.task;

import javax.swing.ImageIcon;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public interface DownloadingTask {
    
    public String getProgress();
    
    public String getRemainTime();
    
    public String getDownloadSpeed();
    
    public String getFinishTime();
    
    public String getCreateTime();
    
    public long getStartOffset();
    
    public String getDownloadURL();
    
    public String getSavePath();
    
    public String getTempFilePath();
    
    public String getFileName();
    
    public long getFileSize();
    
    public ImageIcon getFileType();
    
    public void copyInfo(DownloadingTask task);
    
  public void setProgress(String progress);
    
    public void setRemainTime(String remainTime);
    
    public void setDownloadSpeed(String speed);
    
    public void setFinishTime(String finishTime);
    
    public void setCreateTime(String createTime);
    
    public void setStartOffset(long startOffset);
    
    public void setDownloadURL(String url);
    
    public void setSavePath(String savePath);
    
    public void setFileName(String fileName);
    
    public void setFileSize(long fileSize);
    
    public void setFileType(ImageIcon fileType);
    
}
