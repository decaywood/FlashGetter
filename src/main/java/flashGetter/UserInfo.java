package flashGetter;

import javax.swing.ImageIcon;

import flashGetter.view.sidebar.UserInfoView;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class UserInfo implements UserInfoView{
    
    private String userID;
    
    private String userName;
    
    private String level;
    
    private boolean vip;
    
    private ImageIcon userPhoto;

    public String getLevel() {
        return level;
    }

    public String getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public ImageIcon getUserPhoto() {
        return userPhoto;
    }

    public boolean isVip(){
        return vip;
    }
   
    

}
