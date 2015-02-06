package flashGetter.view.tasktable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
    
 
}
