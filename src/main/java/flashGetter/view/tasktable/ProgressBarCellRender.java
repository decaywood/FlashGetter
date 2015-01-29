package flashGetter.view.tasktable;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * @author decaywood
 * 
 * 2015年1月29日
 * 
 */
public class ProgressBarCellRender extends JProgressBar implements
        TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setValue((Integer)value);
        setStringPainted(true);
        return this;
    }

}
