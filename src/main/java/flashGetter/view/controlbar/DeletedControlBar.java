package flashGetter.view.controlbar;

import java.awt.event.MouseAdapter;

import flashGetter.Resources;
import flashGetter.view.InfoEvent;

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
                new InfoEvent());
        
        addPanel(Resources.removeTask, 
                Resources.removeTaskChoosed, 
                null, 
                "Remove Task",
                new InfoEvent());
        
        addPanel(Resources.removeAllTask, 
                Resources.removeAllTaskChoosed, 
                null, 
                "Remove All Task", 
                new InfoEvent());
        
    }

}
