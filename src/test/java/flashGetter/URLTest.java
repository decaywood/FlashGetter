package flashGetter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.input.AutoCloseInputStream;
import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import flashGetter.downloader.task.DownloadingTask;
import flashGetter.downloader.task.FTPTaskThread;
import flashGetter.downloader.task.TaskInfo;
import flashGetter.util.FTPAddressParser;
import flashGetter.util.FTPAddressParser.FTPInfo;

/**
 * @author decaywood
 * 
 *         2015年2月6日
 * 
 */
public class URLTest {

    

    public static void main(String[] args) throws IOException {
        String urlpath = "ftp://flashgetter:123456@192.168.59.1:21/Scala.pdf";
        DownloadingTask task = new TaskInfo(123, urlpath, "E:\\test\\result\\");
        FTPTaskThread thread = new FTPTaskThread(task, null);
        thread.run();
        File file = new File("E:\\test\\Scala.pdf");
        long checksum = FileUtils.checksumCRC32(file);
        File file2 = new File("E:\\test\\result\\Scala.pdf");
        long checksum2 = FileUtils.checksumCRC32(file2);
        System.out.println(checksum +"  "+checksum2);
    }

}
