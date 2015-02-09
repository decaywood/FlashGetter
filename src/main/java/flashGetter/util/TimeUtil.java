package flashGetter.util;

import org.joda.time.DateTime;

/**
 * @author decaywood
 * 
 * 2015年2月9日
 * 
 */
public class TimeUtil {

    private static final String DEFAULT_FORMAT = "YYYY-MM-DD HH:MM:SS EE";
    
    public static String getCurrentTime(String format){
        
        if(format == null) return getCurrentTime();
        
        String nowTime;
        DateTime time = new DateTime();
        nowTime = time.toString(format);
        return nowTime;
    }
    
    public static String getCurrentTime(){
        
        return getCurrentTime(DEFAULT_FORMAT);
        
    }
}
