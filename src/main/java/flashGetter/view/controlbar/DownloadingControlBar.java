package flashGetter.view.controlbar;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;

import flashGetter.Resources;
import flashGetter.view.InfoEvent;
import flashGetter.view.MainFrame;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DownloadingControlBar extends ControlBar{

    public DownloadingControlBar() {
        
        
        addPanel(Resources.newTask, 
                Resources.newTaskChoosed,
                null,
                "Create a Task",
                new InfoEvent());
        
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
