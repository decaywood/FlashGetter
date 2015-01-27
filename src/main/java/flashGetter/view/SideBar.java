package flashGetter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.pushingpixels.substance.api.SubstanceConstants.ImageWatermarkKind;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.skin.OfficeBlue2007Skin;
import org.pushingpixels.substance.api.watermark.SubstanceImageWatermark;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class SideBar extends EmptyPanel{
    
    private static Logger logger = Logger.getLogger(SideBar.class);
    
    private JPanel userInfoPanel;
    
    private JPanel downloadOptionPanel;
    
    public SideBar() {
        
        
        setLayout(new BorderLayout());
        userInfoPanel = new UserInfoPanel();
        add(userInfoPanel, BorderLayout.NORTH);
        
        add(new EmptyPanel(), BorderLayout.CENTER);
        
        downloadOptionPanel = new DownloadOptionPanel();
        downloadOptionPanel.setPreferredSize(new Dimension(100, 400));
        add(downloadOptionPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.add(new SideBar());
    }
}
