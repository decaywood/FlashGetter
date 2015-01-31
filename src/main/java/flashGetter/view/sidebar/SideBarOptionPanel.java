package flashGetter.view.sidebar;

import java.awt.Event;

import javax.swing.ImageIcon;

import flashGetter.view.OptionPanel;
import flashGetter.view.ViewEvent;
import flashGetter.view.ViewEventDispatcher;
import flashGetter.view.ViewEventHandler;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class SideBarOptionPanel extends OptionPanel implements ViewEventHandler<SideBarOptionPanel>{
    
    private ViewEvent sideBarEvent;
    private boolean isChoosed;
    private int type;
    
    public static final int DOWNLOADING = 0x000000ff;
    public static final int DOWNLOADED =  0x0000ff00;
    public static final int DELETED =     0x00ff0000;
    
    public SideBarOptionPanel(
            int type,
            ImageIcon icon1,
            ImageIcon icon2,
            String option,
            ViewEvent... event){
        this(type, icon1, icon2, option, false, event);
    }
    
    public SideBarOptionPanel(
            int type,
            ImageIcon icon1,
            ImageIcon icon2,
            String option,
            boolean highlight,
            ViewEvent... event){
        super(icon1, icon2, option, event);
        
        this.type = type;
        ViewEventDispatcher.InnerClass.instance.register(this);
        if(highlight) {
            lightComponent();
            isChoosed = true;
        }
        sideBarEvent = new ViewEvent().setTarget(SideBarOptionPanel.class).setData(type);
        
    }
    
    @Override
    protected void mouseExited() {
        if(isChoosed) return;
        darkComponent();
    }
    
    @Override
    protected void mousePressed() {
        super.mousePressed();
        ViewEventDispatcher.InnerClass.instance.fireEvent(sideBarEvent);
    }

    @Override
    public void invoke(ViewEvent event) {
        if((int)event.getData() == type){
            isChoosed = true;
            lightComponent();
            return;
        }
        isChoosed = false;
        darkComponent();
    }

    @Override
    public Class<SideBarOptionPanel> getGroupClass() {
        return SideBarOptionPanel.class;
    }
    

}
