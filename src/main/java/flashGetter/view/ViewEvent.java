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
    
    Class<?> target;
    Object data;
    
    public ViewEvent setTarget(Class<?> target) {
        this.target = target;
        return this;
    }
    
    public ViewEvent setData(Object data) {
        this.data = data;
        return this;
    }
    
    public Class<?> getTarget() {
        return target;
    }
    
    public Object getData() {
        return data;
    }

}
