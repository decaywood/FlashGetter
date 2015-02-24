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
public class DeletedTableModel extends TaskTableModel {

    public DeletedTableModel() {
        super(4);
    }

    @Override
    protected boolean match(Class<?> clazz) {
        return DeletedTableModel.class == clazz;
    }

    

    private void addRow(TaskInfo taskInfo, int index) {
        
        TaskMapper.InnerClass.instance
        .updateRowIndexMapper(TaskMapper.DELETED_MASK, index, taskInfo.getTaskID());
        
        ImageIcon fileType = taskInfo.getFileType();
        String fileName = taskInfo.getFileName();
        String fileSize = ParameterUnitUtil.getFileSize(taskInfo.getFileSize());
        String createTime = taskInfo.getCreateTime();
        
        if(index == getRowCount()) addRow(new Object[]{fileType, fileName, fileSize, createTime});
        else {
            setValueAt(fileType, index, 0);
            setValueAt(fileName, index, 1);
            setValueAt(fileSize, index, 2);
            setValueAt(createTime, index, 3);
        }
        
        
    }

    private int index;
    @Override
    protected void execute(InfoEvent event) {
        
        TaskState key = (TaskState) event.getOperationKey();
        
        if(key == TaskState.TASK_DELETED){
            index = 0;
            TaskMapper mapper = TaskMapper.InnerClass.instance;
            mapper.getStateFiltedTaskInfo(TaskState.TASK_DELETED)
            .forEach(taskInfo -> {
                addRow(taskInfo, index);
                mapper.updateRowIndexMapper(TaskMapper.DELETED_MASK, index, taskInfo.getTaskID());
                index++;
            });
        }
        
        else if(key == TaskState.TASK_REMOVE){
            TaskMapper mapper = TaskMapper.InnerClass.instance;
            mapper.getMapStream((K, V) -> {
                boolean contains = Arrays.binarySearch(event.getTaskIDs(), V) >= 0;
                if(contains){
                    int index = K ^ TaskMapper.DELETED_MASK; 
                    mapper.dropRowIndexMapper(K);
                    removeRow(index);
                    mapper.dropTask(V);
                }
            });
        }
 
    }

    

}
