package flashGetter;

import java.awt.Font;

/**
 * @author decaywood
 * 
 * 2015年1月27日
 * 
 */
public enum Resources {
    
    
    
    downloading("/images/downloading.png"),
    downloadingChoosed("/images/downloadingChoosed.png"),
    downloaded("/images/downloaded.png"),
    downloadedChoosed("/images/downloadedChoosed.png"),
    recycleStation("/images/recycleStation.png"),
    recycleStationChoosed("/images/recycleStationChoosed.png"),
    defaultUser("/images/defaultUser.png"),
    background("/images/background.png"),
    newTask("/images/newTask.png"),
    newTaskChoosed("/images/newTaskChoosed.png"),
    startTask("/images/startTask.png"),
    startTaskChoosed("/images/startTaskChoosed.png"),
    pauseTask("/images/pauseTask.png"),
    pauseTaskChoosed("/images/pauseTaskChoosed.png"),
    deleteTask("/images/deleteTask.png"),
    deleteTaskChoosed("/images/deleteTaskChoosed.png"),
    recoverTask("/images/recoverTask.png"),
    recoverTaskChoosed("/images/recoverTaskChoosed.png"),
    removeTask("/images/removeTask.png"),
    removeTaskChoosed("/images/removeTaskChoosed.png"),
    removeAllTask("/images/removeAllTask.png"),
    removeAllTaskChoosed("/images/removeAllTaskChoosed.png");
    
    String resourcesAddress;
    
    public String getAddress(){
        return resourcesAddress;
    }
    
    public static Font getFont(){
        return new Font(null, Font.ITALIC, 15);
    }
    
    private Resources(String resourcesAddress) {
        this.resourcesAddress = resourcesAddress;
    }

}
