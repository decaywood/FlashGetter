package flashGetter.view.sidebar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.log4j.Logger;
import org.pushingpixels.substance.api.SubstanceConstants.ImageWatermarkKind;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.skin.RavenSkin;
import org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel;
import org.pushingpixels.substance.api.watermark.SubstanceImageWatermark;
import org.pushingpixels.substance.api.watermark.SubstanceWatermark;

import flashGetter.view.FlashGetterRavenSkin;
import flashGetter.view.sidebar.SideBarPlatterView.SidebarPlatter;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class SideBarView extends JPanel implements SidebarPlatter {
    
    
    public SideBarView() {
        
        JPanel downloadOptionPanel = new DownloadOptionPanelView("Downloading", "Downloaded", "Deleted");
        downloadOptionPanel.setPreferredSize(new Dimension(100, 400));
        
        JPanel allocatePanel = new JPanel(new BorderLayout());
        allocatePanel.add(new UserInfoPanelView(), BorderLayout.NORTH);
        allocatePanel.add(downloadOptionPanel, BorderLayout.SOUTH);
        
        setLayout(new BorderLayout());
        
        add(allocatePanel, BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.CENTER);
        
    }

}
