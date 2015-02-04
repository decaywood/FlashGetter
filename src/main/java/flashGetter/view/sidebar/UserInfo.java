package flashGetter.view.sidebar;

import javax.swing.ImageIcon;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public interface UserInfo {
    
    public String getLevel();
      
    public String getUserID();
     
    public String getUserName();
    
    public ImageIcon getUserPhoto();
    
    public boolean isVip();

}
