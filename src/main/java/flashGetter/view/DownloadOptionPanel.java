package flashGetter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;














import org.apache.log4j.Logger;

import flashGetter.util.ImageUtil;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class DownloadOptionPanel extends EmptyPanel{
    
    private static Logger logger = Logger.getLogger(DownloadOptionPanel.class);
    
    private static class OptionPanel extends EmptyPanel{
        
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
        
        JPanel downloadingPanel = new OptionPanel(ImageUtil.readIcon("/images/downloading.png", 50),
                                                  ImageUtil.readIcon("/images/downloading(white).png", 50), "Downloading");
        JPanel downloadedPanel = new OptionPanel(ImageUtil.readIcon("/images/downloaded.png", 50),
                                                 ImageUtil.readIcon("/images/downloaded(white).png", 50), "Downloaded");
        JPanel deletedPanel = new OptionPanel(ImageUtil.readIcon("/images/deleted.png", 50), 
                                              ImageUtil.readIcon("/images/deleted(white).png", 50), "Deleted");
        
        setLayout(new GridLayout(6, 1));
        
        
        add(new EmptyPanel());
        add(downloadingPanel);
        add(downloadedPanel);
        add(deletedPanel);
        add(new EmptyPanel());
        add(new EmptyPanel());
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.add(new DownloadOptionPanel());
        
    }

}
