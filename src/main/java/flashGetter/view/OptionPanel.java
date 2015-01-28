package flashGetter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.IconView;

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
    protected ViewEvent event;
    
    private MouseAdapter adapter = new MouseAdapter() {
        
        @Override
        public void mouseEntered(MouseEvent e) {
            OptionPanel.this.mouseEntered();
        };
        
        @Override
        public void mouseExited(MouseEvent e) {
            OptionPanel.this.mouseExited();
        };
        
        @Override
        public void mousePressed(MouseEvent e) {
            OptionPanel.this.mousePressed();
        };
        
    };
    
    
    protected void mouseEntered(){
        lightComponent();
    }
    
    protected void mouseExited(){
        darkComponent();
    }
    
    protected void mousePressed(){
        if(event != null)
            ViewEventDispatcher.InnerClass.instance.fireEvent(event);
    }
    
    protected void lightComponent(){
        iconLabel.setIcon(icon2);
        optionLabel.setForeground(Color.YELLOW);
    }
    
    protected void darkComponent(){
        iconLabel.setIcon(icon1);
        optionLabel.setForeground(Color.WHITE);
    }
    
    public OptionPanel(ImageIcon icon1, ImageIcon icon2, String option, ViewEvent event){
        this(icon1, icon2, option, null, event);
    }
    
    
    public OptionPanel(ImageIcon icon1, ImageIcon icon2, String option, String tip){
        this(icon1, icon2, option, tip, null);
    }
    
    public OptionPanel(
            ImageIcon icon1, 
            ImageIcon icon2,
            String option, 
            String tip,
            ViewEvent event){
        
        this.event = event;
        
        this.icon1 = icon1;
        this.icon2 = icon2;
        
        iconLabel = new JLabel(icon1);
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
