package flashGetter.downloader.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

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
    private FTPInfo ftpInfo;
    
    public FTPTaskThread(DownloadingTask task) {
       
        this.taskInfo = task;
        initClient();
        refreshTaskInfo();
    }
    
    public void refreshTaskInfo(){
        try {
            
            FTPFile[] files = ftpClient.listFiles();
            this.remotefile = Arrays.stream(files)
                    .filter(file -> file.getName().equals(ftpInfo.getFileName())).findFirst().get();
            
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
            ftpInfo = FTPAddressParser.parseAdress(downloadURL);
            
            ftpClient.connect(ftpInfo.getServer(), ftpInfo.getPort());
            ftpClient.setControlEncoding("GBK");
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            
            String user = ftpInfo.getUserName();
            String password = ftpInfo.getPassword();
         
            
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                if(user != null && password != null)
                    ftpClient.login(user, password);
                ftpClient.changeWorkingDirectory(ftpInfo.getFilePath());
            }
                
            
        } catch (IOException e) {
            LOGGER.info("URL is not connected", e);
        }
    }

    private void execute(){
        
        try {
            String fileName = taskInfo.getFileName();
            String savePath = taskInfo.getSavePath();
            String filePath = savePath.concat(fileName);
            
            long startOffset = taskInfo.getStartOffset();
            File outputFile = new File(filePath);
            
            if(outputFile.length() >= remotefile.getSize()) return; // downloading done
            
            FileOutputStream fos = new FileOutputStream(outputFile, true); // data append mode
            
            ftpClient.setRestartOffset(startOffset); // begin from startOffset
            InputStream in = ftpClient.retrieveFileStream(ftpInfo.getFilePath());
            
            byte[] buffer = new byte[1024 * 1024];
            int dataSize = 0;
            
            while ((dataSize = in.read(buffer)) != -1) {
                startOffset = taskInfo.getStartOffset();
                fos.write(buffer, 0, dataSize);
                taskInfo.setStartOffset(startOffset + dataSize);
            }
            System.out.println("done");
            fos.flush();
            fos.close();
        } catch (IOException e) {
            LOGGER.info("IO problem!", e);
        } 
    }
    
    @Override
    public void run() {
        execute();
    }
    
    

}
