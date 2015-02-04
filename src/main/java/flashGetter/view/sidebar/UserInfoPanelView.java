package flashGetter.view.sidebar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang3.StringUtils;

import flashGetter.Resources;
import flashGetter.presenter.UserInfoPresenter;
import flashGetter.presenter.UserInfoPresenter.UserInfoPresenterView;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class UserInfoPanelView extends JPanel implements UserInfoPresenterView {
    
    private JPanel userPhotoPanel;
    private JPanel infoPanel;
    
    private UserInfoPresenter presenter;
    
    private static class InfoPanel extends JPanel{
        
        public InfoPanel(String key, String value){
            
            String text = null;
            int width = 30;
            text = value.length() > width ? 
                    value.replaceAll(value.substring(width - 3, value.length()), "...")
                    : value + StringUtils.repeat(' ', width - value.length());
                
            JLabel keyLabel = new JLabel(key + " : ");
            JLabel valueLabel = new JLabel(text);
            
            valueLabel.setToolTipText(value);
           
            keyLabel.setFont(Resources.getFont());
            valueLabel.setFont(Resources.getFont());
            
            keyLabel.setForeground(Color.WHITE);
            valueLabel.setForeground(Color.WHITE);
            
            setLayout(new BorderLayout());
            add(valueLabel, BorderLayout.EAST);
            add(keyLabel, BorderLayout.WEST);
            
        }
        
    }
    
   
    public UserInfoPanelView() {
        
        presenter = new UserInfoPresenter(this);
        
        setLayout(new GridLayout(2, 1));
        setVisible(true);
        
        presenter.initView();
        
    }
    
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.add(new UserInfoPanelView());
    }


    @Override
    public void setUserInfoView(UserInfoView userInfo) {
        
        removeAll();
        
        userPhotoPanel = new JPanel(new BorderLayout());
        userPhotoPanel.add(new JLabel(userInfo.getUserPhoto()), BorderLayout.CENTER);
        add(userPhotoPanel);
        
        infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 1, 0, 5));
        infoPanel.add(new InfoPanel("UserID", userInfo.getUserID()));
        infoPanel.add(new InfoPanel("UserName", userInfo.getUserName()));
        infoPanel.add(new InfoPanel("Level", userInfo.getLevel()));
        add(infoPanel);
        
    }
    
}
