package flashGetter.view;

/**
 * @author decaywood
 * 
 * 2015年1月27日
 * 
 */
public interface EventHandler <C> {
    
    public void invoke(InfoEvent event);
    
    /*
     * 控制接收范围，分发器会根据继承关系有选择的分发消息
     */
    public Class<C> getGroupClass();
    
}
