package flashGetter.view.controlbar;

import flashGetter.Resources;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DownloadedControlBarView extends ControlBarView {
    
    public DownloadedControlBarView() {
        
        
        addPanel(Resources.newTask, Resources.newTaskChoosed, "New Task", "Create a Task");
        
        addPanel(Resources.deleteTask, Resources.deleteTaskChoosed, null, "Delete Task");
        
    }

}
