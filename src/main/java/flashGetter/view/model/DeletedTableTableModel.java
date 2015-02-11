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
    boolean match(Class<?> clazz) {
        return DeletedTableTableModel.class == clazz;
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
