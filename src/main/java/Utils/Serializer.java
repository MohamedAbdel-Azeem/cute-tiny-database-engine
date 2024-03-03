package Utils;

import Structures.Page;

import java.io.*;

public class Serializer {
    public static void serialize(Serializable object, String fileName) {
        try {
            //you may also write this verbosely as
            // FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            var fileOutputStream = new FileOutputStream("./DB/"+fileName+".class");

            var objOutputStream = new ObjectOutputStream(fileOutputStream);

            objOutputStream.writeObject(object);
            //we don't want a memory leak if we can avoid it
            fileOutputStream.close();
            objOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Object deserialize(String fileName) {
        Object object = null;

        try {
            //could be written as
            // FileInputStream fileInputStream = new
            //FileInputStream(fileName);
            var fileInputStream = new FileInputStream("./DB/"+fileName+".class");
            var objectInputStream = new ObjectInputStream(fileInputStream);

            //read the binary file
            object = objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return object;
    }

}
