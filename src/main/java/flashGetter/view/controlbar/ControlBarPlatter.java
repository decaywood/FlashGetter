package flashGetter.view.controlbar;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;
import flashGetter.view.InfoEvent;
import flashGetter.view.model.TaskTableModel;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class ControlBarPlatter extends JPanel implements EventHandler {
    
    protected static final int CREATE_DIALOG = 0x0000000f;
    
    private Map<Class<? extends ControlBar>, ControlBar> controlBars;
    
    public ControlBarPlatter() {
        
        
        controlBars = new HashMap<Class<? extends ControlBar>, ControlBar>();
        
        EventDispatcher dispatcher = EventDispatcher.InnerClass.instance;
        dispatcher.register(this);
        
        controlBars.put(DeletedControlBar.class, new DeletedControlBar());
        controlBars.put(DownloadedControlBar.class, new DownloadedControlBar());
        controlBars.put(DownloadingControlBar.class, new DownloadingControlBar());
        
        add(controlBars.get(DownloadingControlBar.class));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
    }

    @Override
    public void invoke(InfoEvent event) {
        if(event.getOperationKey() == CREATE_DIALOG){
            new TaskDialog();
            return;
        }
        removeAll();
        add(controlBars.get(event.getTarget()));
        updateUI();
    }


    @Override
    public boolean filter(InfoEvent event) {
        return ControlBar.class.isAssignableFrom(event.getTarget());
    }

}
