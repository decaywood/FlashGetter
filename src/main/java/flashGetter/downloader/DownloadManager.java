package flashGetter.downloader;

import java.net.URL;

import flashGetter.downloader.TaskEvent.TaskEventType;
import flashGetter.downloader.executor.DownloadingExecutor;
import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;
import flashGetter.view.InfoEvent;
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
    
    public static final int CREATE_TASK = 0x0000000f;
    public static final int START_TASK  = 0x000000f0;
    public static final int PAUSE_TASK  = 0x00000f00;
    public static final int DELETE_TASK = 0x0000f000;
    
    
    private DownloadingOperation downloadingExecutor;
    private DownloadedOperation downloadedExecutor;
    private DeletedOperation deletedExecutor;
    
    public DownloadManager() {
        downloadingExecutor = new DownloadingExecutor();
        downloadingExecutor.addManagerListener(event -> {
             if(!event.typeEqual(TaskEventType.DOWNLOADING_FINISHED)) return;
             
        });
        
        downloadingExecutor.addManagerListener(event -> {
            if(!event.typeEqual(TaskEventType.INFORMATION_UPDATE)) return;
        });
//        downloadedExecutor = 
//        deletedExecutor = 
        EventDispatcher.InnerClass.instance.register(this);
    }

    @Override
    public void invoke(InfoEvent event) {
        
        int operationKey = event.getOperationKey();
        
        if(operationKey == CREATE_TASK)
            downloadingExecutor.createTask(event.getInfo(0), event.getInfo(1));
        if(operationKey == START_TASK)
            downloadingExecutor.startTask(event.getTaskIDs());
        if(operationKey == PAUSE_TASK)
            downloadingExecutor.pauseTask(event.getTaskIDs());
        if(operationKey == DELETE_TASK)
            downloadingExecutor.pauseTask(event.getTaskIDs());
        
    }


    @Override
    public boolean filter(InfoEvent event) {
        return DownloadManager.class.isAssignableFrom(event.getTarget());
    }

    

}
