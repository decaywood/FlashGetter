package flashGetter.view.tasktable;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class DownloadingTable extends TaskTable<DownloadingTable.DownloadingTableModel> {
    
           
    public static class DownloadingTableModel extends TaskTableModel{
        public DownloadingTableModel() {
            super(6);
        }
    }
    
    public DownloadingTable() {
        super(new DownloadingTableModel());
        for(int i = 0; i < 40; i++)
        tableModel.addRow(new Object[]{new JLabel(),"aa","bb",50,"cc","dd"});
    }

    @Override
    protected void initParameter() {
        // icon / string / string / bar / time / string
        addBundle(typeLabel, fileIconRenderer);
        addBundle(nameLabel, nameCellRenderer);
        addBundle(sizeLabel, sizeCellRenderer);
        addBundle(progressLabel, progressBarCellRender);
        addBundle(remainTimeLabel, timeCellRenderer);
        addBundle(speedLabel, speedCellRenderer);
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
//        jFrame.add(new TaskTablePlatter(new DownloadingTable()));
        
        jFrame.setVisible(true);
        jFrame.pack();
        
    }


}
