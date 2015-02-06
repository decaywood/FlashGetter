package flashGetter.view.model;

import javax.swing.table.DefaultTableModel;

import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;
import flashGetter.view.InfoEvent;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class TaskTableModel extends DefaultTableModel implements EventHandler<TaskTableModel>{
    
    private int columnCount;
    private Class<? extends TaskTableModel> ID;
    
    public TaskTableModel(int number, Class<? extends TaskTableModel> id) {
        
        EventDispatcher.InnerClass.instance.register(this);
        ID = id;
        columnCount = number;
        
    }


    @Override
    public int getColumnCount() {
        return columnCount;
    }
 


    @Override
    public void invoke(InfoEvent event) {
        if(event.getTarget() != ID) return;
        int key = event.getOperationKey();
        
        //handle....
        
//        setValueAt(aValue, row, column);
        
    }



    @Override
    public Class<TaskTableModel> getGroupClass() {
        return TaskTableModel.class;
    }
    
   
     
 
}
