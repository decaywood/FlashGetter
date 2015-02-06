package flashGetter;

import flashGetter.downloader.DownloadManager;
import flashGetter.view.MainFrame;

/**
 * @author decaywood
 * 
 * 2015年1月26日
 * 
 */
public class MainApp {

    public static void main(String[] args) {
        
        MainFrame.initializeMainFrame();
        DownloadManager.initializeDownloadManager();
    }
}
