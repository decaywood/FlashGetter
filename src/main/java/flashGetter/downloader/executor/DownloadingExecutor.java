package flashGetter.downloader.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import flashGetter.downloader.DownloadingOperation;
import flashGetter.downloader.TaskMapper;
import flashGetter.downloader.task.FTPTaskThread;
import flashGetter.downloader.task.TaskInfo;
import flashGetter.downloader.task.TaskRunnable;
import flashGetter.util.SequenceGenerator;
import flashGetter.util.TaskGenerator;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public class DownloadingExecutor implements DownloadingOperation {
    
    private ExecutorService executor;
    private List<Long> taskSequences;
    
    public DownloadingExecutor() {
        executor = Executors.newCachedThreadPool();
        taskSequences = new ArrayList<Long>();
    }


    @Override
    public void createTask(String downloadAddr, String savePath) {

        Long sequence = SequenceGenerator.generateSequence();
        TaskInfo taskInfo = new TaskInfo(sequence, downloadAddr, savePath);
        TaskMapper.InnerClass.instance.registerTask(sequence, taskInfo);
        taskSequences.add(sequence);
        TaskRunnable taskThread = TaskGenerator.generateTask(taskInfo);

    }

    @Override
    public void startTask(Long[] taskNumber) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void pauseTask(Long[] taskNumber) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteTask(Long[] taskNumber) {
        // TODO Auto-generated method stub
        
    }

    

   

}
