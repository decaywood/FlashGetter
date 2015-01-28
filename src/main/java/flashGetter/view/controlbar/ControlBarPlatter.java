package flashGetter.view.controlbar;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import flashGetter.view.ViewEvent;
import flashGetter.view.ViewEventDispatcher;
import flashGetter.view.ViewHandler;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class ControlBarPlatter extends JPanel implements ViewHandler<ControlBar> {
    
    private Map<Class<? extends ControlBar>, ControlBar> map;
    
    public ControlBarPlatter() {
        
        map = new HashMap<Class<? extends ControlBar>, ControlBar>();
        
        ViewEventDispatcher dispatcher = ViewEventDispatcher.InnerClass.instance;
        dispatcher.register(this);
        
        map.put(DeletedControlBar.class, new DeletedControlBar());
        map.put(DownloadedControlBar.class, new DownloadedControlBar());
        map.put(DownloadingControlBar.class, new DownloadingControlBar());
        
        add(map.get(DownloadingControlBar.class));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
    }

    @Override
    public void invoke(ViewEvent event) {
        
        removeAll();
        add(map.get(event.getTarget()));
        updateUI();
    }

    @Override
    public Class<ControlBar> getGroupClass() {
        return ControlBar.class;
    }

    

}
