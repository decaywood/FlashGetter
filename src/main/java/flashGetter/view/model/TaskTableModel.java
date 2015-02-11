package flashGetter.view.model;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import flashGetter.downloader.TaskMapper;
import flashGetter.downloader.task.Task.TaskState;
import flashGetter.downloader.task.TaskInfo;
import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;
import flashGetter.view.InfoEvent;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public abstract class TaskTableModel extends DefaultTableModel implements EventHandler {
    
    private int columnCount;
    
    public TaskTableModel(int number) {
        
        EventDispatcher.InnerClass.instance.register(this);
        columnCount = number;
        
    }


    @Override
    public int getColumnCount() {
        return columnCount;
    }
 


    @Override
    public void invoke(InfoEvent event) {
        if(!match(event.getTarget())) return;
        TaskState key = (TaskState) event.getOperationKey();
        if(key == TaskState.TASK_BEGIN || key == TaskState.TASK_UPDATE){
//            Arrays.stream(event.getTaskIDs()).forEach(taskID -> updateRow(taskID));
        }
             
        //handle....
        
//        setValueAt(aValue, row, column);
        
    }
    

    abstract boolean match(Class<?> clazz);
    abstract void updateRow(int row, Long taskID);
    
    @Override
    public boolean filter(InfoEvent event) {
        return TaskTableModel.class.isAssignableFrom(event.getTarget());
    }

     
 
}
