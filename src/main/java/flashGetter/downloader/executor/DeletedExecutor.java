package flashGetter.downloader.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import flashGetter.downloader.DeletedOperation;
import flashGetter.downloader.ManagerListener;
import flashGetter.downloader.TaskMapper;
import flashGetter.downloader.task.Task;
import flashGetter.downloader.task.Task.TaskState;

/**
 * @author decaywood
 * 
 * 2015年2月23日
 * 
 */
public class DeletedExecutor implements DeletedOperation {

    private List<ManagerListener> listeners;
    private List<Long> deletedTaskIDs;
    
    public DeletedExecutor() {
        listeners = new ArrayList<ManagerListener>();
        deletedTaskIDs = new ArrayList<Long>();
    }

    @Override
    public void offerDeletedTask(long... taskIDs) {
        Arrays.stream(taskIDs).forEach(taskID -> deletedTaskIDs.add(taskID));
    }

    @Override
    public void recoverTask(long... taskIDs) {
        Arrays.stream(taskIDs).mapToObj(taskID -> getTask(taskID))
        .forEach(taskInfo -> {
            taskInfo.changeTaskState(TaskState.TASK_RECOVER);
            fireTaskInfo(taskInfo);
        });
    }

    @Override
    public void removeTask(long... taskIDs) {
        Arrays.stream(taskIDs).mapToObj(taskID -> getTask(taskID))
        .forEach(taskInfo -> fireTaskInfo(taskInfo));
    }

    @Override
    public void removeAllTask() {
        deletedTaskIDs.stream().map(taskID -> getTask(taskID))
        .forEach(taskInfo -> fireTaskInfo(taskInfo));
    }

    @Override
    public void addManagerListener(ManagerListener listener) {
        listeners.add(listener);
    }

    @Override
    public void fireTaskInfo(Task event) {
        listeners.stream().forEach(listener -> listener.onEvent(event));
    }

    private Task getTask(long taskID){
        TaskMapper mapper = TaskMapper.InnerClass.instance;
        return mapper.getTaskInfo(taskID);
    }

}
