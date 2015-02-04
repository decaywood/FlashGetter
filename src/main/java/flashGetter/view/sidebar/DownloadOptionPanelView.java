package flashGetter.view.sidebar;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;
import flashGetter.view.OptionPanel;
import flashGetter.view.ViewEvent;
import flashGetter.view.controlbar.DeletedControlBarView;
import flashGetter.view.controlbar.DownloadedControlBarView;
import flashGetter.view.controlbar.DownloadingControlBarView;
import flashGetter.view.tasktable.DeletedTableView;
import flashGetter.view.tasktable.DownloadedTableView;
import flashGetter.view.tasktable.DownloadingTableView;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class DownloadOptionPanelView extends JPanel{
    
    private static Logger logger = Logger.getLogger(DownloadOptionPanelView.class);
    
    
    public DownloadOptionPanelView() {
        
        setLayout(new GridLayout(6, 1));
        init(null, null, null);
        
    }
    
    public DownloadOptionPanelView(String tag1, String tag2, String tag3) {
       
        setLayout(new GridLayout(6, 1));
        init(tag1, tag2, tag3);
        
    }
    
    public void init(String tag1, String tag2, String tag3){
        JPanel downloadingPanel = new SideBarOptionPanelView(SideBarOptionPanelView.DOWNLOADING,
                ImageUtil.readIcon(Resources.downloading, 50),
                ImageUtil.readIcon(Resources.downloadingChoosed, 50), 
                tag1,
                true,
                new ViewEvent().setTarget(DownloadingControlBarView.class),
                new ViewEvent().setTarget(DownloadingTableView.class));


        JPanel downloadedPanel = new SideBarOptionPanelView(SideBarOptionPanelView.DOWNLOADED,
                ImageUtil.readIcon(Resources.downloaded, 50),
                       ImageUtil.readIcon(Resources.downloadedChoosed, 50), 
                       tag2,
                       new ViewEvent().setTarget(DownloadedControlBarView.class),
                       new ViewEvent().setTarget(DownloadedTableView.class));
        
        JPanel deletedPanel = new SideBarOptionPanelView(SideBarOptionPanelView.DELETED,
                ImageUtil.readIcon(Resources.recycleStation, 50), 
                    ImageUtil.readIcon(Resources.recycleStationChoosed, 50), 
                    tag3,
                    new ViewEvent().setTarget(DeletedControlBarView.class),
                    new ViewEvent().setTarget(DeletedTableView.class));
        
        add(new JPanel());
        add(downloadingPanel);
        add(downloadedPanel);
        add(deletedPanel);
        add(new JPanel());
        add(new JPanel());

    }
    
    

}
