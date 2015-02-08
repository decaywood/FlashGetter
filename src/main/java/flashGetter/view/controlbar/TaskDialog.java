package flashGetter.view.controlbar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import flashGetter.Resources;
import flashGetter.downloader.DownloadManager;
import flashGetter.view.EventDispatcher;
import flashGetter.view.InfoEvent;
import flashGetter.view.MainFrame;

/**
 * @author decaywood
 * 
 * 2015年2月4日
 * 
 */
public class TaskDialog extends JDialog {
    
    private JTextArea textArea;
    private JTextField jTextField;
    
    public TaskDialog() {
        
        setLocation(MainFrame.mainFrame.getX(), MainFrame.mainFrame.getY());
        setSize(650, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setTitle("New Task");
        setVisible(true); 
        
        Font font = Resources.getFont();
        
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        JButton chooseDirButton = new JButton("Choose Dir");
        chooseDirButton.setFont(font);
        chooseDirButton.addActionListener(e -> {
            int option = chooser.showSaveDialog(null);
            if(option == JFileChooser.APPROVE_OPTION)
                jTextField.setText(chooser.getSelectedFile().getPath());
        });
       
        jTextField = new JTextField();
        jTextField.setFont(font);
       
        
        JPanel dirPanel = new JPanel();
        dirPanel.setLayout(new BorderLayout());
        dirPanel.add(jTextField, BorderLayout.CENTER);
        dirPanel.add(chooseDirButton, BorderLayout.EAST);
        dirPanel.add(new JPanel(), BorderLayout.SOUTH);
        dirPanel.add(new JPanel(), BorderLayout.NORTH);
        add(dirPanel, BorderLayout.NORTH);
        
        textArea = new JTextArea();
        textArea.setFont(font);
        textArea.setText("Resources Link");
        textArea.setEditable(true);
        textArea.setLineWrap(true);
        textArea.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                 textArea.setText(null);
            }
        });
        
        add(textArea, BorderLayout.CENTER);
        
        JPanel buttons = new JPanel(new FlowLayout());
        JButton buttonOK = new JButton("Confirm");
        buttonOK.setFont(font);
        buttonOK.addActionListener(l -> {
            String downloadAddr = textArea.getText();
            String savePath = jTextField.getText();
            InfoEvent event = new InfoEvent()
            .setTarget(DownloadManager.class)
            .setInfo(downloadAddr, savePath)
            .setOperationKey(DownloadManager.CREATE_TASK);
            EventDispatcher.InnerClass.instance.fireEvent(event);
            dispose();
        });
        
        JButton buttonCancel = new JButton("Cancel");
        buttonCancel.setFont(font);
        buttonCancel.addActionListener(l -> {
            dispose();
        });
        
        buttons.add(buttonOK);
        buttons.add(buttonCancel);
        
        add(buttons, BorderLayout.SOUTH);
        
    }

}
