package flashGetter.view.tasktable;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;

/**
 * @author decaywood
 * 
 * 2015年1月29日
 * 
 */
public class FileIconRenderer extends JLabel implements WidthScaleCellRenderer {

    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setIcon(ImageUtil.readIcon(Resources.deleteTask, 20));
        return this;
    }

    @Override
    public void scaleWidth(TableColumn column) {
        column.setMaxWidth(TaskTableView.ROW_HEIGHT);
    }

}
