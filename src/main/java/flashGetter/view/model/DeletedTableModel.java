package flashGetter.view.model;

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

    

    private void addRow(TaskInfo taskInfo) {
        int rowIndex = getRowCount();
        
        TaskMapper.InnerClass.instance
        .updateRowIndexMapper(TaskMapper.DELETED_MASK, rowIndex, taskInfo.getTaskID());
        
        ImageIcon fileType = taskInfo.getFileType();
        String fileName = taskInfo.getFileName();
        String fileSize = ParameterUnitUtil.getFileSize(taskInfo.getFileSize());
        String createTime = taskInfo.getCreateTime();
        addRow(new Object[]{fileType, fileName, fileSize, createTime});
    }

    @Override
    protected void execute(InfoEvent event) {
        
        TaskState key = (TaskState) event.getOperationKey();
        
        if(key == TaskState.TASK_DELETED){
            TaskMapper.InnerClass.instance.getStateFiltedTaskInfo(TaskState.TASK_DELETED)
            .forEach(taskInfo -> addRow(taskInfo));
        }
 
    }

    

}
