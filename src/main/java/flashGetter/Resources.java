package flashGetter;

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
    deleted("/images/deleted.png"),
    deletedChoosed("/images/deletedChoosed.png"),
    defaultUser("/images/defaultUser.png"),
    background("/images/background.png");
    String resourcesAddress;
    
    public String getAddress(){
        return resourcesAddress;
    }
    
    private Resources(String resourcesAddress) {
        this.resourcesAddress = resourcesAddress;
    }

}
