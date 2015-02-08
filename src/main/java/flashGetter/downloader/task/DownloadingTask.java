package flashGetter.downloader.task;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public interface DownloadingTask {
    
    public long getStartOffset();
    public String getDownloadURL();
    public String getSavePath();
    public String getFileName();
    
    public void setStartOffset(long startOffset);
    public void setFileName(String fileName);
    public void setFileSize(long fileSize);
}
