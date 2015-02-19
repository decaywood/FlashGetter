package flashGetter.downloader;

import flashGetter.downloader.executor.DownloadingExecutor;
import flashGetter.downloader.task.Task;
import flashGetter.downloader.task.Task.TaskState;
import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;
import flashGetter.view.InfoEvent;
import flashGetter.view.model.DeletedTableModel;
import flashGetter.view.model.DownloadedTableModel;
import flashGetter.view.model.DownloadingTableModel;
import flashGetter.view.model.TaskTableModel;
/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public class DownloadManager implements EventHandler {
    
    private static DownloadManager manager;
    
    public static void initializeDownloadManager(){
        if(manager != null) return;
        manager = new DownloadManager();
    }
    
    public static enum TaskEventType {
        TASK_CREATE,
        TASK_START,
        TASK_PAUSE,
        TASK_DELETE,
    }
     
    
    
    private DownloadingOperation downloadingExecutor;
    private DownloadedOperation downloadedExecutor;
    private DeletedOperation deletedExecutor;
    
    /*
     *  the main purpose of adding listener (rather than implement behavior 
     *  inside the executor) is to build a regulation of transformation. 
     */
    public DownloadManager() {
        downloadingExecutor = new DownloadingExecutor();
        
        downloadingExecutor.addManagerListener(event -> {
            
            if(event.stateEqual(TaskState.TASK_BEGIN))
                sendInfoEvent(event, DownloadingTableModel.class, TaskState.TASK_BEGIN);
            
            else if(event.stateEqual(TaskState.TASK_UPDATE))
                sendInfoEvent(event, DownloadingTableModel.class, TaskState.TASK_UPDATE);
            
            else if(event.stateEqual(TaskState.TASK_FINISHED)){
                
                sendInfoEvent(event, DownloadingTableModel.class, TaskState.TASK_FINISHED);
                sendInfoEvent(event, DownloadedTableModel.class, TaskState.TASK_FINISHED);
                
            }
            
            else if(event.stateEqual(TaskState.TASK_DELETED)){
                
                sendInfoEvent(event, DownloadingTableModel.class, TaskState.TASK_DELETED);
                sendInfoEvent(event, DeletedTableModel.class, TaskState.TASK_DELETED);
                
            }
        });
        
//        downloadedExecutor = 
//        deletedExecutor = 
        EventDispatcher.InnerClass.instance.register(this);
    }

    @Override
    public void invoke(InfoEvent event) {
        
        TaskEventType operationKey = (TaskEventType) event.getOperationKey();
        
        if(operationKey == TaskEventType.TASK_CREATE)
            downloadingExecutor.createTask(event.getInfo(0), event.getInfo(1));
        if(operationKey == TaskEventType.TASK_START)
            downloadingExecutor.startTask(event.getTaskIDs());
        if(operationKey == TaskEventType.TASK_PAUSE)
            downloadingExecutor.pauseTask(event.getTaskIDs());
        if(operationKey == TaskEventType.TASK_DELETE)
            downloadingExecutor.deleteTask(event.getTaskIDs());
        
        
    }


    private void sendInfoEvent(
            Task event,
            Class<? extends TaskTableModel> target, 
            TaskState state){
        
        if(!event.stateEqual(state)) return;
        InfoEvent infoEvent = new InfoEvent();
        infoEvent
        .setTarget(target)
        .setTaskID(event.getTaskID())
        .setOperationKey(state);
        EventDispatcher.InnerClass.instance.fireEvent(infoEvent);
        
    }
    
    @Override
    public boolean filter(InfoEvent event) {
        return DownloadManager.class.isAssignableFrom(event.getTarget());
    }

    

}
