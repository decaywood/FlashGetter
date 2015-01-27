package flashGetter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class DownloadOptionPanel extends JPanel{
    
    private static Logger logger = Logger.getLogger(DownloadOptionPanel.class);
    
    
    public DownloadOptionPanel() {
        
        JPanel downloadingPanel = new OptionPanel(ImageUtil.readIcon(Resources.downloading, 50),
                                                  ImageUtil.readIcon(Resources.downloadingChoosed, 50), "Downloading");
        JPanel downloadedPanel = new OptionPanel(ImageUtil.readIcon(Resources.downloaded, 50),
                                                 ImageUtil.readIcon(Resources.downloadedChoosed, 50), "Downloaded");
        JPanel deletedPanel = new OptionPanel(ImageUtil.readIcon(Resources.recycleStation, 50), 
                                              ImageUtil.readIcon(Resources.recycleStationChoosed, 50), "Deleted");
        
        setLayout(new GridLayout(6, 1));
        
        
        add(new JPanel());
        add(downloadingPanel);
        add(downloadedPanel);
        add(deletedPanel);
        add(new JPanel());
        add(new JPanel());
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.add(new DownloadOptionPanel());
        
    }

}
