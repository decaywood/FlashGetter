package flashGetter.view.tasktable;

import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import flashGetter.downloader.DownloadManager;
import flashGetter.downloader.TaskMapper;
import flashGetter.view.EventDispatcher;
import flashGetter.view.InfoEvent;
import flashGetter.view.model.DownloadingTableModel;



/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DownloadingTable extends TaskTable<DownloadingTableModel> {
    
           
    public DownloadingTable() {
        super(new DownloadingTableModel());
    }

    @Override
    protected void initParameter() {
        // icon / string / string / bar / time / string
        addBundle(typeLabel, fileIconRenderer);
        addBundle(nameLabel, nameCellRenderer);
        addBundle(sizeLabel, sizeCellRenderer);
        addBundle(progressLabel, progressBarCellRender);
        addBundle(remainTimeLabel, timeCellRenderer);
        addBundle(speedLabel, speedCellRenderer);
        
    }
    
   
    
    @Override
    public void invoke(InfoEvent event) {
        TaskMapper mapper = TaskMapper.InnerClass.instance;
        int[] selectedRows = getSelectedRows();
         
        long[] taskIDs = Arrays.stream(selectedRows)
        .mapToLong(index -> mapper.getTaskID(TaskMapper.DOWNLOADING_MASK, index))
        .toArray();
        InfoEvent fireEvent = event.newCopy()
        .setTaskID(taskIDs)
        .setTarget(DownloadManager.class);
        EventDispatcher.InnerClass.instance.fireEvent(fireEvent);
    }
    
    
    @Override
    public boolean filter(InfoEvent event) {
        return DownloadingTable.class.isAssignableFrom(event.getTarget());
    }
 
}
