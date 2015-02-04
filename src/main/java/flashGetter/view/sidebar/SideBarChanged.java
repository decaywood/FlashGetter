package flashGetter.view.sidebar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;
import flashGetter.view.sidebar.SideBarPlatter.SidebarPlatter;

/**
 * @author decaywood
 * 
 * 2015年1月31日
 * 
 */
public class SideBarChanged extends JPanel implements SidebarPlatter {
    
    public SideBarChanged() {
        
        JPanel userPhotoPanel = new JPanel(new BorderLayout());
        userPhotoPanel.add(new JLabel(ImageUtil.readIcon(Resources.defaultUser, 50)), BorderLayout.CENTER);
        
        JPanel downloadOptionPanel = new DownloadOptionPanel();
        downloadOptionPanel.setPreferredSize(new Dimension(100, 400));
        
        JPanel allocatePanel = new JPanel(new BorderLayout());
        allocatePanel.add(userPhotoPanel, BorderLayout.NORTH);
        allocatePanel.add(downloadOptionPanel, BorderLayout.SOUTH);
        
        setLayout(new BorderLayout());
        
        add(allocatePanel, BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.CENTER);
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.add(new SideBarChanged());
    }

}
