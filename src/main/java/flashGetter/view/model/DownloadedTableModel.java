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

//    protected synchronized void updateRow(int row, TaskInfo taskInfo) {
//        
//        if(getRowCount() <= row) return;
//        
//        ImageIcon fileType = taskInfo.getFileType();
//        String fileName = taskInfo.getFileName();
//        String fileSize = ParameterUnitUtil.getFileSize(taskInfo.getFileSize());
//        String finishTime = taskInfo.getFinishTime();
//        
//        setValueAt(fileType, row, 0);
//        setValueAt(fileName, row, 1);
//        setValueAt(fileSize, row, 2);
//        setValueAt(finishTime, row, 3);
//        
//    }

    private synchronized void addRow(TaskInfo taskInfo) {
        
        int rowIndex = getRowCount();
        
        TaskMapper.InnerClass.instance
        .updateRowIndexMapper(TaskMapper.DOWNLOADED_MASK, rowIndex, taskInfo.getTaskID());
        
        ImageIcon fileType = taskInfo.getFileType();
        String fileName = taskInfo.getFileName();
        String fileSize = ParameterUnitUtil.getFileSize(taskInfo.getFileSize());
        String finishTime = taskInfo.getFinishTime();
        addRow(new Object[]{fileType, fileName, fileSize, finishTime});
    }

    @Override
    protected void execute(InfoEvent event) {
        
        TaskState key = (TaskState) event.getOperationKey();
        
        if(key == TaskState.TASK_FINISHED){
            TaskMapper.InnerClass.instance.getStateFiltedTaskInfo(TaskState.TASK_FINISHED)
            .forEach(taskInfo -> addRow(taskInfo));
        }
        
        else if(key == TaskState.TASK_DELETED){
            
        }
        
    }

  

}
