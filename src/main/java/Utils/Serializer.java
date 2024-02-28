package Utils;

import Structures.Page;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
    public static void serialize(Page page, String fileName) {
        try {
            //you may also write this verbosely as
            // FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            var fileOutputStream = new FileOutputStream(fileName);

            var objOutputStream = new ObjectOutputStream(fileOutputStream);

            objOutputStream.writeObject(page);
            //we don't want a memory leak if we can avoid it
            fileOutputStream.close();
            objOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Page deserialize(String fileName) {
        Page page = null;

        try {
            //could be written as
            // FileInputStream fileInputStream = new
            //FileInputStream(fileName);
            var fileInputStream = new FileInputStream(fileName);
            var objectInputStream = new ObjectInputStream(fileInputStream);

            //read the binary file
            page = (Page) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return page;
    }

}
