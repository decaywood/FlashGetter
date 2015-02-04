package flashGetter.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class InfoEvent {
    
    private Class<?> target;
    private int operationKey;
    
    public InfoEvent setTarget(Class<?> target) {
        this.target = target;
        return this;
    }
    
    public InfoEvent setOperationKey(int key) {
        this.operationKey = key;
        return this;
    }
    
    public Class<?> getTarget() {
        return target;
    }
    
    public int getOperationKey() {
        return operationKey;
    }

}
