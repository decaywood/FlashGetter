package flashGetter.view.tasktable;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * @author decaywood
 * 
 * 2015年1月29日
 * 
 */
public class ProgressBarCellRender extends JProgressBar implements WidthScaleCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setValue((Integer)value);
        setStringPainted(true);
        return this;
    }

    @Override
    public void scaleWidth(TableColumn column) {
        column.setMaxWidth(TaskTableView.ROW_HEIGHT * 50);
    }

}
