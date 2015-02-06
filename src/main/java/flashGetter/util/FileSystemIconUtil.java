package flashGetter.util;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;

import org.apache.log4j.Logger;

import sun.awt.shell.ShellFolder;

/**
 * @author decaywood
 * 
 * 2015年2月6日
 * 
 */
@SuppressWarnings("restriction")
public class FileSystemIconUtil {
    
    private static final Logger logger = Logger.getLogger(FileSystemIconUtil.class);
    
    
    public static ImageIcon readSystemBigIcon(String path){
        return readSystemBigIcon(new File(path));
    }
            
    public static ImageIcon readSystemBigIcon(File file){
        ImageIcon bigIcon = null;
        
        try {
            ShellFolder shellFolder = ShellFolder.getShellFolder(file);
            bigIcon = new ImageIcon(shellFolder.getIcon(true));
        } catch (FileNotFoundException e) {
            logger.info("File Not Found!", e); 
        }
        
        return bigIcon;
    }
    
    
    
    public static ImageIcon readSystemSmallIcon(String path){
        
        return readSystemSmallIcon(new File(path));
        
    }
    
    public static ImageIcon readSystemSmallIcon(File file){
        
        FileSystemView view = FileSystemView.getFileSystemView();
        ImageIcon smallIcon = (ImageIcon) view.getSystemIcon(file);
        return smallIcon;
        
    }

}
