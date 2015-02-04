package flashGetter.view.controlbar;

import javax.swing.ImageIcon;

import flashGetter.view.InfoEvent;
import flashGetter.view.OptionPanel;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class ControlBarOptionPanel extends OptionPanel {

    public ControlBarOptionPanel(ImageIcon icon1, ImageIcon icon2,
            String option, String tip, InfoEvent... events) {
        super(icon1, icon2, option, tip, events);
    }

}
