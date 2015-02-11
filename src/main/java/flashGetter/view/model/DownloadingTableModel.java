package flashGetter.view.model;

import javax.swing.ImageIcon;

import flashGetter.downloader.TaskMapper;
import flashGetter.downloader.task.TaskInfo;

/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public class DownloadingTableModel extends TaskTableModel {

    public DownloadingTableModel() {
        super(6);
    }

    @Override
    boolean match(Class<?> clazz) {
        return DownloadingTableModel.class == clazz;
    }

    @Override
    void updateRow(int row, Long taskID) {
        
        TaskInfo taskInfo = TaskMapper.InnerClass.instance.getTaskInfo(taskID);
        ImageIcon fileType = taskInfo.getFileType();
        String fileName = taskInfo.getFileName();
        long fileSize = taskInfo.getFileSize();
        double progress = taskInfo.getProgress();
        String remianTime = taskInfo.getRemainTime();
        String speed = taskInfo.getDownloadSpeed();
        
        setValueAt(fileType, row, 0);
        setValueAt(fileName, row, 1);
        setValueAt(fileSize, row, 2);
        setValueAt(progress, row, 3);
        setValueAt(remianTime, row, 4);
        setValueAt(speed, row, 5);
        
    }

}
