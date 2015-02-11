package flashGetter.view.model;

/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public class DeletedTableTableModel extends TaskTableModel {

    public DeletedTableTableModel() {
        super(4);
    }

    @Override
    boolean match(Class<?> clazz) {
        return DeletedTableTableModel.class == clazz;
    }

    @Override
    void updateRow(int row, Long taskID) {
        // TODO Auto-generated method stub
        
    }

}
