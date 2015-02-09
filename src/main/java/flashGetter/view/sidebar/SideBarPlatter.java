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
public class SideBarPlatter extends JPanel implements EventHandler {
    
    private Map<Class<? extends JPanel>, JPanel> sideBars;
    
    public static interface SidebarPlatter {}
    
    public SideBarPlatter() {
        sideBars = new HashMap<Class<? extends JPanel>, JPanel>();
        EventDispatcher.InnerClass.instance.register(this);
        sideBars.put(SideBar.class, new SideBar());
        sideBars.put(SideBarChanged.class, new SideBarChanged());
        add(sideBars.get(SideBar.class));
    }

    @Override
    public void invoke(InfoEvent event) {
        removeAll();
        add(sideBars.get(event.getTarget()));
        updateUI();
    }

   
    @Override
    public boolean filter(InfoEvent event) {
        return SideBarPlatter.SidebarPlatter.class.isAssignableFrom(event.getTarget());
    }
    

}
