package flashGetter.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 * ftp://userName:passwd@IPaddress&domain:port
 * 
 */
public class FTPAddressParser {
    
    public static class FTPInfo{
        
        private String userName;
        private String password;
        private String server;
        private String fileName;
        private int port;
        
        public String getUserName() {
            return userName;
        }
        public String getPassword() {
            return password;
        }
        public String getServer() {
            return server;
        }
        public String getFileName() {
            return fileName;
        }
        public int getPort() {
            return port;
        }
        
        
    }
    
    private static final Logger LOGGER = Logger.getLogger(FTPAddressParser.class);
    
    private static final String REGEX_1 = "://";
    private static final String REGEX_2 = ":";
    private static final String REGEX_3 = "@";
    private static final String REGEX_4 = "/";
    
    
    public static FTPInfo parseAdress(String address){
        
        FTPInfo info = new FTPInfo();
        
        info.userName = StringUtils.substringBetween(address, REGEX_1, REGEX_2);
        address = StringUtils.substringAfter(address, REGEX_1);
        
        info.password = StringUtils.substringBetween(address, REGEX_2, REGEX_3);
        address = StringUtils.substringAfter(address, REGEX_2);
        
        
        info.server = StringUtils.substringBetween(address, REGEX_3, REGEX_2);
        address = StringUtils.substringAfter(address, REGEX_3);
        
        String port = StringUtils.substringBetween(address, REGEX_2, REGEX_4);
        port = port != null ? port : StringUtils.substringAfter(address, REGEX_2);
        address = StringUtils.substringAfter(address, REGEX_2);
        
        info.port = port != "" ? Integer.parseInt(port) : 0;
        info.fileName = StringUtils.substringAfter(address, REGEX_4);
        
        return info;
    }
    
    
    
    
}
