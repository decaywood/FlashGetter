package flashGetter.view.model;

import flashGetter.downloader.task.TaskInfo;

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
    boolean match(Class<?> clazz) {
        return false;
    }

    @Override
    void updateRow(int row, TaskInfo taskInfo) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void addRow(TaskInfo taskInfo) {
        // TODO Auto-generated method stub
        
    }

  

}
