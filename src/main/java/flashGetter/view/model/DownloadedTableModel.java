package flashGetter.view.model;

import java.util.Arrays;

import javax.swing.ImageIcon;

import flashGetter.downloader.TaskMapper;
import flashGetter.downloader.task.TaskInfo;
import flashGetter.downloader.task.Task.TaskState;
import flashGetter.util.ParameterUnitUtil;
import flashGetter.view.InfoEvent;

/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public class DownloadedTableModel extends TaskTableModel {

    public DownloadedTableModel() {
        super(4);
    }

    @Override
    protected boolean match(Class<?> clazz) {
        return DownloadedTableModel.class == clazz;
    }

 

    private synchronized void addRow(TaskInfo taskInfo, int index) {
        
        TaskMapper.InnerClass.instance
        .updateRowIndexMapper(TaskMapper.DOWNLOADED_MASK, index, taskInfo.getTaskID());
        
        ImageIcon fileType = taskInfo.getFileType();
        String fileName = taskInfo.getFileName();
        String fileSize = ParameterUnitUtil.getFileSize(taskInfo.getFileSize());
        String finishTime = taskInfo.getFinishTime();
        
        if(index == getRowCount()) addRow(new Object[]{fileType, fileName, fileSize, finishTime});
        else {
            setValueAt(fileType, index, 0);
            setValueAt(fileName, index, 1);
            setValueAt(fileSize, index, 2);
            setValueAt(finishTime, index, 3);
        }
        
    }
    
    private int index;
    
    

    @Override
    protected void execute(InfoEvent event) {
        
        TaskState key = (TaskState) event.getOperationKey();
        
        if(key == TaskState.TASK_FINISHED){
            index = 0;
            TaskMapper mapper = TaskMapper.InnerClass.instance;
            mapper.getStateFiltedTaskInfo(TaskState.TASK_FINISHED)
            .forEach(taskInfo -> {
                addRow(taskInfo, index);
                mapper.updateRowIndexMapper(TaskMapper.DOWNLOADED_MASK, index, taskInfo.getTaskID());
                index++;
            });
        }
        
        else if(key == TaskState.TASK_DELETED){
            TaskMapper mapper = TaskMapper.InnerClass.instance;
            mapper.getMapStream((K, V) -> {
                boolean contains = Arrays.binarySearch(event.getTaskIDs(), V) >= 0;
                if(contains){
                    int index = K ^ TaskMapper.DOWNLOADED_MASK; 
                    mapper.dropRowIndexMapper(K);
                    removeRow(index);
                }
            });
        }
        
    }

  

}
