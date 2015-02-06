package flashGetter.downloader.task;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import flashGetter.downloader.TaskMapper;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public class TaskThread implements Runnable{
    
    private static final Logger LOGGER = Logger.getLogger(TaskThread.class);
    
    
    public TaskThread(DownloadingTask task) {
        try {
            URL url = new URL(task.getDownloadURL());
            URLConnection connection = url.openConnection();
            long fileSize = connection.getContentLengthLong();
            
        } catch (IOException e) {
            LOGGER.info("URL is not connected", e);
        }
         
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

}
