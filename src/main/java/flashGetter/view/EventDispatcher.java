package flashGetter.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class EventDispatcher {
    
    private List<EventHandler<?>> listeners;
    
    public static class InnerClass{
        
        public static final EventDispatcher instance = new EventDispatcher();
        
    }
    
    private EventDispatcher() {
        listeners = new ArrayList<EventHandler<?>>();
    }
    
    public void register(EventHandler<?> handler){
        listeners.add(handler);
    }
    
   public void fireEventStream(InfoEvent... event){
        
       Arrays.stream(event).forEach(e -> fireEvent(e));
        
    }
    
    public void fireEvent(InfoEvent event){
        
        if(event == null) return;
        
        listeners.stream()
        .filter(target -> target.getGroupClass().isAssignableFrom(event.getTarget()))
        .forEach(listener -> listener.invoke(event));
    }

}
