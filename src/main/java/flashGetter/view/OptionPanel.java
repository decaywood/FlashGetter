package flashGetter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import flashGetter.Resources;

/**
 * @author decaywood
 * 
 * 2015年1月27日
 * 
 */
public class OptionPanel extends JPanel{
    
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
        this(icon1, icon2, option, null);
    }
    
    public OptionPanel(ImageIcon icon1, ImageIcon icon2, String option, String tip){
        
        this.icon1 = icon1;
        this.icon2 = icon2;
        
        iconLabel = new JLabel(icon1);
        iconLabel.setOpaque(true);
        optionLabel = new JLabel(option);
        optionLabel.setFont(Resources.getFont());
        iconLabel.addMouseListener(adapter);
        optionLabel.addMouseListener(adapter);
        addMouseListener(adapter);
        
        optionLabel.setForeground(Color.WHITE);
        
        setLayout(new BorderLayout());
      
        add(iconLabel, BorderLayout.WEST);
        add(optionLabel, BorderLayout.EAST);
        
        if(tip != null){
            iconLabel.setToolTipText(tip);
            optionLabel.setToolTipText(tip);
        }
        
    }
    
}
