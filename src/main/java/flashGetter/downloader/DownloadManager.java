package flashGetter.downloader;

import java.net.URL;

import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;
import flashGetter.view.InfoEvent;

/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public abstract class DownloadManager implements EventHandler<DownloadManager>, DownloadRegulation{
    
    public static final int CREATE_TASK = 0x0000000f;
    public static final int START_TASK  = 0x000000f0;
    public static final int PAUSE_TASK  = 0x00000f00;
    public static final int DELETE_TASK = 0x0000f000;
    
    public DownloadManager() {
        EventDispatcher.InnerClass.instance.register(this);
    }

    @Override
    public void invoke(InfoEvent event) {
        int operationKey = event.getOperationKey();
        if(operationKey == CREATE_TASK)
            createTask(event.getInfo());
    }

    @Override
    public Class<DownloadManager> getGroupClass() {
        return DownloadManager.class;
    }

    @Override
    public void createTask(String address) {
        
    }

    

}
