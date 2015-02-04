package flashGetter.view.sidebar;

import java.awt.GridLayout;

import javax.swing.JPanel;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;
import flashGetter.view.InfoEvent;
import flashGetter.view.controlbar.DeletedControlBar;
import flashGetter.view.controlbar.DownloadedControlBar;
import flashGetter.view.controlbar.DownloadingControlBar;
import flashGetter.view.tasktable.DeletedTable;
import flashGetter.view.tasktable.DownloadedTable;
import flashGetter.view.tasktable.DownloadingTable;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class DownloadOptionPanel extends JPanel{
    
    
    public DownloadOptionPanel() {
        
        setLayout(new GridLayout(6, 1));
        init(null, null, null);
        
    }
    
    public DownloadOptionPanel(String tag1, String tag2, String tag3) {
       
        setLayout(new GridLayout(6, 1));
        init(tag1, tag2, tag3);
        
    }
    
    public void init(String tag1, String tag2, String tag3){
        JPanel downloadingPanel = new SideBarOptionPanel(SideBarOptionPanel.DOWNLOADING,
                ImageUtil.readIcon(Resources.downloading, 50),
                ImageUtil.readIcon(Resources.downloadingChoosed, 50), 
                tag1,
                true,
                new InfoEvent().setTarget(DownloadingControlBar.class),
                new InfoEvent().setTarget(DownloadingTable.class));


        JPanel downloadedPanel = new SideBarOptionPanel(SideBarOptionPanel.DOWNLOADED,
                ImageUtil.readIcon(Resources.downloaded, 50),
                       ImageUtil.readIcon(Resources.downloadedChoosed, 50), 
                       tag2,
                       new InfoEvent().setTarget(DownloadedControlBar.class),
                       new InfoEvent().setTarget(DownloadedTable.class));
        
        JPanel deletedPanel = new SideBarOptionPanel(SideBarOptionPanel.DELETED,
                ImageUtil.readIcon(Resources.recycleStation, 50), 
                    ImageUtil.readIcon(Resources.recycleStationChoosed, 50), 
                    tag3,
                    new InfoEvent().setTarget(DeletedControlBar.class),
                    new InfoEvent().setTarget(DeletedTable.class));
        
        add(new JPanel());
        add(downloadingPanel);
        add(downloadedPanel);
        add(deletedPanel);
        add(new JPanel());
        add(new JPanel());

    }
    
    

}
