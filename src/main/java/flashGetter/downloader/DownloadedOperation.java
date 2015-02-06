package flashGetter.downloader;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public interface DownloadedOperation {
    
    public void createTask(String downloadAddr, String savePath);

    public void deleteTask(String[] taskNumber);
    
}
