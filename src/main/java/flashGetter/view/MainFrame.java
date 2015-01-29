package flashGetter.view;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.SubstanceConstants.ImageWatermarkKind;
import org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel;

import flashGetter.view.controlbar.ControlBarPlatter;
import flashGetter.view.sidebar.SideBar;
import flashGetter.view.tasktable.TaskTablePlatter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class MainFrame extends JFrame{
    
    public MainFrame() {
        getContentPane().setLayout(new BorderLayout());
        
        add(new SideBar(), BorderLayout.WEST);
        
        JPanel rightSidePanel = new JPanel();
        
        rightSidePanel.setLayout(new BorderLayout());
        rightSidePanel.add(new ControlBarPlatter(), BorderLayout.NORTH);
//        rightSidePanel.add(new TaskTablePlatter(), BorderLayout.CENTER);
        rightSidePanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        add(rightSidePanel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
                JFrame jFrame = new MainFrame();
                jFrame.setVisible(true);
            }  
        });  
      
       
       
       
    }

}
