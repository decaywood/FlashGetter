package flashGetter.view.tasktable;

import java.awt.Color;
import java.awt.Component;
import java.util.EventObject;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * @author decaywood
 * 
 * 2015年1月29日
 * 
 */
public class StringCellRenderer extends JLabel implements TableCellRenderer {
    
    private UnitParser parser;
    
    public StringCellRenderer(UnitParser parser) {
        this.parser = parser;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setForeground(Color.LIGHT_GRAY);
        setText(parser.parseUnit((String)value));
        return this;
    }

    
}
