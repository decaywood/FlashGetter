package flashGetter.downloader;

/**
 * @author decaywood
 * 
 * 2015年2月9日
 * 
 * mention manager to do something
 * 
 */

@FunctionalInterface
public interface ManagerListener {
    
    public void onEvent(TaskEvent event);
    
    
}
