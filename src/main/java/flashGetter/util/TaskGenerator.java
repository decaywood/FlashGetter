package flashGetter.util;

import flashGetter.downloader.task.FTPTaskThread;
import flashGetter.downloader.task.TaskInfo;
import flashGetter.downloader.task.TaskRunnable;

/**
 * @author decaywood
 * 
 * 2015年2月8日
 * 
 */
public class TaskGenerator {
    
    public static TaskRunnable generateTask(TaskInfo taskInfo){
        
        String url = taskInfo.getDownloadURL();
        
        boolean isURL = match("ftp", url);
        if(isURL) return new FTPTaskThread(taskInfo);
        
        boolean isHTTP = match("http", url);
//        if(isHTTP) return new FTPTaskThread(taskInfo);
            
        return null;
    }
    
    private static boolean match(String regex, String URL){
        return URL.contains(regex + "://") 
                || URL.contains(regex.toUpperCase() + "://");
    }
    
    
    

}
