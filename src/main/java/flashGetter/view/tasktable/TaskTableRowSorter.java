package flashGetter.view.tasktable;

import javax.swing.table.TableRowSorter;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class TaskTableRowSorter<T extends TaskTableModel> extends TableRowSorter<T>{
    
    public TaskTableRowSorter(T model) {
        
        super(model);
        
    }

}
