package flashGetter.downloader.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import flashGetter.downloader.DownloadingOperation;
import flashGetter.downloader.ManagerListener;
import flashGetter.downloader.TaskMapper;
import flashGetter.downloader.task.Task;
import flashGetter.downloader.task.Task.TaskState;
import flashGetter.downloader.task.DownloadingTask;
import flashGetter.downloader.task.TaskInfo;
import flashGetter.downloader.task.TaskRunnable;
import flashGetter.util.SequenceGenerator;
import flashGetter.util.TaskGenerator;
import flashGetter.util.TimeUtil;
import flashGetter.util.TimeUtil.UpdateCounter;

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
    private UpdateCounter updateCounter;
    
    public DownloadingExecutor() {
        this.executor = Executors.newCachedThreadPool();
        this.taskTable = new ConcurrentHashMap<Long, TaskRunnable>();
        this.listeners = new ArrayList<ManagerListener>();
        this.updateCounter = TimeUtil.getUpdateCounter();
    }

    public void addManagerListener(ManagerListener listener){
        listeners.add(listener); 
    }
    
    
    /*
     * invoke this method when thread changed its state
     */
    @Override
    public synchronized void fireTaskInfo(Task event){
        if(!event.stateEqual(TaskState.TASK_UPDATE) || updateCounter.canUpdate())
        listeners.forEach(listener -> listener.onEvent(event));
    }
    

    /*
     *  when invoke the createTask method, the task info should be locked 
     *  in order to prevent from invoking restart method, it would result
     *  in IO synchronized problem.
     *  
     *  see startTask method for detail
     *  
     */
    @Override
    public void createTask(String downloadAddr, String savePath) {

        Long sequence = SequenceGenerator.generateSequence();
        TaskInfo taskInfo = new TaskInfo(sequence, downloadAddr, savePath);
        TaskMapper.InnerClass.instance.registerTask(sequence, taskInfo);
        TaskRunnable taskThread = TaskGenerator.generateTask(taskInfo, this); // TaskThread
        
        if(taskThread == null){
            LOGGER.error("URL Not matched", new Exception("URL Error!"));
            return;
        }
        
        taskTable.put(sequence, taskThread);
        taskInfo.lock();
        executor.execute(taskThread);
    }

    /*
     *  wake task according to taskID 
     */
    @Override
    public void startTask(long... taskIDs) {
        Arrays.stream(taskIDs).forEach(taskNumber -> {
            TaskInfo taskInfo = TaskMapper.InnerClass.instance.getTaskInfo(taskNumber);
            if(taskInfo.isLock()) return;
            TaskRunnable taskThread = TaskGenerator.generateTask(taskInfo, this);
            taskTable.put(taskInfo.getTaskID(), taskThread);
            executor.execute(taskThread);
        });
    }

    /*
     *  when invoked pauseTask method, no thread occupied the IO resources 
     *  so the lock can be released
     */
    @Override
    public void pauseTask(long... taskIDs) {
        
        if(taskIDs == null) return;
        
        Arrays.stream(taskIDs).forEach(taskID -> {
            taskTable.get(taskID).terminateTask(); 
            TaskMapper.InnerClass.instance.getTaskInfo(taskID).unLock();
        });
    }

    /*
     *  terminateTask, remove it from table, update change 
     */
    @Override
    public void deleteTask(long... taskIDs) {
        removeOperation(TaskState.TASK_DELETED, taskIDs);
    }

    @Override
    public void finishTask(long... taskIDs) {
        removeOperation(TaskState.TASK_FINISHED, taskIDs);
    }

    private synchronized void removeOperation(TaskState taskState, long... taskIDs){
        Arrays.stream(taskIDs).forEach(taskID -> {
           
            taskTable.get(taskID).terminateTask();
            taskTable.remove(taskID);
            DownloadingTask taskInfo = TaskMapper.InnerClass.instance.getTaskInfo(taskID);
            taskInfo.setFinishTime(TimeUtil.getCurrentTime());
            taskInfo.changeTaskState(taskState);
            fireTaskInfo(taskInfo);
            
        });
    }
    
   

}
