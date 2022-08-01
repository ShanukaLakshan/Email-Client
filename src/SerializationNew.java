import java.io.*;
import java.util.ArrayList;

public class SerializationNew {

    static void serialization(ArrayList<String> data) throws IOException {
        String savefile = "seridata.ser";
        FileOutputStream file = new FileOutputStream(savefile, true);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(data);
        out.close();
        file.close();
    }

    static void deserialization(ArrayList<String> data) throws ClassNotFoundException, IOException {
        String savefile = "seridata.ser";
        FileInputStream file = new FileInputStream(savefile);
        ObjectInputStream in = new ObjectInputStream(file);
        JavaMail.emaildata = (ArrayList<String>) in.readObject();
        in.close();
        file.close();
    }
}

// ArrayList<String> data = new ArrayList<String>();
// data.add("One");
// data.add("Two");

// data.add("Three");
// Serialization.serialization(data);

// Serialization.deserialization(data);

// for (int i = 0; i < data.size(); i++)
// System.out.print(data.get(i) + " ");
