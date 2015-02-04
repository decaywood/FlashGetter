package flashGetter.view.controlbar;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;
import flashGetter.view.OptionPanel;
import flashGetter.view.ViewEventDispatcher;
import flashGetter.view.ViewEvent;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class ControlBarView extends JPanel{
    
   
    public ControlBarView() {
        setLayout(new FlowLayout());
    }
 
    protected ControlBarView addPanel(
            Resources type, 
            Resources typeChoosed, 
            String name, 
            String tip){
        OptionPanel taskPanel = new ControlBarOptionPanelView(ImageUtil.readIcon(type, 30),
                ImageUtil.readIcon(typeChoosed, 30), null, tip);
        taskPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ViewEvent event = new ViewEvent();
                ViewEventDispatcher.InnerClass.instance.fireEvent(event);
            }
        });
        add(taskPanel);
        return this;
    }
  

}
