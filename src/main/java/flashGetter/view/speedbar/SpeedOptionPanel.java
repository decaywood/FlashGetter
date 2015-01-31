package flashGetter.view.speedbar;

import javax.swing.JLabel;
import javax.swing.JPanel;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;
import flashGetter.view.OptionPanel;
import flashGetter.view.ViewEvent;

import java.awt.BorderLayout;

/**
 * @author decaywood
 * 
 * 2015年1月30日
 * 
 */
public class SpeedOptionPanel extends OptionPanel {
    
    
    public SpeedOptionPanel() {
        
        super(ImageUtil.readIcon(Resources.downloadingSpeed, 30),
                            ImageUtil.readIcon(Resources.downloadingSpeedChoosed, 30),
                            "0 kb/s", "Flow monitoring", new ViewEvent().setTarget(SpeedPlatter.class));
    }

    public void setText(String text){
        optionLabel.setText(text);
    }
}
