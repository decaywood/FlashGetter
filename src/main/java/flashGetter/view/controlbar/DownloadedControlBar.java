package flashGetter.view.controlbar;

import flashGetter.Resources;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DownloadedControlBar extends ControlBar {
    
    public DownloadedControlBar() {
        
        
        addPanel(Resources.newTask, Resources.newTaskChoosed, "New Task", "Create a Task");
        
        addPanel(Resources.deleteTask, Resources.deleteTaskChoosed, null, "Delete Task");
        
    }

}
