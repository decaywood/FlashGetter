package flashGetter.view.tasktable;

import java.awt.Component;
import java.text.DecimalFormat;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.apache.log4j.Logger;

/**
 * @author decaywood
 * 
 * 2015年1月29日
 * 
 */
public class ProgressBarCellRender extends JProgressBar implements WidthScaleCellRenderer {

    private static final Logger LOGGER = Logger.getLogger(ProgressBarCellRender.class);
    private DecimalFormat format = new DecimalFormat("###.## %"); 
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        LOGGER.info("row : "+row+"col : "+column +" -> " + value);
        double percentage = (double) value;
        setString(format.format(percentage));
        setValue((int) (percentage * 100));
        setStringPainted(true);
        return this;
    }

    @Override
    public void scaleWidth(TableColumn column) {
//        column.setPreferredWidth(TaskTable.ROW_HEIGHT * 25);
    }

}
