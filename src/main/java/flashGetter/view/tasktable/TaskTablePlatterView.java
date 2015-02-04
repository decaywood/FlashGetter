package flashGetter.view.tasktable;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import flashGetter.view.ViewEvent;
import flashGetter.view.ViewEventDispatcher;
import flashGetter.view.ViewEventHandler;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class TaskTablePlatterView extends JScrollPane implements ViewEventHandler<TaskTableView>{
    
    private Map<Class<? extends TaskTableView>, TaskTableView> tables;
    
    public TaskTablePlatterView() {
        
        ViewEventDispatcher.InnerClass.instance.register(this);
        
        tables = new HashMap<Class<? extends TaskTableView>, TaskTableView>();
        
        tables.put(DownloadingTableView.class, new DownloadingTableView());
        tables.put(DownloadedTableView.class, new DownloadedTableView());
        tables.put(DeletedTableView.class, new DeletedTableView());
        
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
        
        setViewportView((tables.get(DownloadingTableView.class)));
        
    }

    @Override
    public void invoke(ViewEvent event) {
        JTable table = tables.get(event.getTarget());
        setViewportView(table);
        table.repaint();
    }

    @Override
    public Class<TaskTableView> getGroupClass() {
        return TaskTableView.class;
    }

}
