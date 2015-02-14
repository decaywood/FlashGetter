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
    private int tempIndex = 0;
    @Override
    public synchronized void invoke(InfoEvent event) {
        if(!match(event.getTarget())) return;
        TaskState key = (TaskState) event.getOperationKey();
        
        if(key == TaskState.TASK_BEGIN){
            TaskMapper.InnerClass.instance.getBeginTaskInfo()
            .forEach(taskInfo -> addRow(taskInfo));
        }else if(key == TaskState.TASK_UPDATE){
            tempIndex = 0;
            TaskMapper mapper = TaskMapper.InnerClass.instance;
            mapper.getUpdateTaskInfo().forEach(taskInfo -> {
                updateRow(tempIndex, taskInfo);
                mapper.updateRowIndexMapper(TaskMapper.DOWNLOADING_MASK ,tempIndex, taskInfo.getTaskID());
                tempIndex++;
            });
            
        }else if(key == TaskState.TASK_FINISHED || key == TaskState.TASK_DELETED){
            TaskMapper mapper = TaskMapper.InnerClass.instance;
            mapper.getMapStream((K, V) -> {
                boolean contains = Arrays.binarySearch(event.getTaskIDs(), V) >= 0;
                int index = K ^ TaskMapper.DOWNLOADING_MASK; 
                if(contains) removeRow(index);
            });
        }
            
             
        //handle....
        
//        setValueAt(aValue, row, column);
        
    }
    

    protected abstract boolean match(Class<?> clazz);
    protected abstract void updateRow(int row, TaskInfo taskInfo);
    protected abstract void addRow(TaskInfo taskInfo);
    
    @Override
    public boolean filter(InfoEvent event) {
        return TaskTableModel.class.isAssignableFrom(event.getTarget());
    }

     
 
}
