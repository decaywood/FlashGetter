package flashGetter.view;

import java.awt.BorderLayout;
import java.awt.Color;
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
    
    private static class OptionPanel extends JPanel{
        
        private JLabel iconLabel;
        private JLabel optionLabel;
        private Icon icon1;
        private Icon icon2;
        
        private MouseAdapter adapter = new MouseAdapter() {
            
            @Override
            public void mouseEntered(MouseEvent e) {
                OptionPanel.this.lightComponent();
            };
            
            @Override
            public void mouseExited(MouseEvent e) {
                OptionPanel.this.darkComponent();
            };
            
            @Override
            public void mousePressed(MouseEvent e) {
                
            };
            
        };
        
        private void lightComponent(){
            iconLabel.setIcon(icon2);
            optionLabel.setForeground(Color.YELLOW);
        }
        
        private void darkComponent(){
            iconLabel.setIcon(icon1);
            optionLabel.setForeground(Color.WHITE);
        }
        
        public OptionPanel(ImageIcon icon1, ImageIcon icon2, String option){
            
            this.icon1 = icon1;
            this.icon2 = icon2;
            
            iconLabel = new JLabel(icon1);
            iconLabel.setOpaque(true);
            optionLabel = new JLabel(option);
            iconLabel.addMouseListener(adapter);
            optionLabel.addMouseListener(adapter);
            addMouseListener(adapter);
            
            optionLabel.setForeground(Color.WHITE);
            
            setLayout(new BorderLayout());
          
            add(iconLabel, BorderLayout.WEST);
            add(optionLabel, BorderLayout.EAST);
            
        }
        
    }
    
    public DownloadOptionPanel() {
        
        JPanel downloadingPanel = new OptionPanel(ImageUtil.readIcon(Resources.downloading, 50),
                                                  ImageUtil.readIcon(Resources.downloadingChoosed, 50), "Downloading");
        JPanel downloadedPanel = new OptionPanel(ImageUtil.readIcon(Resources.downloaded, 50),
                                                 ImageUtil.readIcon(Resources.downloadedChoosed, 50), "Downloaded");
        JPanel deletedPanel = new OptionPanel(ImageUtil.readIcon(Resources.deleted, 50), 
                                              ImageUtil.readIcon(Resources.deletedChoosed, 50), "Deleted");
        
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
