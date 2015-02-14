package flashGetter.downloader;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
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
    
    /*
     * each mask will combined with the row index to
     * distinct which table the index working for
     */
    public static final int DOWNLOADING_MASK = 0xf0000000;
    public static final int DOWNLOADED_MASK  = 0xff000000;
    public static final int DELETED_MASK     = 0xfff00000;
    
    /*
     * it aims to map the relation from row index to task id 
     * in order to offer a convenient way to pause or delete
     * downloading task in table view 
     */
    private final ConcurrentHashMap<Integer, Long> rowIndex2TaskIDMapper;
    
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
        rowIndex2TaskIDMapper = new ConcurrentHashMap<Integer, Long>();
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
    
    public void getMapStream(BiConsumer<Integer, Long> action){
        rowIndex2TaskIDMapper.forEach(action);
    }
    
    public void updateRowIndexMapper(int mask, int rowIndex, Long taskID){
        System.out.println(rowIndex + " -> " + taskID);
        int index = mask ^ rowIndex;
        rowIndex2TaskIDMapper.put(index, taskID);
    }
    
    public Long getTaskID(int mask, int rowIndex){
        int index = mask ^ rowIndex;
        return rowIndex2TaskIDMapper.get(index);
    }
    
    public Stream<TaskInfo> getDeletedTaskInfo(){
        return taskMapper.values().stream().filter(taskInfo -> taskInfo.stateEqual(TaskState.TASK_DELETED));
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
