package flashGetter.view.tasktable;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import model.DownloadingTableModel;
import flashGetter.presenter.DownloadingTablePresenter;



/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DownloadingTableView extends TaskTableView<DownloadingTableModel> 
    implements DownloadingTablePresenter.DownloadingTablePresenterView {
    
           
    public DownloadingTableView() {
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
    public void addRow(ImageIcon icon, String fileName, String fileSize,
            int progress, String time, String speed) {
         
    }


}
