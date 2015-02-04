package flashGetter.view.tasktable;

import javax.swing.JFrame;
import javax.swing.JLabel;

import model.DownloadedTableModel;
import model.TaskTableModel;

/**
 * @author decaywood
 * 
 * 2015年1月30日
 * 
 * 
 */
public class DownloadedTableView extends TaskTableView<DownloadedTableModel>{
    

    @Override
    protected void initParameter() {
        // icon / string / string / bar / time / string
        addBundle(typeLabel, fileIconRenderer);
        addBundle(nameLabel, nameCellRenderer);
        addBundle(sizeLabel, sizeCellRenderer);
        addBundle(finishTimeLabel, timeCellRenderer);
        
    }
    
    
    public DownloadedTableView() {
        
        super(new DownloadedTableModel());
        
    }
    
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
//        jFrame.add(new TaskTablePlatter(new DownloadedTable()));
        
        jFrame.setVisible(true);
        jFrame.pack();
        
    }

  

}
