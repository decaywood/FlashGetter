package flashGetter.view.tasktable;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;

import flashGetter.downloader.DownloadManager;
import flashGetter.downloader.TaskMapper;
import flashGetter.view.EventDispatcher;
import flashGetter.view.InfoEvent;
import flashGetter.view.model.DeletedTableModel;

/**
 * @author decaywood
 * 
 * 2015年1月30日
 * 
 */
public class DeletedTable extends TaskTable<DeletedTableModel> {
    
   

    @Override
    protected void initParameter() {
        // icon / string / string / bar / time / string
        addBundle(typeLabel, fileIconRenderer);
        addBundle(nameLabel, nameCellRenderer);
        addBundle(sizeLabel, sizeCellRenderer);
        addBundle(finishTimeLabel, timeCellRenderer);
        
    }
    
    
    public DeletedTable() {
        
        super(new DeletedTableModel());
        
    }
    
    @Override
    public void invoke(InfoEvent event) {
        TaskMapper mapper = TaskMapper.InnerClass.instance;
        int[] selectedRows = getSelectedRows();
        long[] taskIDs = Arrays.stream(selectedRows)
        .mapToLong(index -> mapper.getTaskID(TaskMapper.DELETED_MASK, index))
        .toArray();
        InfoEvent fireEvent = event.newCopy()
                .setTaskID(taskIDs)
                .setTarget(DownloadManager.class);
        EventDispatcher.InnerClass.instance.fireEvent(fireEvent);
    }
    
    @Override
    public boolean filter(InfoEvent event) {
        return DeletedTable.class.isAssignableFrom(event.getTarget());
    }
    
    

}
