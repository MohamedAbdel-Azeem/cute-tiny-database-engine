package Utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configurator {

    public static Properties readFile(){
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/DBMain.DBApp.config")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static int getMaxPageSize(){
        Properties properties = readFile();
        return Integer.parseInt(properties.getProperty("MaximumRowsCountinPage"));
    }


}
