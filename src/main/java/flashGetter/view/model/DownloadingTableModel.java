package flashGetter.view.model;

import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import flashGetter.downloader.TaskMapper;
import flashGetter.downloader.task.TaskInfo;
import flashGetter.util.ParameterUnitUtil;

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

    @Override
    boolean match(Class<?> clazz) {
        return DownloadingTableModel.class == clazz;
    }

    @Override
    synchronized void updateRow(int row, TaskInfo taskInfo) {
        
        if(getRowCount() <= row) return;
        
        ImageIcon fileType = taskInfo.getFileType();
        String fileName = taskInfo.getFileName();
        String fileSize = ParameterUnitUtil.getFileSize(taskInfo.getFileSize());
        double progress = taskInfo.getProgress();
        String remainTime = taskInfo.getRemainTime();
        String speed = ParameterUnitUtil.getDownloadSpeed(taskInfo.getDownloadSpeed());
        
        LOGGER.info("fileType : "+fileType+" fileName : "+fileName+" fileSize : "+fileSize
                +" prog : " +progress+" speed : "+speed);
        
        setValueAt(fileType, row, 0);
        setValueAt(fileName, row, 1);
        setValueAt(fileSize, row, 2);
        setValueAt(progress, row, 3);
        setValueAt(remainTime, row, 4);
        setValueAt(speed, row, 5);
        
    }

    @Override
    synchronized void addRow(TaskInfo taskInfo) {
        
        int rowIndex = getRowCount();
        
        TaskMapper.InnerClass.instance
        .updateRowIndexMapper(TaskMapper.DOWNLOADING_MASK, rowIndex, taskInfo.getTaskID());
        
        ImageIcon fileType = taskInfo.getFileType();
        String fileName = taskInfo.getFileName();
        String fileSize = ParameterUnitUtil.getFileSize(taskInfo.getFileSize());
        double progress = taskInfo.getProgress();
        String remainTime = taskInfo.getRemainTime();
        String speed = ParameterUnitUtil.getDownloadSpeed(taskInfo.getDownloadSpeed());
        addRow(new Object[]{fileType, fileName, fileSize, progress, remainTime, speed});
    }

}
