package flashGetter.view.tasktable;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DownloadingTable extends TaskTable {
    
    private DownloadingTableModel model;
    private List<TableHeadRenderer> headerLabels;
           
    public static class DownloadingTableModel extends TaskTableModel{
        
        public DownloadingTableModel() {
            super(6);
        }
        
    }

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
    
    
    public DownloadingTable() {
        
        model = new DownloadingTableModel();
        headerLabels = new ArrayList<TableHeadRenderer>();
        
        headerLabels.add(typeLabel);
        headerLabels.add(nameLabel);
        headerLabels.add(sizeLabel);
        headerLabels.add(progressLabel);
        headerLabels.add(remainTimeLabel);
        headerLabels.add(speedLabel);
        
        List<TableCellRenderer> columnCellRenderers = new ArrayList<TableCellRenderer>();
        
        // icon / string / string / bar / time / string
        columnCellRenderers.add(fileIconRenderer);
        columnCellRenderers.add(nameCellRenderer);
        columnCellRenderers.add(sizeCellRenderer);
        columnCellRenderers.add(progressBarCellRender);
        columnCellRenderers.add(timeCellRenderer);
        columnCellRenderers.add(speedCellRenderer);
        
        tableHeader.addMouseListener(this);
        
        model.addRow(new Object[]{new JLabel(),"aa","bb",50,"cc","dd"});
        
        setModel(model);
//        setRowSorter(new DownloadingTaskTableRowSorter());
        
        for(int i = 0; i < headerLabels.size(); i++){
            TableColumn column = getColumnModel().getColumn(i);
            column.setCellRenderer(columnCellRenderers.get(i));
            column.setHeaderRenderer(headerLabels.get(i));
            column.setIdentifier(headerLabels.get(i));
        }
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.add(new TaskTablePlatter(new DownloadingTable()));
        
        jFrame.setVisible(true);
        jFrame.pack();
        
    }
    
   

}
