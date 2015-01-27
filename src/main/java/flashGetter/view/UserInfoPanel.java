package flashGetter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import flashGetter.util.ImageUtil;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class UserInfoPanel extends JPanel {
    
    private JPanel userPhotoPanel;
    private JPanel infoPanel;
    
    private UserInfoView userInfo;
    private static class DefaultUserInfo implements UserInfoView{

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
            return ImageUtil.readIcon("/images/defaultUser.jpg", 50, 50);
        }
        public boolean isVip() {
            return false;
        }
        
    }
    
    private static class InfoPanel extends EmptyPanel{
        
        public InfoPanel(String value, String key){
            
            JLabel valueLabel = new JLabel(value+" : ");
            JLabel keyLabel = new JLabel(key);
            
            valueLabel.setForeground(Color.WHITE);
            keyLabel.setForeground(Color.WHITE);
            
            setLayout(new BorderLayout(0, 0));
            add(valueLabel, BorderLayout.WEST);
            add(keyLabel, BorderLayout.EAST);
            
        }
        
    }
    
    public UserInfoPanel(){
        this(new DefaultUserInfo());
    }
    
    public UserInfoPanel(UserInfoView userInfoView) {
        
        userInfo = userInfoView;
        
        setLayout(new GridLayout(2, 1));
        setVisible(true);
        
        
        userPhotoPanel = new EmptyPanel(new BorderLayout());
        userPhotoPanel.add(new JLabel(userInfo.getUserPhoto()), BorderLayout.CENTER);
        add(userPhotoPanel);
        
        infoPanel = new EmptyPanel();
        infoPanel.setLayout(new GridLayout(3, 1, 0, 20));
        infoPanel.add(new InfoPanel("UserID", userInfo.getUserID()));
        infoPanel.add(new InfoPanel("UserName", userInfo.getUserName()));
        infoPanel.add(new InfoPanel("Level", userInfo.getLevel()));
        add(infoPanel);
        
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.add(new UserInfoPanel());
    }
    
}
