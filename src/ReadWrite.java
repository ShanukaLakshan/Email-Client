import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class ReadWrite {

    // static public void read(String getdate) {
    // try {
    // File data = new File("E:\\Sem 2\\Moodle\\Program Construction\\OOP\\OOP
    // Project\\Email-Client\\emai.txt");

    // Scanner input = new Scanner(data);
    // String line;

    // while (input.hasNextLine()) {
    // line = input.nextLine();
    // String[] getNewDate = line.split(",");
    // if (getNewDate[3].equals(getdate)) {
    // System.out.println(line);
    // }
    // // createObject(line);
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // }

    static public void readPreviousData() {
        try {
            File data = new File("t.txt");
            try (Scanner input = new Scanner(data)) {
                String line;
                while (input.hasNextLine()) {
                    line = input.nextLine();
                    createObject(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static public void write() {
        Console console = System.console();
        String data = console.readLine();
        try {
            FileWriter writer = new FileWriter("t.txt", true);
            writer.write(data + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        createObject(data); // create recipient object
    }

    static private void createObject(String data) {
        String[] dataSet = data.split(",");
        String name = (dataSet[0].split(":"))[1].strip();

        String isWho = (dataSet[0].split(":"))[0];

        switch (isWho) {
            case "Official":
                Recipient.recipientArrayList.add(new Official(name, dataSet[1], dataSet[2]));
                break;

            case "Office_friend":
                Recipient.recipientArrayList.add(new OfficialFriend(name, dataSet[1], dataSet[2], dataSet[3]));
                break;

            case "Personal":
                Recipient.recipientArrayList.add(new Personal(name, dataSet[1], dataSet[2], dataSet[3]));
                break;

            default:
                System.out.println("Invalid Input !");
        }
    }

}
