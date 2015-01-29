package flashGetter.view.tasktable;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import flashGetter.Resources;

/**
 * @author decaywood
 * 
 * 2015年1月29日
 * 
 */
public class TableHeadRenderer extends JLabel implements TableCellRenderer {

    public TableHeadRenderer(String name) {
        
        setFont(Resources.getFont());
        setText(name);
        setForeground(Color.WHITE);
        
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
        boolean isSelected, boolean hasFocus, int row, int column) {
        return this;
    }
    
    
    public TableHeadRenderer pressLabel(){
        setForeground(Color.YELLOW);
        return this;
    }
    
    public void releaseLabel(){
        setForeground(Color.WHITE);
    }
    
}
