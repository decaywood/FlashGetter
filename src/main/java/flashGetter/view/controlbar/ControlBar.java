package flashGetter.view.controlbar;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;
import flashGetter.view.OptionPanel;
import flashGetter.view.EventDispatcher;
import flashGetter.view.InfoEvent;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class ControlBar extends JPanel{
    
   
    public ControlBar() {
        setLayout(new FlowLayout());
    }
 
    protected ControlBar addPanel(
            Resources type, 
            Resources typeChoosed, 
            String name, 
            String tip,
            InfoEvent... events){
        OptionPanel taskPanel = new ControlBarOptionPanel(ImageUtil.readIcon(type, 30),
                ImageUtil.readIcon(typeChoosed, 30), null, tip, events);
        add(taskPanel);
        return this;
    }
  

}
