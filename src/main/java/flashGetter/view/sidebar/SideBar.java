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

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class SideBar extends JPanel{
    
    private static Logger logger = Logger.getLogger(SideBar.class);
    
    private JPanel userInfoPanel;
    
    private JPanel downloadOptionPanel;
    
    public SideBar() {
        
        
        setLayout(new BorderLayout());
        userInfoPanel = new UserInfoPanel();
        add(userInfoPanel, BorderLayout.NORTH);
        
        add(new JPanel(), BorderLayout.CENTER);
        
        downloadOptionPanel = new DownloadOptionPanel();
        downloadOptionPanel.setPreferredSize(new Dimension(100, 400));
        add(downloadOptionPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                try {
//                    JFrame.setDefaultLookAndFeelDecorated(true); //加上此语句连同窗体外框也改变  
//                    JDialog.setDefaultLookAndFeelDecorated(true); //加上此语句会使弹出的对话框也改变  
                    SubstanceSkin skin = new FlashGetterRavenSkin(0.8f, ImageWatermarkKind.APP_CENTER);
                    UIManager.setLookAndFeel(new SubstanceRavenLookAndFeel());
                    SubstanceLookAndFeel.setSkin(skin);
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                } 
                JFrame jFrame = new JFrame();
                jFrame.setVisible(true);
                jFrame.add(new SideBar());
            }  
        });  
      
       
       
       
    }
}
