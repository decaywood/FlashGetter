package flashGetter.downloader.task;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public interface DownloadingTask {
    
    public long getStartOffset();
    public long getEndOffset();
    
    public String getDownloadURL();
    public String getSavePath();
    public String getFileName();
    
    public void setStartOffset(long startOffset);
    public void setEndOffset(long endOffset);
    public void setFileName(String fileName);
    public void setFileSize(long fileSize);
}
