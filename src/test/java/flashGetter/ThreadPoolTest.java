package flashGetter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import flashGetter.downloader.task.DownloadingTask;
import flashGetter.downloader.task.FTPTaskThread;
import flashGetter.downloader.task.TaskExecutor;
import flashGetter.downloader.task.TaskInfo;

/**
 * @author decaywood
 * 
 * 2015年2月10日
 * 
 */
public class ThreadPoolTest {
    
    
    
    public static void main(String[] args) throws InterruptedException {
       
        TaskExecutor executor = new TaskExecutor();
        String urlpath = "ftp://flashgetter:123456@192.168.59.1:21/testMovie.rmvb";
        executor.createTask(urlpath, "E:\\test\\result\\");
        Thread.sleep(10000);
        executor.pauseTask(1l);
        System.out.println("pause!");
        Thread.sleep(5000);
        executor.startTask(1l);
    }

}
