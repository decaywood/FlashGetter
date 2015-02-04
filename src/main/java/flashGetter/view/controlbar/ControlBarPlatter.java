package flashGetter.view.controlbar;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import flashGetter.view.InfoEvent;
import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class ControlBarPlatter extends JPanel implements EventHandler<ControlBar> {
    
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
        removeAll();
        add(controlBars.get(event.getTarget()));
        updateUI();
    }

    @Override
    public Class<ControlBar> getGroupClass() {
        return ControlBar.class;
    }

    

}
