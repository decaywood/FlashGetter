package model;

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
import flashGetter.view.InfoEvent;
import flashGetter.view.EventHandler;
import flashGetter.view.EventDispatcher;

/**
 * @author decaywood
 * 
 * 2015年1月28日
 * 
 */
public class TaskTableModel extends DefaultTableModel implements EventHandler<TaskTableModel>{
    
    private int columnCount;
    private Class<? extends TaskTableModel> ID;
    
    public TaskTableModel(int number, Class<? extends TaskTableModel> id) {
        EventDispatcher.InnerClass.instance.register(this);
        ID = id;
        columnCount = number;
    }


    @Override
    public int getColumnCount() {
        return columnCount;
    }
 


    @Override
    public void invoke(InfoEvent event) {
        if(event.getTarget() != ID) return;
        int key = event.getOperationKey();
        
    }



    @Override
    public Class<TaskTableModel> getGroupClass() {
        return TaskTableModel.class;
    }
    
   
     
 
}
