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
 

    
    /*
     *  the influence of synchronization to performance is not
     *  considerable enough to care about, because the task information
     *  shown in JTable doesn't require high real-time performance 
     */
    
    @Override
    public synchronized void invoke(InfoEvent event) {
        
        if(!match(event.getTarget())) return;
        execute(event);
        
    }
    
    protected abstract boolean match(Class<?> clazz);
    protected abstract void execute(InfoEvent event);
    
    @Override
    public boolean filter(InfoEvent event) {
        return TaskTableModel.class.isAssignableFrom(event.getTarget());
    }

     
 
}
