package flashGetter.view.controlbar;

import javax.swing.JDialog;

import flashGetter.Resources;
import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;
import flashGetter.view.InfoEvent;
import flashGetter.view.MainFrame;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DownloadingControlBar extends ControlBar implements EventHandler<DownloadingControlBar>{

    public DownloadingControlBar() {
        
        EventDispatcher.InnerClass.instance.register(this);
        
        addPanel(Resources.newTask, 
                Resources.newTaskChoosed,
                null,
                "Create a Task",
                new InfoEvent().setTarget(DownloadingControlBar.class));
        
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

    @Override
    public void invoke(InfoEvent event) {
        JDialog dialog = new TaskDialog();
    }

    @Override
    public Class<DownloadingControlBar> getGroupClass() {
        return DownloadingControlBar.class;
    }

    
}
