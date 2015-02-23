package flashGetter.view.controlbar;

import java.awt.event.MouseAdapter;

import flashGetter.Resources;
import flashGetter.downloader.DownloadManager.TaskEventType;
import flashGetter.view.InfoEvent;
import flashGetter.view.tasktable.DeletedTable;
import flashGetter.view.tasktable.DownloadingTable;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DeletedControlBar extends ControlBar {
    
    public DeletedControlBar() {
        
        addPanel(Resources.recoverTask, 
                Resources.recoverTaskChoosed, 
                null, 
                "Create a Task",
                new InfoEvent()
                .setTarget(DeletedTable.class)
                .setOperationKey(TaskEventType.TASK_RECOVER));
        
        addPanel(Resources.removeTask, 
                Resources.removeTaskChoosed, 
                null, 
                "Remove Task",
                new InfoEvent()
                .setTarget(DeletedTable.class)
                .setOperationKey(TaskEventType.TASK_REMOVE));
        
        addPanel(Resources.removeAllTask, 
                Resources.removeAllTaskChoosed, 
                null, 
                "Remove All Task", 
                new InfoEvent()
                .setTarget(DeletedTable.class)
                .setOperationKey(TaskEventType.TASK_REMOVE_ALL));
        
    }

}
