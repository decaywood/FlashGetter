package flashGetter.downloader.task;

/**
 * @author decaywood
 * 
 * 2015年2月10日
 * 
 */
public interface Task {
    
    public static enum TaskState{
        TASK_BEGIN,
        TASK_FINISHED,
        TASK_UPDATE,
        TASK_RECOVER,
        TASK_DELETED,
        TASK_REMOVE
        
//        TASK_CREATE,
//        TASK_START,
//        TASK_PAUSE,
//        TASK_DELETE,
//        TASK_RECOVER,
//        TASK_REMOVE,
//        TASK_REMOVE_ALL
    }

    public long getTaskID();
    public void changeTaskState(TaskState state);
    public boolean stateEqual(TaskState state);
    
}
