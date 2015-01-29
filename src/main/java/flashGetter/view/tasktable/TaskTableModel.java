package flashGetter.view.tasktable;

import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.log4j.Logger;

import flashGetter.Resources;
import flashGetter.view.ViewEvent;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class TaskTableModel extends DefaultTableModel {
    
    private int columnCount;
    
    public TaskTableModel(int number) {
        columnCount = number;
    }

   

    @Override
    public int getColumnCount() {
        return columnCount;
    }
//
//    @Override
//    public String getColumnName(int columnIndex) {
//        return null;
//    }
//
//    @Override
//    public Class<?> getColumnClass(int columnIndex) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public boolean isCellEditable(int rowIndex, int columnIndex) {
//        // TODO Auto-generated method stub
//        return false;
//    }
//
//    @Override
//    public Object getValueAt(int rowIndex, int columnIndex) {
//        // TODO Auto-generated method stub
//        return null;
//    }
//
//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public void addTableModelListener(TableModelListener l) {
//        // TODO Auto-generated method stub
//        
//    }
//
//    @Override
//    public void removeTableModelListener(TableModelListener l) {
//        // TODO Auto-generated method stub
//        
//    }
    
   
     
 
}
