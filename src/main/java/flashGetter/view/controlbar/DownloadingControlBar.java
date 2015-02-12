package flashGetter.view.controlbar;

import flashGetter.Resources;
import flashGetter.downloader.DownloadManager.TaskEventType;
import flashGetter.view.InfoEvent;
import flashGetter.view.controlbar.ControlBarPlatter.ActionType;
import flashGetter.view.tasktable.DownloadingTable;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DownloadingControlBar extends ControlBar {

    public DownloadingControlBar() {
        
        addPanel(Resources.newTask, 
                Resources.newTaskChoosed,
                null,
                "Create a Task",
                new InfoEvent()
                .setOperationKey(ActionType.CREATE_DIALOG)
                .setTarget(ControlBar.class));
        
        addPanel(Resources.startTask,
                Resources.startTaskChoosed, 
                null,
                "Start Task",
                new InfoEvent()
                .setTarget(DownloadingTable.class)
                .setOperationKey(TaskEventType.TASK_START));
        
        addPanel(Resources.pauseTask,
                Resources.pauseTaskChoosed, 
                null, 
                "Pause Task", 
                new InfoEvent()
                .setTarget(DownloadingTable.class)
                .setOperationKey(TaskEventType.TASK_PAUSE));
        
        addPanel(Resources.deleteTask,
                Resources.deleteTaskChoosed,
                null,
                "Delete Task",
                new InfoEvent()
                .setTarget(DownloadingTable.class)
                .setOperationKey(TaskEventType.TASK_DELETE));
        
    }


    
}
