package flashGetter.view;

import java.awt.event.MouseEvent;


/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class InfoEvent {
    
    private Class<?> target;
    private int operationKey;
    
    private String[] info;
    
    private Long[] taskIDs;
    
    public void setTaskID(Long... taskIDs) {
        this.taskIDs = taskIDs;
    }
    
    public Long[] getTaskIDs() {
        return taskIDs;
    }
    
    public InfoEvent setInfo(String... info) {
        this.info = info;
        return this;
    }
    
    public String getInfo(int i) {
        return info[i];
    }
    
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
