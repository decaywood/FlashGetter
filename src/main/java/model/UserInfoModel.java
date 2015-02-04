package model;

import javax.swing.ImageIcon;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;
import flashGetter.view.sidebar.UserInfo;

/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public class UserInfoModel {
    
    private static class DefaultUserInfo implements UserInfo{

        public String getLevel() {
            return "Null";
        }
        public String getUserID() {
            return "Null";
        }
        public String getUserName() {
            return "please click photo to login";
        }
        public ImageIcon getUserPhoto() {
            return ImageUtil.readIcon(Resources.defaultUser, 50);
        }
        public boolean isVip() {
            return false;
        }
        
    }
    
    public UserInfo getUserInfo(){
        return new DefaultUserInfo();
    }

}
