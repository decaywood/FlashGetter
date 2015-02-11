package flashGetter.util;

/**
 * @author decaywood
 * 
 * 2015年2月11日
 * 
 */
public class ParameterUnitUtil {

    public static String getFileSize(long fileSize){
        
        if(fileSize < 1000L) return fileSize + " B";
        if(fileSize < 1000000L) return fileSize / 1000L + " KB";
        if(fileSize < 1000000000L) return fileSize / 1000000L + " MB";
        if(fileSize < 100000000000L) return fileSize / 1000000000L + "GB";
        if(fileSize < 100000000000000L) return fileSize / 1000000000000L + "TB";
        
        return "Size Error!";
        
    }
    
 public static String getDownloadSpeed(double speed){
        
        if(speed < 1000D) return speed + " B/s";
        if(speed < 1000000D) return speed / 1000D + " KB/s";
        if(speed < 1000000000D) return speed / 1000000D + " MB/s";
        if(speed < 100000000000D) return speed / 1000000000D + "GB/s";
        if(speed < 100000000000000D) return speed / 1000000000000D + "TB/s";
        
        return "speed Error!";
        
    }
    
}
