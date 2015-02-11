package flashGetter.downloader;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import flashGetter.downloader.task.Task.TaskState;
import flashGetter.downloader.task.TaskInfo;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public class TaskMapper {
    
    private final ConcurrentHashMap<Long, TaskInfo> taskMapper;
    
    /*
     * all task informations are managed in taskMapper
     * the whole program can retrieve the task information(taskInfo)
     * through it. any class instance can share task information 
     * by transform the taskID rather than rebuild a instance.
     * 
     * but the only thing you should pay attention, the task 
     * informations changed in instanceA, it would effect instanceB
     * which rely on the task information,so think cautiously when you
     * change task information.
     * 
     * it is designed to save heap memory
     * 
     */
    
    private TaskMapper() {
        taskMapper = new ConcurrentHashMap<Long, TaskInfo>();
    }
    
    public static class InnerClass{
        public static final TaskMapper instance = new TaskMapper(); 
    }
    
    
    
    public void registerTask(Long taskID, TaskInfo taskInfo){
        taskMapper.put(taskID, taskInfo);
    }
    
    public TaskInfo getTaskInfo(Long taskID){
        return taskMapper.get(taskID);
    }
    
    public Stream<TaskInfo> getUpdateTaskInfo(){
        return taskMapper.values().stream().filter(taskInfo -> taskInfo.stateEqual(TaskState.TASK_UPDATE));
    }
    
    public Stream<TaskInfo> getBeginTaskInfo(){
        return taskMapper.values().stream().filter(taskInfo -> taskInfo.stateEqual(TaskState.TASK_BEGIN));
    }
    
    public Stream<TaskInfo> getTaskInfoStream(){
        return taskMapper.values().stream();
    }
    
    
    public void dropTask(Long[] taskIDs){
        Arrays.stream(taskIDs);
    }
    
    public void dropTask(List<Long> taskIDs){
        dropTask(taskIDs.stream());
    }
    
    public void dropTask(Stream<Long> taskIDsStream){
        taskIDsStream.forEach(id -> dropTask(id));
    }
    
    public void dropTask(Long taskID){
        taskMapper.remove(taskID);
    }

}
