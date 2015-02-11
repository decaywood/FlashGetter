package flashGetter.view.tasktable;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

/**
 * @author decaywood
 * 
 * 2015年1月29日
 * 
 */
public class StringCellRenderer extends JLabel implements WidthScaleCellRenderer {
    
    private static final Logger LOGGER = Logger.getLogger(StringCellRenderer.class);
    
    private UnitParser parser;
    private int stringLength;
    
    public StringCellRenderer(int stringLength, UnitParser parser) {
        this.stringLength = stringLength;
        this.parser = parser;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        LOGGER.info("row : "+row+"col : "+column +" -> " + value);
        setForeground(Color.LIGHT_GRAY);
        setText(parser.parseUnit((String)value));
        return this;
    }

    @Override
    public void scaleWidth(TableColumn column) {
        if(stringLength == Integer.MAX_VALUE) return;
        column.setMaxWidth(stringLength * 15);
        column.setMinWidth(stringLength * 10);
    }

    
}
