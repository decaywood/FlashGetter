package flashGetter.view.tasktable;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import flashGetter.Resources;
import flashGetter.view.ViewEvent;
import flashGetter.view.ViewEventDispatcher;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public abstract class TaskTable extends JTable implements MouseListener{
    
    protected TableHeadRenderer typeLabel;
    protected TableHeadRenderer nameLabel;
    protected TableHeadRenderer sizeLabel;
    protected TableHeadRenderer progressLabel;
    protected TableHeadRenderer remainTimeLabel;
    protected TableHeadRenderer speedLabel;
    
    protected FileIconRenderer fileIconRenderer;
    protected StringCellRenderer nameCellRenderer;
    protected StringCellRenderer sizeCellRenderer;
    protected ProgressBarCellRender progressBarCellRender;
    protected StringCellRenderer timeCellRenderer;
    protected StringCellRenderer speedCellRenderer;
    
    public TaskTable() {
        
        /**
         * headIcon
         */
        typeLabel = new TableHeadRenderer("Flie Type");
        nameLabel = new TableHeadRenderer("Flie Name");
        sizeLabel = new TableHeadRenderer("Flie Size");
        progressLabel = new TableHeadRenderer("Progress");
        remainTimeLabel = new TableHeadRenderer("Remain Time");
        speedLabel = new TableHeadRenderer("Download Speed");
        
        /**
         * cellRenderer per column
         */
        fileIconRenderer = new FileIconRenderer();
        nameCellRenderer = new StringCellRenderer(data -> "name not be implemented yet");
        sizeCellRenderer = new StringCellRenderer(data -> "size not be implemented yet");
        progressBarCellRender = new ProgressBarCellRender();
        timeCellRenderer = new StringCellRenderer(data -> "time not be implemented yet");
        speedCellRenderer = new StringCellRenderer(data -> "speed not be implemented yet");
       
       
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
      
}
