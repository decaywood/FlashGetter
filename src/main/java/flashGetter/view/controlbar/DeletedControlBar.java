package flashGetter.view.controlbar;

import flashGetter.Resources;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DeletedControlBar extends ControlBar {
    
    public DeletedControlBar() {
        
        
        addPanel(Resources.recoverTask, Resources.recoverTaskChoosed, "New Task", "Create a Task");
        
        addPanel(Resources.removeTask, Resources.removeTaskChoosed, null, "Remove Task");
        
        addPanel(Resources.removeAllTask, Resources.removeAllTaskChoosed, null, "Remove All Task");
        
    }

}
