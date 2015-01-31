package flashGetter.view.tasktable;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * @author decaywood
 * 
 * 2015年1月30日
 * 
 * 设置表格单元宽度
 * 
 */
public interface WidthScaleCellRenderer extends TableCellRenderer {
    
    public void scaleWidth(TableColumn column);

}
