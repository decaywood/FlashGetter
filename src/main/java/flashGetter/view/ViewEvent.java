package flashGetter.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class ViewEvent {
    
    Class<? extends JComponent> target;
    
    public ViewEvent setTarget(Class<? extends JComponent> target) {
        this.target = target;
        return this;
    }
    
    public Class<? extends JComponent> getTarget() {
        return target;
    }

}
