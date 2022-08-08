import java.io.*;
import java.util.ArrayList;

public class SerializationNew {

    static void serialization(ArrayList<String> data)
            throws IOException, ClassNotFoundException, FileNotFoundException {
        String savefile = "seridata.ser";
        FileOutputStream file = new FileOutputStream(savefile);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(data);
        out.close();
        file.close();
    }

    static ArrayList<String> deserialization() throws ClassNotFoundException, IOException, FileNotFoundException {
        String savefile = "seridata.ser";
        FileInputStream file = new FileInputStream(savefile);
        ObjectInputStream in = new ObjectInputStream(file);
        JavaMail.emaildata = (ArrayList<String>) in.readObject();
        in.close();
        file.close();
        return JavaMail.emaildata;

    }
}
