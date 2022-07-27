import java.io.*;
import java.util.ArrayList;

public class Serialization {

    static void serialization() throws IOException {
        String savefile = "seridata.ser";
        FileOutputStream file = new FileOutputStream(savefile);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(Recipient.recipientArrayList);
        out.close();
        file.close();
    }

    static void deserialization() throws ClassNotFoundException, IOException {
        String savefile = "seridata.ser";
        FileInputStream file = new FileInputStream(savefile);
        ObjectInputStream in = new ObjectInputStream(file);
        Recipient.recipientArrayList = (ArrayList<Recipient>) in.readObject();
        in.close();
        file.close();
    }
}
