package Utils;

import java.io.File;
import java.io.IOException;

public class metaFile {

    public static String metaPath = "./DB/metaFile.txt";
    public static void generateMetaDataFile() {
        File file = new File("./DB/metadata.csv");
        if (file.exists()) {
            return;
        } else {
            try{
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
