package flashGetter.view.tasktable;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;

/**
 * @author decaywood
 * 
 * 2015年1月29日
 * 
 */
public class FileIconRenderer extends JLabel implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setIcon(ImageUtil.readIcon(Resources.deleteTask, 20));
        return this;
    }

}
