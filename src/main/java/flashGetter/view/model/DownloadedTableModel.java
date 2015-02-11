package flashGetter.view.model;

/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public class DownloadedTableModel extends TaskTableModel {

    public DownloadedTableModel() {
        super(4);
    }

    @Override
    boolean match(Class<?> clazz) {
        return false;
    }

    @Override
    void updateRow(int row, Long taskID) {
        // TODO Auto-generated method stub
        
    }

}
