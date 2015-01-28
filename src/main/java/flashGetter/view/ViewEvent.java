package flashGetter.view;

import javax.swing.JPanel;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class ViewEvent {
    
    Class<? extends JPanel> target;
    
    public ViewEvent setTarget(Class<? extends JPanel> target) {
        this.target = target;
        return this;
    }
    
    public Class<? extends JPanel> getTarget() {
        return target;
    }

}
