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
    protected boolean match(Class<?> clazz) {
        return false;
    }

    @Override
    protected void updateRow(int row, TaskInfo taskInfo) {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void addRow(TaskInfo taskInfo) {
        // TODO Auto-generated method stub
        
    }

  

}
