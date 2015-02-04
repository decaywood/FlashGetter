package flashGetter.view.controlbar;

import flashGetter.Resources;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DownloadingControlBarView extends ControlBarView{

    public DownloadingControlBarView() {
        
        addPanel(Resources.newTask, Resources.newTaskChoosed, "New Task", "Create a Task");
        
        addPanel(Resources.startTask, Resources.startTaskChoosed, null, "Start Task");
        
        addPanel(Resources.pauseTask, Resources.pauseTaskChoosed, null, "Pause Task");
        
        addPanel(Resources.deleteTask, Resources.deleteTaskChoosed, null, "Delete Task");
        
    }

    
}
