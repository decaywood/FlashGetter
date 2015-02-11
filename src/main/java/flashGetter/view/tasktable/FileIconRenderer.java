package flashGetter.view.tasktable;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

import flashGetter.Resources;
import flashGetter.util.ImageUtil;

/**
 * @author decaywood
 * 
 * 2015年1月29日
 * 
 */
public class FileIconRenderer extends JLabel implements WidthScaleCellRenderer {

    private static final Logger LOGGER = Logger.getLogger(FileIconRenderer.class);
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        LOGGER.info("row : "+row+"col : "+column +" -> " + value);
        setIcon((ImageIcon)value);
        return this;
    }

    @Override
    public void scaleWidth(TableColumn column) {
        column.setMaxWidth(TaskTable.ROW_HEIGHT * 2);
    }

}
