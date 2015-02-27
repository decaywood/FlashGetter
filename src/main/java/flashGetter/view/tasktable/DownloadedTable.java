package flashGetter.view.tasktable;

import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JLabel;

import flashGetter.downloader.DownloadManager;
import flashGetter.downloader.TaskMapper;
import flashGetter.view.EventDispatcher;
import flashGetter.view.InfoEvent;
import flashGetter.view.model.DownloadedTableModel;
import flashGetter.view.model.TaskTableModel;

/**
 * @author decaywood
 * 
 * 2015年1月30日
 * 
 * 
 */
public class DownloadedTable extends TaskTable<DownloadedTableModel>{
    

    @Override
    protected void initParameter() {
        // icon / string / string / bar / time / string
        addBundle(typeLabel, fileIconRenderer);
        addBundle(nameLabel, nameCellRenderer);
        addBundle(sizeLabel, sizeCellRenderer);
        addBundle(finishTimeLabel, timeCellRenderer);
        
    }
    
    
    public DownloadedTable() {
        
        super(new DownloadedTableModel());
        
    }
    
    @Override
    public void invoke(InfoEvent event) {
        
        TaskMapper mapper = TaskMapper.InnerClass.instance;
        int[] selectedRows = getSelectedRows();
        long[] taskIDs = Arrays.stream(selectedRows)
        .mapToLong(index -> mapper.getTaskID(TaskMapper.DOWNLOADED_MASK, index))
        .toArray();
        InfoEvent fireEvent = event.newCopy()
        .setTaskID(taskIDs)
        .setTarget(DownloadManager.class);
        EventDispatcher.InnerClass.instance.fireEvent(fireEvent);
        
    }
    
    @Override
    public boolean filter(InfoEvent event) {
        return DownloadedTable.class.isAssignableFrom(event.getTarget());
    }
  

}
