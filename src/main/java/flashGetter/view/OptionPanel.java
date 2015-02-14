package flashGetter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Vector;

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
    
    protected JLabel iconLabel;
    protected JLabel optionLabel;
    private Icon icon1;
    private Icon icon2;
    protected ArrayList<InfoEvent> viewEvents;
    
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
        if(!viewEvents.isEmpty())
            viewEvents.forEach(event -> EventDispatcher.InnerClass.instance.fireEvent(event.newCopy()));
    }
    
    protected void lightComponent(){
        iconLabel.setIcon(icon2);
        optionLabel.setForeground(Color.YELLOW);
    }
    
    protected void darkComponent(){
        iconLabel.setIcon(icon1);
        optionLabel.setForeground(Color.WHITE);
    }
    
    public OptionPanel(ImageIcon icon1, ImageIcon icon2, String option, InfoEvent... event){
        this(icon1, icon2, option, null, event);
    }
    
    
   
    
    public OptionPanel(
            ImageIcon icon1, 
            ImageIcon icon2,
            String option, 
            String tip,
            InfoEvent... events){
        
        this.viewEvents = new ArrayList<InfoEvent>();
        
        for(InfoEvent event : events)
            this.viewEvents.add(event);
        
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
      
        if(option != null){
            add(iconLabel, BorderLayout.WEST);
            add(optionLabel, BorderLayout.EAST);
        }else {
            add(iconLabel, BorderLayout.CENTER);
        }
        
        
        if(tip != null){
            iconLabel.setToolTipText(tip);
            optionLabel.setToolTipText(tip);
        }
        
    }
    
}
