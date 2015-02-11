package flashGetter.view.controlbar;

import javax.swing.JDialog;

import flashGetter.Resources;
import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;
import flashGetter.view.InfoEvent;
import flashGetter.view.MainFrame;
import flashGetter.view.controlbar.ControlBarPlatter.ActionType;

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
                new InfoEvent());
        
        addPanel(Resources.pauseTask,
                Resources.pauseTaskChoosed, 
                null, 
                "Pause Task", 
                new InfoEvent());
        
        addPanel(Resources.deleteTask,
                Resources.deleteTaskChoosed,
                null,
                "Delete Task",
                new InfoEvent());
        
    }


    
}
