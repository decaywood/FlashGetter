package flashGetter.util;

import java.util.UUID;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
public class SequenceGenerator {
    
    public static Long generateSequence(){
        UUID uuid = UUID.randomUUID();
        return uuid.getMostSignificantBits();
    }
    
    
}
