package flashGetter.downloader.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import flashGetter.downloader.DownloadedOperation;
import flashGetter.downloader.ManagerListener;
import flashGetter.downloader.TaskMapper;
import flashGetter.downloader.task.Task;
import flashGetter.downloader.task.Task.TaskState;

/**
 * @author decaywood
 * 
 * 2015年2月27日
 * 
 */
public class DownloadedExecutor implements DownloadedOperation {
    
    private List<ManagerListener> listeners;
    private List<Long> downloadedTaskIDs;
    
    public DownloadedExecutor() {
        listeners = new ArrayList<ManagerListener>();
        downloadedTaskIDs = new ArrayList<Long>();
    }

    @Override
    public void addManagerListener(ManagerListener listener) {
        listeners.add(listener);
    }

    @Override
    public void fireTaskInfo(Task event) {
        listeners.stream().forEach(listener -> listener.onEvent(event));
    }

    @Override
    public void deleteTask(long... taskIDs) {
        Arrays.stream(taskIDs).mapToObj(taskID -> getTask(taskID))
        .forEach(taskInfo -> {
            taskInfo.changeTaskState(TaskState.TASK_DELETED);
            fireTaskInfo(taskInfo);
        });
    }

    @Override
    public void offerFinishedTask(long taskID) {
        downloadedTaskIDs.add(taskID);
        Task task = TaskMapper.InnerClass.instance.getTaskInfo(taskID);
        fireTaskInfo(task);
    }

    private Task getTask(long taskID){
        TaskMapper mapper = TaskMapper.InnerClass.instance;
        return mapper.getTaskInfo(taskID);
    }
    
}
