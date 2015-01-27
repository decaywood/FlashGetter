package flashGetter.view;

import javax.swing.ImageIcon;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public interface UserInfoView {
    
    public String getLevel();
      
    public String getUserID();
     
    public String getUserName();
    
    public ImageIcon getUserPhoto();
    
    public boolean isVip();

}
