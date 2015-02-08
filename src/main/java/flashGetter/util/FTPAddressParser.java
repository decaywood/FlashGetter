package flashGetter.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.omg.PortableServer.POA;

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
        private String filePath;
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
        
        public String getFilePath() {
            return filePath;
        }
        
       
        public int getPort() {
            return port;
        }
        
        @Override
        public String toString() {
            return "User Name : " + userName + ", Password : " + password
                    + ", Server IP : " + server + ", Port : " + port 
                    + ", File Path : " + filePath;
        }
        
    }
    
    private static final Logger LOGGER = Logger.getLogger(FTPAddressParser.class);
    
    private static final String REGEX_1 = "://";
    
    private static final String REGEX_2 = ":";
    
    private static final String REGEX_3 = "@";
    
    private static final String REGEX_4 = "/";
    
    private static final String REGEX_IP=
            
            "(2[5][0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\."
            + "(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\."
            + "(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})\\."
            + "(25[0-5]|2[0-4]\\d|1\\d{2}|\\d{1,2})";  
    
    public static FTPInfo parseAdress(String address){
        
        String parseAddress = address;
        FTPInfo info = new FTPInfo();
 
        address = getNoAuthIP(info, parseAddress);
        if(info.server == null) address = getAuthIP(info, parseAddress);
       
        address = getPort(info, address);
        getFileInfo(info, address);
        
        return info;
    }
    
    
    
    public static boolean isboolIP(String ipAddress){ 
       
        if(ipAddress == null) return false;
        
        try {
            ipAddress = StringUtils.substringAfter(InetAddress.getByName(ipAddress).toString(), REGEX_4);
            Pattern pattern = Pattern.compile(REGEX_IP);  
            Matcher matcher = pattern.matcher(ipAddress);  
            return matcher.matches();  
        } catch (UnknownHostException e) {
            LOGGER.info("Wrong IP Address!", e);
            return false;
        }
        
       
        
    }
 
    private static String getIP(String ipAddress){
        try {
            if(isboolIP(ipAddress)) return ipAddress;
            ipAddress = StringUtils.substringAfter(InetAddress.getByName(ipAddress).toString(), REGEX_4);
        } catch (UnknownHostException e) {
            LOGGER.info("Wrong IP Address!", e);
        }
        return ipAddress;
    }
    
    private static void getFileInfo(FTPInfo info, String address){ 
        
        info.filePath = address;
        
    }
    
    private static String getAuthIP(FTPInfo info, String address) {  
        
         
        info.userName = StringUtils.substringBetween(address, REGEX_1, REGEX_2);
        address = StringUtils.substringAfter(address, REGEX_1);
        
        info.password = StringUtils.substringBetween(address, REGEX_2, REGEX_3);
        address = StringUtils.substringAfter(address, REGEX_2);
        
        
        String serverIP = StringUtils.substringBetween(address, REGEX_3, REGEX_2);
        serverIP = isboolIP(serverIP) ? serverIP : StringUtils.substringBetween(address, REGEX_3, REGEX_4);
        info.server = isboolIP(serverIP) ? getIP(serverIP) : StringUtils.substringAfter(address, REGEX_3);
       
        return StringUtils.substringAfter(address, serverIP);
    }  
    
    

    private static String getNoAuthIP(FTPInfo info, String address) {  
        
         
        String maybeIP = StringUtils.substringAfter(address, REGEX_1);
        maybeIP = isboolIP(maybeIP) ? maybeIP : StringUtils.substringBefore(maybeIP, REGEX_2);
        maybeIP = isboolIP(maybeIP) ? maybeIP : StringUtils.substringBefore(maybeIP, REGEX_4);
        
        if(isboolIP(maybeIP)){
            info.server = getIP(maybeIP);
            address = StringUtils.substringAfter(address, maybeIP);
        }
        
        return address;
    }  
    
    
    /**
     *  judge whether the string is integer by catch exception
     */
    public static String getPort(FTPInfo info, String value) {  
        String portValue = value;
        try {  
            if(!value.contains(REGEX_2)) return portValue;
            value = StringUtils.substringAfter(value, REGEX_2);
            value = isPort(value) ? value : StringUtils.substringBefore(value, REGEX_4);
            info.port = Integer.parseInt(value);  
            return StringUtils.substringAfter(portValue, value);
        } catch (NumberFormatException e) {  
            LOGGER.info("Wrong Number!!", e);
            return portValue;  
        }  
    }  
    
    private static boolean isPort(String value){
        try {  
            Integer.parseInt(value);  
            return true;
        } catch (NumberFormatException e) {  
            return false;  
        }  
    }
    
    
}
