package flashGetter.util;

import java.util.concurrent.atomic.LongAdder;

import org.joda.time.DateTime;

/**
 * @author decaywood
 * 
 * 2015年2月9日
 * 
 */
public class TimeUtil {
    
    public static class SpeedCounter {
        
        private long oldStart = System.currentTimeMillis();
        private long oldCurrentSize;
        private double oldSpeed;
        
        private SpeedCounter() {}
        
        public double getSpeed(long currentSize) {
            
            long start = System.currentTimeMillis();
            if(start - oldStart < 1000) return oldSpeed;
            
            this.oldStart = start;
            oldSpeed = (currentSize - oldCurrentSize) * 1D / 1000;
            oldCurrentSize = currentSize;
            return oldSpeed;
            
        }
        
    }

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
    
    public static SpeedCounter getSpeedCounter(){
        return new SpeedCounter();
    }
    
}
