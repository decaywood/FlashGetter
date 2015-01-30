package flashGetter.view.tasktable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public abstract class TaskTable<T extends TaskTableModel> extends JTable implements MouseListener{
    
    protected List<TableHeadRenderer> headerLabels;
    protected List<TableCellRenderer> columnCellRenderers;
    protected TaskTableModel tableModel;
    
    protected TableHeadRenderer typeLabel;
    protected TableHeadRenderer nameLabel;
    protected TableHeadRenderer sizeLabel;
    protected TableHeadRenderer progressLabel;
    protected TableHeadRenderer remainTimeLabel;
    protected TableHeadRenderer finishTimeLabel;
    protected TableHeadRenderer createTimeLabel;
    protected TableHeadRenderer speedLabel;
    
    protected FileIconRenderer fileIconRenderer;
    protected StringCellRenderer nameCellRenderer;
    protected StringCellRenderer sizeCellRenderer;
    protected ProgressBarCellRender progressBarCellRender;
    protected StringCellRenderer timeCellRenderer;
    protected StringCellRenderer speedCellRenderer;
    
    public TaskTable(T tableModel) {
        
        headerLabels = new ArrayList<TableHeadRenderer>();
        columnCellRenderers = new ArrayList<TableCellRenderer>();
                
        this.tableModel = tableModel;
        
        setModel(tableModel);
        setRowHeight(30);
        
        tableHeader.addMouseListener(this);
        
        /**
         * headIcon
         */
        typeLabel = new TableHeadRenderer("Flie Type");
        nameLabel = new TableHeadRenderer("Flie Name");
        sizeLabel = new TableHeadRenderer("Flie Size");
        progressLabel = new TableHeadRenderer("Progress");
        remainTimeLabel = new TableHeadRenderer("Remain Time");
        finishTimeLabel = new TableHeadRenderer("Finish Time");
        createTimeLabel = new TableHeadRenderer("Create Time");
        speedLabel = new TableHeadRenderer("Download Speed");
        
        /**
         * cellRenderer per column
         */
        fileIconRenderer = new FileIconRenderer();
        nameCellRenderer = new StringCellRenderer(data -> "name not be implemented yet");
        sizeCellRenderer = new StringCellRenderer(data -> "size not be implemented yet");
        progressBarCellRender = new ProgressBarCellRender();
        timeCellRenderer = new StringCellRenderer(data -> "time not be implemented yet");
        speedCellRenderer = new StringCellRenderer(data -> "speed not be implemented yet");
       
//      setRowSorter(new DownloadingTaskTableRowSorter());
        
        initParameter();
        
        for(int i = 0; i < headerLabels.size(); i++){
            TableColumn column = getColumnModel().getColumn(i);
            column.setCellRenderer(columnCellRenderers.get(i));
            column.setHeaderRenderer(headerLabels.get(i));
            column.setIdentifier(headerLabels.get(i));
        }
        
          
    }
    
    protected void addBundle(TableHeadRenderer headerLabel, TableCellRenderer columnCellRenderer){
        headerLabels.add(headerLabel);
        columnCellRenderers.add(columnCellRenderer);
    }
    
    protected abstract void initParameter();
        
    
    @Override
    public void mouseReleased(MouseEvent e) {
        headerLabels.forEach(l -> l.releaseLabel());
        getTableHeader().repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int selectColumn = getTableHeader().columnAtPoint(e.getPoint());
        TableHeadRenderer label = (TableHeadRenderer)getColumnModel().getColumn(selectColumn).getIdentifier();
        label.pressLabel();
        getTableHeader().repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
      
}
