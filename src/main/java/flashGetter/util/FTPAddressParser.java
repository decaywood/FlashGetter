package flashGetter.util;

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
        
        @Override
        public String toString() {
            return "User Name : " + userName + ", Password : " + password
                    + ", Server IP : " + server + ", Port : " + port 
                    + ", File Name : " + fileName;
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
        
        FTPInfo info = new FTPInfo();
 
        boolean done = parseNoAuthAdress(info, address);
        if(done) return info;
        
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
        info.fileName = getFileName(address);
        
        return info;
    }
    
    
    
    public static boolean isboolIP(String ipAddress){ 
       
        Pattern pattern = Pattern.compile(REGEX_IP);  
        Matcher matcher = pattern.matcher(ipAddress);  
        return matcher.matches();  
        
    }
    
    private static String getFileName(String address){ 
        
        while(address.contains(REGEX_4))
            address = StringUtils.substringAfter(address, REGEX_4);
        
        return address;
        
    }
    
    public static boolean parseNoAuthAdress(FTPInfo info, String address) {  
        
        boolean done = false;
         
        String maybeIP = StringUtils.substringAfter(address, REGEX_1);
        maybeIP = StringUtils.substringBefore(maybeIP, REGEX_2);
        
        if(isboolIP(maybeIP)){
            info.server = maybeIP;
            address = StringUtils.substringAfter(address, REGEX_1);
            String port = isPort(address) ? StringUtils.substringAfter(address, REGEX_2)
                    : StringUtils.substringBetween(address, REGEX_2, REGEX_4);
             
            info.port = getPort(port);
            info.fileName = getFileName(address);
            
            done = true;
        }
        
        return done;
    }  
    
    
    /**
     *  judge whether the string is integer by catch exception
     */
    public static int getPort(String value) {  
        try {  
            return Integer.parseInt(value);  
        } catch (NumberFormatException e) {  
            LOGGER.info("Wrong Number!!", e);
            return 0;  
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
