package flashGetter.downloader.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import flashGetter.downloader.DownloadManager;
import flashGetter.downloader.DownloadingOperation;
import flashGetter.downloader.ManagerListener;
import flashGetter.downloader.TaskEvent;
import flashGetter.downloader.TaskMapper;
import flashGetter.downloader.TaskEvent.TaskEventType;
import flashGetter.downloader.task.FTPTaskThread;
import flashGetter.downloader.task.TaskInfo;
import flashGetter.downloader.task.TaskRunnable;
import flashGetter.util.SequenceGenerator;
import flashGetter.util.TaskGenerator;
import flashGetter.view.EventDispatcher;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public class DownloadingExecutor implements DownloadingOperation {
    
    private static final Logger LOGGER = Logger.getLogger(DownloadingExecutor.class);
    
    private ExecutorService executor;
    private Map<Long, TaskRunnable> taskTable;
    private List<ManagerListener> listeners;
    
    public DownloadingExecutor() {
        this.executor = Executors.newCachedThreadPool();
        this.taskTable = new ConcurrentHashMap<Long, TaskRunnable>();
        this.listeners = new ArrayList<ManagerListener>();
    }

    public void addManagerListener(ManagerListener listener){
        listeners.add(listener); 
    }
    
    
    /*
     * invoke this method when thread changed its state
     */
    @Override
    public synchronized void fireTaskInfo(TaskEvent event){
        
        if(event.typeEqual(TaskEventType.DOWNLOADING_FINISHED)){
            finishTask(event.getTaskID());
            listeners.forEach(listener -> listener.onEvent(event));
        }
        
        else if(event.typeEqual(TaskEventType.INFORMATION_UPDATE))
            listeners.forEach(listener -> listener.onEvent(event));
        
    }
    

    @Override
    public void createTask(String downloadAddr, String savePath) {

        Long sequence = SequenceGenerator.generateSequence();
        TaskInfo taskInfo = new TaskInfo(sequence, downloadAddr, savePath);
        TaskMapper.InnerClass.instance.registerTask(sequence, taskInfo);
        TaskRunnable taskThread = TaskGenerator.generateTask(taskInfo, this); // TaskThread
        taskTable.put(sequence, taskThread);
        taskInfo.lock();
        executor.execute(taskThread);
    }

    /*
     *  wake task according to taskID 
     */
    @Override
    public void startTask(Long... taskIDs) {
        Arrays.stream(taskIDs).forEach(taskNumber -> {
            TaskInfo taskInfo = TaskMapper.InnerClass.instance.getTaskInfo(taskNumber);
            if(taskInfo.isLock()) return;
            TaskRunnable taskThread = TaskGenerator.generateTask(taskInfo, this);
            taskTable.put(taskInfo.getTaskID(), taskThread);
            executor.execute(taskThread);
        });
    }

    /*
     *  other thread can invoke synchronized method when the thread which possesses
     *  the lock of it invoked wait method
     */
    @Override
    public void pauseTask(Long... taskIDs) {
        Arrays.stream(taskIDs).forEach(taskID -> {
            taskTable.get(taskID).terminateTask(); 
            TaskMapper.InnerClass.instance.getTaskInfo(taskID).unLock();
        });
    }

    /*
     *  terminateTask, remove it from table, update change 
     */
    @Override
    public void deleteTask(Long... taskIDs) {
        Arrays.stream(taskIDs).forEach(taskID -> taskTable.get(taskID).terminateTask());
        removeFromTaskTable(taskIDs);
    }

    @Override
    public void finishTask(Long... taskIDs) {
        removeFromTaskTable(taskIDs);
    }

    
    private void removeFromTaskTable(Long... taskIDs){
        Arrays.stream(taskIDs).forEach(taskID -> taskTable.remove(taskID));
    }
   

}
