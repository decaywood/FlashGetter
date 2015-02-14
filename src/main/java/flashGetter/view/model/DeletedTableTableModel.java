package flashGetter.view.model;

import flashGetter.downloader.task.TaskInfo;

/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public class DeletedTableTableModel extends TaskTableModel {

    public DeletedTableTableModel() {
        super(4);
    }

    @Override
    protected boolean match(Class<?> clazz) {
        return DeletedTableTableModel.class == clazz;
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
