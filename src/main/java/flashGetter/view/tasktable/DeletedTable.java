package flashGetter.view.tasktable;

import javax.swing.JFrame;
import javax.swing.JLabel;

import flashGetter.view.tasktable.DownloadedTable.DownloadedTableModel;

/**
 * @author decaywood
 * 
 * 2015年1月30日
 * 
 */
public class DeletedTable extends TaskTable<DeletedTable.DeletedTableTableModel> {
    
    public static class DeletedTableTableModel extends TaskTableModel{
        
        public DeletedTableTableModel() {
            super(4);
        }
        
    }

    @Override
    protected void initParameter() {
        // icon / string / string / bar / time / string
        addBundle(typeLabel, fileIconRenderer);
        addBundle(nameLabel, nameCellRenderer);
        addBundle(sizeLabel, sizeCellRenderer);
        addBundle(finishTimeLabel, timeCellRenderer);
        
    }
    
    
    public DeletedTable() {
        
        super(new DeletedTableTableModel());
        tableModel.addRow(new Object[]{new JLabel(),"aa","bbcc","dd"});
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
//        jFrame.add(new TaskTablePlatter(new DownloadedTable()));
        
        jFrame.setVisible(true);
        jFrame.pack();
        
    }
    

}
