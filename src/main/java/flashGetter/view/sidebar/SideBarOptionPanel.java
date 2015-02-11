package flashGetter.view.sidebar;


import javax.swing.ImageIcon;

import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;
import flashGetter.view.InfoEvent;
import flashGetter.view.OptionPanel;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class SideBarOptionPanel extends OptionPanel implements EventHandler {
    
    private InfoEvent sideBarEvent;
    private boolean isChoosed;
    private OptionType type;
    
    public static enum OptionType{
        DOWNLOADING,
        DOWNLOADED,
        DELETED
    }
    
    
    public SideBarOptionPanel(
            OptionType type,
            ImageIcon icon1,
            ImageIcon icon2,
            String option,
            InfoEvent... event){
        this(type, icon1, icon2, option, false, event);
    }
    
    public SideBarOptionPanel(
            OptionType type,
            ImageIcon icon1,
            ImageIcon icon2,
            String option,
            boolean highlight,
            InfoEvent... event){
        super(icon1, icon2, option, event);
        
        this.type = type;
        EventDispatcher.InnerClass.instance.register(this);
        if(highlight) {
            lightComponent();
            isChoosed = true;
        }
        sideBarEvent = new InfoEvent().setTarget(SideBarOptionPanel.class).setOperationKey(type);
        
    }
    
    @Override
    protected void mouseExited() {
        if(isChoosed) return;
        darkComponent();
    }
    
    @Override
    protected void mousePressed() {
        super.mousePressed();
        EventDispatcher.InnerClass.instance.fireEvent(sideBarEvent);
    }

    @Override
    public void invoke(InfoEvent event) {
        if(event.getOperationKey() == type){
            isChoosed = true;
            lightComponent();
            return;
        }
        isChoosed = false;
        darkComponent();
    }

   
    @Override
    public boolean filter(InfoEvent event) {
        return SideBarOptionPanel.class.isAssignableFrom(event.getTarget());
    }

}
