package flashGetter.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class ImageUtil {
    
    public static ImageIcon readIcon(URL url, int size){
        
        return readIcon(url, size, size);
        
    }
    
    public static ImageIcon readIcon(String url, int size){
        
        return readIcon(ImageUtil.class.getResource(url), size, size);
        
    }
    
    public static ImageIcon readIcon(String url, int width, int height){
        
        return readIcon(ImageUtil.class.getResource(url), width, height);
        
    }
    
    public static ImageIcon readIcon(URL url, int width, int height){
        BufferedImage dest = null;
        try {
            BufferedImage source = ImageIO.read(url);
            dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            dest.getGraphics().drawImage(source.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ImageIcon(dest);
    }
    
    public static BufferedImage readBufferedImage(URL url, int size){
        
        return readBufferedImage(url, size, size);
        
    }
    
    public static BufferedImage readBufferedImage(String url, int size){
        
        return readBufferedImage(ImageUtil.class.getResource(url), size, size);
        
    }
    
    public static BufferedImage readBufferedImage(String url, int width, int height){
        
        return readBufferedImage(ImageUtil.class.getResource(url), width, height);
        
    }
    
    public static BufferedImage readBufferedImage(URL url, int width, int height){
        BufferedImage dest = null;
        try {
            BufferedImage source = ImageIO.read(url);
            dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            dest.getGraphics().drawImage(source.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null );
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }
    
    public static BufferedImage readBufferedImage(String url){
        BufferedImage source = null;
        try {
            source = ImageIO.read(ImageUtil.class.getResource(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return source;
    }

}
