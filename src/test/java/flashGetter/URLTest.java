package flashGetter;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;

import org.apache.commons.net.ftp.FTPClient;
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

    String password, String path) throws IOException {
         
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(server, port);
            ftpClient.setControlEncoding("GBK");
            
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                if (ftpClient.login(user, password)) {
                    if (path.length() != 0) {
                        ftpClient.changeWorkingDirectory(path);
                    }
                    return true;
                }
            }
        } finally {
            ftpClient.disconnect();
        }
        
        return false;
    }

    public static void main(String[] args) throws IOException {
        String urlpath = "ftp://ygdy8:ygdy8@y201.dygod.org:1180/[阳光电影www.ygdy8.com].致胜王牌.HD.576p.中英双字幕.rmvb";
        FTPInfo info = FTPAddressParser.parseAdress(urlpath);
        connectServer(info.getServer(), info.getPort(), info.getUserName(), info.getPassword(), urlpath);
    }

}
