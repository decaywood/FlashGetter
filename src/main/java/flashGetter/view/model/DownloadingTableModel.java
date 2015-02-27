package flashGetter.view.model;

import java.util.Arrays;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import flashGetter.downloader.TaskMapper;
import flashGetter.downloader.task.Task.TaskState;
import flashGetter.downloader.task.TaskInfo;
import flashGetter.util.ParameterUnitUtil;
import flashGetter.view.InfoEvent;

/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public class DownloadingTableModel extends TaskTableModel {
    
    private static final Logger LOGGER = Logger.getLogger(DownloadingTableModel.class);

    public DownloadingTableModel() {
        super(6);
    }

 

    private synchronized void addRow(TaskInfo taskInfo, int index) {
        
        TaskMapper.InnerClass.instance
        .updateRowIndexMapper(TaskMapper.DOWNLOADING_MASK, index, taskInfo.getTaskID());
        
        
        ImageIcon fileType = taskInfo.getFileType();
        String fileName = taskInfo.getFileName();
        String fileSize = ParameterUnitUtil.getFileSize(taskInfo.getFileSize());
        double progress = taskInfo.getProgress();
        String remainTime = taskInfo.getRemainTime();
        String speed = ParameterUnitUtil.getDownloadSpeed(taskInfo.getDownloadSpeed());
        
//        LOGGER.info("fileType : "+fileType+" fileName : "+fileName+" fileSize : "+fileSize
//                +" prog : " +progress+" speed : "+speed);
        
        if(index == getRowCount()) addRow(new Object[]{fileType, fileName, fileSize, progress, remainTime, speed});
        else {
            setValueAt(fileType, index, 0);
            setValueAt(fileName, index, 1);
            setValueAt(fileSize, index, 2);
            setValueAt(progress, index, 3);
            setValueAt(remainTime, index, 4);
            setValueAt(speed, index, 5);
        }
        
    }

  
    
    
    @Override
    protected boolean match(Class<?> clazz) {
        return DownloadingTableModel.class == clazz;
    }

    private int tempIndex = 0;
    @Override
    protected void execute(InfoEvent event) {
        
        TaskState key = (TaskState) event.getOperationKey();
        
        if(key == TaskState.TASK_BEGIN){
            TaskMapper.InnerClass.instance.getStateFiltedTaskInfo(TaskState.TASK_BEGIN)
            .forEach(taskInfo -> addRow(taskInfo, getRowCount()));
        }else if(key == TaskState.TASK_UPDATE){
            tempIndex = 0;
            TaskMapper mapper = TaskMapper.InnerClass.instance;
            mapper.getStateFiltedTaskInfo(TaskState.TASK_UPDATE).forEach(taskInfo -> {
                addRow(taskInfo, tempIndex);
                mapper.updateRowIndexMapper(TaskMapper.DOWNLOADING_MASK ,tempIndex, taskInfo.getTaskID());
                tempIndex++;
            });
            
        }else if(key == TaskState.TASK_FINISHED || key == TaskState.TASK_DELETED){
            
            TaskMapper mapper = TaskMapper.InnerClass.instance;
            mapper.getMapStream((K, V) -> {
                boolean contains = Arrays.binarySearch(event.getTaskIDs(), V) >= 0;
                if(contains){
                    int index = K ^ TaskMapper.DOWNLOADING_MASK; 
                    mapper.dropRowIndexMapper(K);
                    removeRow(index);
                }
            });
        }
    }
    
 

}
