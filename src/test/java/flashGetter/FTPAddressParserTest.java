package flashGetter;

import flashGetter.util.FTPAddressParser;

/**
 * @author decaywood
 * 
 * 2015年2月7日
 * 
 */
public class FTPAddressParserTest {
    
    public static void main(String[] args) {
        
        System.out.println(FTPAddressParser.parseAdress("ftp://192.168.59.1"));
        System.out.println(FTPAddressParser.parseAdress("ftp://192.168.59.1:23"));
        System.out.println(FTPAddressParser.parseAdress("ftp://192.168.59.1:23/aa"));
        System.out.println(FTPAddressParser.parseAdress("ftp://192.168.59.1/aa"));
        System.out.println(FTPAddressParser.parseAdress("ftp://192.168.59.1:23/aa/bb/cc"));
        System.out.println(FTPAddressParser.parseAdress("ftp://192.168.59.1/aa/bb/cc"));
        System.out.println(FTPAddressParser.parseAdress("ftp://userName:passwd@192.168.59.1"));
        System.out.println(FTPAddressParser.parseAdress("ftp://userName:passwd@192.168.59.1:23"));
        System.out.println(FTPAddressParser.parseAdress("ftp://userName:passwd@192.168.59.1:23/aa"));
        System.out.println(FTPAddressParser.parseAdress("ftp://userName:passwd@192.168.59.1/aa"));
        System.out.println(FTPAddressParser.parseAdress("ftp://userName:passwd@192.168.59.1:23/aa/cc/bb/cc"));
        System.out.println(FTPAddressParser.parseAdress("ftp://userName:passwd@192.168.59.1/aa/bb/cc"));
        
    }

}
