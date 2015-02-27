package flashGetter.view.controlbar;

import java.awt.event.MouseAdapter;

import flashGetter.Resources;
import flashGetter.downloader.DownloadManager.TaskEventType;
import flashGetter.view.InfoEvent;
import flashGetter.view.controlbar.ControlBarPlatter.ActionType;
import flashGetter.view.tasktable.DownloadedTable;
import flashGetter.view.tasktable.DownloadingTable;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DownloadedControlBar extends ControlBar {
    
    public DownloadedControlBar() {
        
        
        addPanel(Resources.newTask, 
                Resources.newTaskChoosed,
                null,
                "Create a Task", 
                new InfoEvent()
        .setOperationKey(ActionType.CREATE_DIALOG)
        .setTarget(ControlBar.class));
        
        addPanel(Resources.deleteTask, 
                Resources.deleteTaskChoosed, 
                null, 
                "Delete Task",
                new InfoEvent()
            .setTarget(DownloadedTable.class)
            .setOperationKey(TaskEventType.TASK_AFTER_DELETE));
        
    }

}
