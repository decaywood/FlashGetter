package flashGetter.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import flashGetter.util.ImageUtil;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class EmptyPanel extends JPanel {
    
    Image image = ImageUtil.readBufferedImage("/images/backGround.png");
    
    public EmptyPanel() {}
    
    public EmptyPanel(Image image) {
        this.image = image; 
    }
 
    
    public EmptyPanel(LayoutManager layout) {
        this(layout, true);
    }

    public EmptyPanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }
}
