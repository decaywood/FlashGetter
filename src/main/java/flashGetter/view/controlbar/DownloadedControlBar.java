package flashGetter.view.controlbar;

import java.awt.event.MouseAdapter;

import flashGetter.Resources;
import flashGetter.view.InfoEvent;
import flashGetter.view.controlbar.ControlBarPlatter.ActionType;

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
                new InfoEvent());
        
    }

}
