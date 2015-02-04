package flashGetter.view.sidebar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import flashGetter.view.InfoEvent;
import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;

/**
 * @author decaywood
 * 
 * 2015年1月31日
 * 
 */
public class SideBarPlatterView extends JPanel implements EventHandler<SideBarPlatterView.SidebarPlatter>{
    
    private Map<Class<? extends JPanel>, JPanel> sideBars;
    
    public static interface SidebarPlatter {}
    
    public SideBarPlatterView() {
        sideBars = new HashMap<Class<? extends JPanel>, JPanel>();
        EventDispatcher.InnerClass.instance.register(this);
        sideBars.put(SideBarView.class, new SideBarView());
        sideBars.put(SideBarChangedView.class, new SideBarChangedView());
        add(sideBars.get(SideBarView.class));
    }

    @Override
    public void invoke(InfoEvent event) {
        removeAll();
        add(sideBars.get(event.getTarget()));
        updateUI();
    }

    @Override
    public Class<SideBarPlatterView.SidebarPlatter> getGroupClass() {
        return SideBarPlatterView.SidebarPlatter.class;
    }

}
