package flashGetter.downloader.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import flashGetter.downloader.TaskMapper;
import flashGetter.util.FTPAddressParser;
import flashGetter.util.FTPAddressParser.FTPInfo;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public class FTPTaskThread implements Runnable, TaskRunnable{
    
    private static final Logger LOGGER = Logger.getLogger(FTPTaskThread.class);
    
    private FTPClient ftpClient;
    private DownloadingTask taskInfo;
    private FTPFile remotefile;
    
    public FTPTaskThread(DownloadingTask task) {
       
        this.taskInfo = task;
        initClient();
        refreshTaskInfo();
    }
    
    public void refreshTaskInfo(){
        try {
            
            FTPFile[] files = ftpClient.listFiles();
            
            if(files.length == 0) throw new Exception();
            
            this.remotefile = files[0];
            
            this.taskInfo.setFileName(remotefile.getName());
            this.taskInfo.setFileSize(remotefile.getSize());
            
        } catch (Exception e) {
            LOGGER.info("No Files!", e);
        }
        
    }
    
    public void initClient(){
        try {
            
            ftpClient = new FTPClient();
            ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
            
            String downloadURL = taskInfo.getDownloadURL();
            FTPInfo info = FTPAddressParser.parseAdress(downloadURL);
            
            ftpClient.connect(info.getServer(), info.getPort());
            ftpClient.setControlEncoding("GBK");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            ftpClient.login(info.getUserName(), info.getPassword());
            String user = info.getUserName();
            String password = info.getPassword();
         
            
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                if(user != null && password != null)
                    ftpClient.login(user, password);
            }
                
            
        } catch (IOException e) {
            LOGGER.info("URL is not connected", e);
        }
    }

    @Override
    public void run() {
        
        try {
            String fileName = taskInfo.getFileName();
            String savePath = taskInfo.getSavePath();
            String filePath = savePath.concat(fileName);
            
            long startOffset = taskInfo.getStartOffset();
            long endOffset = taskInfo.getEndOffset();
            
            File outputFile = new File(savePath);
            
            if(outputFile.length() >= remotefile.getSize()) return; // downloading done
            
            FileOutputStream fos = new FileOutputStream(outputFile);
            
            InputStream in = ftpClient.retrieveFileStream(filePath);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

}
