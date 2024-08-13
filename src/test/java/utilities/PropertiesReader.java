package utilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {
	
	 
    public  Properties initProp(){
 
    	Properties prop=new Properties();
        try {
        	System.out.println(System.getProperty("user.dir"));
            FileInputStream fileLocation=new FileInputStream("src/test/resources/config.properties");
 
            prop.load(fileLocation);
        } catch (FileNotFoundException e) {
           e.printStackTrace();
           System.out.println(e);
 
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
 
        return  prop;
    }
 
}
