package flashGetter.view.tasktable;

import javax.swing.JFrame;
import javax.swing.JLabel;

import flashGetter.view.tasktable.DownloadedTableView.DownloadedTableModel;

/**
 * @author decaywood
 * 
 * 2015年1月30日
 * 
 */
public class DeletedTableView extends TaskTableView<DeletedTableView.DeletedTableTableModel> {
    
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
    
    
    public DeletedTableView() {
        
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
