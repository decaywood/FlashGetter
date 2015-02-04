package flashGetter.view.tasktable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import model.TaskTableModel;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public abstract class TaskTable<T extends TaskTableModel> extends JTable implements MouseListener{
    
    public static final int ROW_HEIGHT = 30;
    
    protected List<TableHeadRenderer> headerLabels;
    protected List<WidthScaleCellRenderer> columnCellRenderers;
    protected TaskTableModel tableModel;
    
    protected TableHeadRenderer typeLabel;
    protected TableHeadRenderer nameLabel;
    protected TableHeadRenderer sizeLabel;
    protected TableHeadRenderer progressLabel;
    protected TableHeadRenderer remainTimeLabel;
    protected TableHeadRenderer finishTimeLabel;
    protected TableHeadRenderer createTimeLabel;
    protected TableHeadRenderer speedLabel;
    
    protected WidthScaleCellRenderer fileIconRenderer;
    protected WidthScaleCellRenderer nameCellRenderer;
    protected WidthScaleCellRenderer sizeCellRenderer;
    protected WidthScaleCellRenderer progressBarCellRender;
    protected WidthScaleCellRenderer timeCellRenderer;
    protected WidthScaleCellRenderer speedCellRenderer;
    
    public TaskTable(T tableModel) {
        
        headerLabels = new ArrayList<TableHeadRenderer>();
        columnCellRenderers = new ArrayList<WidthScaleCellRenderer>();
                
        this.tableModel = tableModel;
        setModel(tableModel);
        setRowHeight(ROW_HEIGHT);
        
        tableHeader.addMouseListener(this);
        
        /**
         * headIcon
         */
        typeLabel = new TableHeadRenderer("Flie Type");
        typeLabel.setToolTipText("Flie Type");
        
        nameLabel = new TableHeadRenderer("Flie Name");
        typeLabel.setToolTipText("Flie Name");
        
        sizeLabel = new TableHeadRenderer("Flie Size");
        sizeLabel.setToolTipText("File Size");
        
        progressLabel = new TableHeadRenderer("Progress");
        progressLabel.setToolTipText("Progress");
        
        remainTimeLabel = new TableHeadRenderer("Remain Time");
        remainTimeLabel.setToolTipText("Remain Time");
        
        finishTimeLabel = new TableHeadRenderer("Finish Time");
        finishTimeLabel.setToolTipText("Finish Time");
        
        createTimeLabel = new TableHeadRenderer("Create Time");
        createTimeLabel.setToolTipText("Create Time");
        
        speedLabel = new TableHeadRenderer("Download Speed");
        speedLabel.setToolTipText("Download Speed");
        
        /**
         * cellRenderer per column
         */
        fileIconRenderer = new FileIconRenderer();
        
        nameCellRenderer = new StringCellRenderer(" xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx.rmvb ".length(),
                data -> "name not be implemented yet");
        
        sizeCellRenderer = new StringCellRenderer(" xxx xb ".length(),
                data -> "size not be implemented yet");
        
        progressBarCellRender = new ProgressBarCellRender();
        
        timeCellRenderer = new StringCellRenderer(" xxxx-xx-xx xx:xx:xx ".length(),
                data -> "time not be implemented yet");
        
        speedCellRenderer = new StringCellRenderer(" xxx xb/s ".length(),
                data -> "speed not be implemented yet");
       
//      setRowSorter(new DownloadingTaskTableRowSorter());
        
        initParameter();
        
        for(int i = 0; i < headerLabels.size(); i++){
            WidthScaleCellRenderer cellRenderer = columnCellRenderers.get(i);
            TableColumn column = getColumnModel().getColumn(i);
            TableHeadRenderer headRenderer = headerLabels.get(i);
            column.setCellRenderer(cellRenderer);
            cellRenderer.scaleWidth(column);
            column.setHeaderRenderer(headRenderer);
            column.setIdentifier(headRenderer);
        }
        
          
    }
    
    protected void addBundle(TableHeadRenderer headerLabel, WidthScaleCellRenderer columnCellRenderer){
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
