package flashGetter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Arrays;

import org.apache.commons.io.input.AutoCloseInputStream;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import flashGetter.util.FTPAddressParser;
import flashGetter.util.FTPAddressParser.FTPInfo;

/**
 * @author decaywood
 * 
 *         2015年2月6日
 * 
 */
public class URLTest {

    public static boolean connectServer(String server, int port, String user,

    String password, String filePath) throws IOException {
         
        FTPClient ftpClient = new FTPClient();
       
        ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        ftpClient.connect(server, port);
        ftpClient.setControlEncoding("GBK");
        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        ftpClient.login(user, password);
        
        String savePath = "E:\\test\\result\\scala.pdf";
        FileOutputStream fos = new FileOutputStream(new File(savePath));
        
        if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
            if(user != null && password != null)
                ftpClient.login(user, password);
            ftpClient.changeWorkingDirectory(filePath);
            FTPFile file = ftpClient.listFiles(filePath)[0];
            InputStream in = ftpClient.retrieveFileStream(filePath);
            byte[] bytes = new byte[1024];
        }
            
       
        
        return false;
    }

    public static void main(String[] args) throws IOException {
        String urlpath = "ftp://flashgetter:123456@192.168.59.1:21/Scala.pdf";
        FTPInfo info = FTPAddressParser.parseAdress(urlpath);
        connectServer(info.getServer(), info.getPort(), info.getUserName()
                , info.getPassword(), info.getFilePath());
    }

}
