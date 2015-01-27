package flashGetter.view;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.security.auth.callback.Callback;
import javax.swing.JFrame;
import javax.swing.JPanel;

import flashGetter.Resources;
import flashGetter.controller.Controller;
import flashGetter.util.ImageUtil;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class ControlBar extends JPanel{
    
    protected Controller controller;
    
    public ControlBar(Controller controller) {
        this.controller = controller;
        setLayout(new FlowLayout());
    }
    
    private static class DownloadingControlBar extends ControlBar{
        
        public DownloadingControlBar(Controller controller) {
            
            super(controller);
             
            addPanel(Resources.newTask, Resources.newTaskChoosed, "New Task", "Create a Task");
            
            addPanel(Resources.startTask, Resources.startTaskChoosed, null, "Start Task");
            
            addPanel(Resources.pauseTask, Resources.pauseTaskChoosed, null, "Pause Task");
            
            addPanel(Resources.deleteTask, Resources.deleteTaskChoosed, null, "Delete Task");
            
        }

       
    }
    
    
    private static class DownloadedControlBar extends ControlBar{
        
        public DownloadedControlBar(Controller controller) {
            
            super(controller);
             
            addPanel(Resources.newTask, Resources.newTaskChoosed, "New Task", "Create a Task");
            
            addPanel(Resources.deleteTask, Resources.deleteTaskChoosed, null, "Delete Task");
            
        }

    }
    
    private static class DeletedControlBar extends ControlBar{
        
        public DeletedControlBar(Controller controller) {
            
            super(controller);
             
            addPanel(Resources.recoverTask, Resources.recoverTaskChoosed, "New Task", "Create a Task");
            
            addPanel(Resources.removeTask, Resources.removeTaskChoosed, null, "Remove Task");
            
            addPanel(Resources.removeAllTask, Resources.removeAllTaskChoosed, null, "Remove All Task");
            
        }

    }
    
    protected void Callback() {
        controller.invoke();
    }
    
    protected ControlBar addPanel(
            Resources type, 
            Resources typeChoosed, 
            String name, 
            String tip){
        OptionPanel deleteTaskPanel = new OptionPanel(ImageUtil.readIcon(type, 30),
                ImageUtil.readIcon(typeChoosed, 30), null, tip);
        deleteTaskPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Callback();
            }
        });
        add(deleteTaskPanel);
        return this;
    }
    
   
    
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
//        jFrame.getContentPane().add(new ControlBar());
        jFrame.setVisible(true);
    }

}
