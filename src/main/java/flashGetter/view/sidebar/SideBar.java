package flashGetter.view.sidebar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import flashGetter.view.sidebar.SideBarPlatter.SidebarPlatter;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class SideBar extends JPanel implements SidebarPlatter {
    
    
    public SideBar() {
        
        JPanel downloadOptionPanel = new DownloadOptionPanel("Downloading", "Downloaded", "Deleted");
        downloadOptionPanel.setPreferredSize(new Dimension(100, 400));
        
        JPanel allocatePanel = new JPanel(new BorderLayout());
        allocatePanel.add(new UserInfoPanel(), BorderLayout.NORTH);
        allocatePanel.add(downloadOptionPanel, BorderLayout.SOUTH);
        
        setLayout(new BorderLayout());
        
        add(allocatePanel, BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.CENTER);
        
    }

}
