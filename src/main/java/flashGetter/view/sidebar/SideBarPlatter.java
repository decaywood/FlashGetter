package flashGetter.view.sidebar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import flashGetter.view.ViewEvent;
import flashGetter.view.ViewEventDispatcher;
import flashGetter.view.ViewEventHandler;

/**
 * @author decaywood
 * 
 * 2015年1月31日
 * 
 */
public class SideBarPlatter extends JPanel implements ViewEventHandler<SideBarPlatter.SidebarPlatter>{
    
    private Map<Class<? extends JPanel>, JPanel> sideBars;
    
    public static interface SidebarPlatter {}
    
    public SideBarPlatter() {
        sideBars = new HashMap<Class<? extends JPanel>, JPanel>();
        ViewEventDispatcher.InnerClass.instance.register(this);
        sideBars.put(SideBar.class, new SideBar());
        sideBars.put(SideBarChanged.class, new SideBarChanged());
        add(sideBars.get(SideBar.class));
    }

    @Override
    public void invoke(ViewEvent event) {
        removeAll();
        add(sideBars.get(event.getTarget()));
        updateUI();
    }

    @Override
    public Class<SideBarPlatter.SidebarPlatter> getGroupClass() {
        return SideBarPlatter.SidebarPlatter.class;
    }

}
