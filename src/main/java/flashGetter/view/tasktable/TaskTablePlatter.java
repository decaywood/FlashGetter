package flashGetter.view.tasktable;

import javax.swing.JComponent;
import javax.swing.JScrollPane;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class TaskTablePlatter extends JScrollPane {
    
    public TaskTablePlatter(JComponent component) {
        
        super(component);
        
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        
        
    }

}
