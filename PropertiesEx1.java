import java.io.*;
import java.util.Properties;

//propertiesEx 

public class PropertiesEx1 {
    public static void main(String[] args) {
        Properties prop = new Properties();

        prop.setProperty("timeout", "30");
        prop.setProperty("language","kr");
        prop.setProperty("size", "10");

        System.out.println(prop); // 출력
        prop.list(System.out);    // 출력

        //  xml 출력 관련~
        try{
            prop.storeToXML(new FileOutputStream("file.xml"), "comment");
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
}
