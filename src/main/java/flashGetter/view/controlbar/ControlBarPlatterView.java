package flashGetter.view.controlbar;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import flashGetter.view.ViewEvent;
import flashGetter.view.ViewEventDispatcher;
import flashGetter.view.ViewEventHandler;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class ControlBarPlatterView extends JPanel implements ViewEventHandler<ControlBarView> {
    
    private Map<Class<? extends ControlBarView>, ControlBarView> controlBars;
    
    public ControlBarPlatterView() {
        
        controlBars = new HashMap<Class<? extends ControlBarView>, ControlBarView>();
        
        ViewEventDispatcher dispatcher = ViewEventDispatcher.InnerClass.instance;
        dispatcher.register(this);
        
        controlBars.put(DeletedControlBarView.class, new DeletedControlBarView());
        controlBars.put(DownloadedControlBarView.class, new DownloadedControlBarView());
        controlBars.put(DownloadingControlBarView.class, new DownloadingControlBarView());
        
        add(controlBars.get(DownloadingControlBarView.class));
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        
    }

    @Override
    public void invoke(ViewEvent event) {
        removeAll();
        add(controlBars.get(event.getTarget()));
        updateUI();
    }

    @Override
    public Class<ControlBarView> getGroupClass() {
        return ControlBarView.class;
    }

    

}
