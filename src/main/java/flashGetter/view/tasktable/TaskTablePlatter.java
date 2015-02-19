package flashGetter.view.tasktable;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import flashGetter.view.InfoEvent;
import flashGetter.view.EventDispatcher;
import flashGetter.view.EventHandler;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class TaskTablePlatter extends JScrollPane implements EventHandler{
    
    public static interface ITaskTablePlatter{}
    public static interface IDownloadingTaskTable extends ITaskTablePlatter{}
    public static interface IDownloadedTaskTable extends ITaskTablePlatter{}
    public static interface IDeletedTaskTable extends ITaskTablePlatter{}
    
    
    private Map<Class<? extends ITaskTablePlatter>, TaskTable> tables;
    
    public TaskTablePlatter() {
        
        EventDispatcher.InnerClass.instance.register(this);
        
        tables = new HashMap<Class<? extends ITaskTablePlatter>, TaskTable>();
        
        tables.put(IDownloadingTaskTable.class, new DownloadingTable());
        tables.put(IDownloadedTaskTable.class, new DownloadedTable());
        tables.put(IDeletedTaskTable.class, new DeletedTable());
        
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        
        setViewportView((tables.get(IDownloadingTaskTable.class)));
        
    }

    @Override
    public void invoke(InfoEvent event) {
        JTable table = tables.get(event.getTarget());
        setViewportView(table);
        table.repaint();
    }

    @Override
    public boolean filter(InfoEvent event) {
        return ITaskTablePlatter.class.isAssignableFrom(event.getTarget());
    }

}
