package flashGetter.view;

import org.pushingpixels.substance.api.SubstanceConstants.ImageWatermarkKind;
import org.pushingpixels.substance.api.skin.RavenSkin;
import org.pushingpixels.substance.api.watermark.SubstanceImageWatermark;

import flashGetter.Resources;
import flashGetter.view.sidebar.SideBar;

/**
 * @author decaywood
 * 
 * 2015年1月27日
 * 
 */
public class FlashGetterRavenSkin extends RavenSkin {
    
    SubstanceImageWatermark imageWatermark;
    
    
    public FlashGetterRavenSkin() {
        
        this(0.2f);
        
    }
    
    public FlashGetterRavenSkin(float opacity) {
        
        this(opacity, ImageWatermarkKind.SCREEN_CENTER_SCALE);
        
    }
    
    
    
    public FlashGetterRavenSkin(float opacity, ImageWatermarkKind kind) {
        super();
        imageWatermark = new SubstanceImageWatermark(
                SideBar.class.getResourceAsStream(Resources.background.getAddress()));
        watermark = imageWatermark;
        imageWatermark.setOpacity(opacity);
        imageWatermark.setKind(kind);
    }

}
