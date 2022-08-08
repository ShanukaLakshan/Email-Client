import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

class Color {
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String YELLOW = "\u001B[33m";
    static String PURPLE = "\u001B[35m";
    static String CYAN = "\u001B[36m";
    static String WHITE = "\u001B[37m";
}

public class Email_Client {

    public static void main(String[] args) throws Exception, FileNotFoundException {

        JavaMail.emaildata = SerializationNew.deserialization();

        ReadWrite.readPreviousData();

        JavaMail.sendBirthDayWishes();

        loop: while (true) {
            Console console = System.console();
            System.out.println(Color.PURPLE + "\nEnter option type: \n" + Color.WHITE
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application\n"
                    + Color.RED + "6 - Exit\n" + Color.WHITE);
            int option = Integer.parseInt(console.readLine());
            switch (option) {
                case 1:
                    System.out.println(
                            "give the detail following format\n  *  Official: nimal,nimal@gmail.com,ceo\n  *  Office_friend: kamal,newmail@gmail.com,clerk,2000/2/4\n  *  Personal: sunil,<nick name>,newmail@gmail.com,2000/2/6 ");
                    ReadWrite.write();
                    break;
                case 2:
                    System.out.println(
                            "Enter detail following format to send an email \t * input format - (email, subject, content) :");
                    String[] dataInputArray = console.readLine().split(",");
                    String emailAddres = dataInputArray[0];
                    String subject = dataInputArray[1];
                    String content = dataInputArray[2];
                    JavaMail.sendMail(emailAddres, subject, content); // send new mail for given data

                    break;
                case 3:
                    System.out.println("Enter date following format\t * input format - yyyy/MM/dd (ex: 2018/09/17) :");
                    String givenDate = console.readLine();
                    Recipient.getRecipientBirthday(givenDate);

                    break;
                case 4:
                    System.out.println("Enter date following format\t * input format - yyyy/MM/dd (ex: 2018/09/17) :");
                    String getdate = console.readLine();
                    int counter = 1;
                    for (int i = 0; i < JavaMail.emaildata.size(); i++) {
                        String[] emailDetails = JavaMail.emaildata.get(i).strip().split(",");
                        if (emailDetails[emailDetails.length - 1].equalsIgnoreCase(getdate)) {
                            System.out.println(counter + ")" + Color.GREEN +
                                    " ---------------------------------------------------------------------------------------------------\n"
                                    + Color.WHITE + "To\t: "
                                    + emailDetails[0]
                                    + "\nSublect : " + emailDetails[1] + "\nContent : "
                                    + emailDetails[2].strip());
                            counter++;
                        }

                    }
                    break;

                case 5:
                    System.out.println(
                            "Client List include number of " + Color.GREEN + Recipient.numberOfrecipient + Color.WHITE
                                    + " recipient objects.");

                    break;
                case 6:
                    // SerializationNew.serialization(JavaMail.emaildata);
                    System.out.println(Color.RED + "Program End!" + Color.WHITE);
                    break loop;

            }
        }
    }
}

class JavaMail {

    public static ArrayList<String> emaildata = new ArrayList<String>();
    public static ArrayList<String> sentmail = new ArrayList<String>();

    public static void sendMail(String sendToEmailAddress, String subject, String content) throws Exception {

        System.out.println(Color.YELLOW + "Sending email !" + Color.WHITE);
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        String myEmail = "shanukalakshan4567@gmail.com";
        // String password = "sbylqcjerwdcewub";
        String password = "imisugzmuxpzcqnv";

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }
        });

        Message message = prepareMessage(session, myEmail, sendToEmailAddress, subject, content);
        Transport.send(message);
        printDetail(sendToEmailAddress, subject, content);
        SerializationNew.serialization(JavaMail.emaildata);
        System.out.println(Color.GREEN + "Mail Successfuly Sent!" + Color.WHITE);
    }

    private static Message prepareMessage(Session session, String myEmail, String sendToEmailAddress, String subject,
            String content) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(sendToEmailAddress));
            message.setSubject(subject);
            message.setText(content);
            return message;
        } catch (Exception e) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static void checkAndSend() throws ClassNotFoundException, IOException, FileNotFoundException {
        String getdate = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        for (int i = 0; i < emaildata.size(); i++) {
            String[] emailDetails = JavaMail.emaildata.get(i).strip().split(",");
            if (emailDetails[emailDetails.length - 1].equalsIgnoreCase(getdate) && emailDetails[1]
                    .equalsIgnoreCase("Wish you a Happy BirthDay")) {

                sentmail.add(JavaMail.emaildata.get(i).strip().split(",")[0]);
            }
        }
    }

    public static void sendBirthDayWishes() throws Exception {
        JavaMail.checkAndSend();
        String[] getdate = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()).split("/");

        for (Recipient birthDayPerson : Recipient.recipientArrayList) {
            if (birthDayPerson instanceof Personal) {
                String[] birthDay = ((Personal) birthDayPerson).getBirthday().split("/");
                if (checkIsBirthdayToday(birthDay, getdate)) {
                    if (!sentmail.contains(birthDayPerson.getEmail())) {
                        JavaMail.sendMail(birthDayPerson.getEmail(),
                                "Wish you a Happy BirthDay",
                                "hugs and love on your birthday.Shanu for personal recipients.");
                    }

                }
            } else if (birthDayPerson instanceof OfficialFriend) {
                String[] birthDay = ((OfficialFriend) birthDayPerson).getBirthday().split("/");
                if (checkIsBirthdayToday(birthDay, getdate)) {
                    if (!sentmail.contains(birthDayPerson.getEmail())) {
                        JavaMail.sendMail(birthDayPerson.getEmail(),
                                "Wish you a Happy BirthDay",
                                "Shanu for an office friend and hugs and love on your birthday.");
                    } else
                        System.out.println(Color.CYAN + "birthday email already sent to " + birthDayPerson.getName());
                }
            }
        }

    }

    public static boolean checkIsBirthdayToday(String[] CheckDate, String[] CurrentDate) {
        int currentDay = Integer.parseInt(CurrentDate[2]);
        int currentMonth = Integer.parseInt(CurrentDate[1]);
        int day = Integer.parseInt(CheckDate[2]);
        int month = Integer.parseInt(CheckDate[1]);
        return (day == currentDay) && (month == currentMonth);

    }

    public static String printDetail(String sendToEmailAddress, String subject, String content) throws IOException {
        String date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        String saveData = sendToEmailAddress + "," + subject + "," + content + "," + date + "\n";
        // FileWriter writer = new FileWriter("emai.txt", true);
        // writer.write(saveData);
        // writer.close();
        emaildata.add(saveData);
        return saveData;
    }
}

// use Abstraction
abstract class Recipient {

    public static ArrayList<Recipient> recipientArrayList = new ArrayList<Recipient>();
    private String name;
    private String email;
    static int numberOfrecipient;

    public Recipient(String name, String email) {
        this.name = name;
        this.email = email;
        numberOfrecipient++;
    }

    // use Encapsulation
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public static int getNumberOfrecipient() {
        return numberOfrecipient;
    }

    static void getRecipientBirthday(String givenday) {
        int personNumber = 1;
        for (Recipient person : recipientArrayList) {
            if ((person instanceof CloseFriend)) {
                String[] birthDay = ((CloseFriend) person).getBirthday().split("/");
                String[] enterd_date = givenday.split("/");
                if (JavaMail.checkIsBirthdayToday(enterd_date, birthDay)) {
                    System.out.println(personNumber + "). " + person.getName());
                    personNumber++;
                }
            }
        }
    }

}

// use Inheritance
class Official extends Recipient {

    private String designation;

    public Official(String name, String email, String designation) {
        super(name, email);
        this.designation = designation;
    }

    // use Encapsulation
    public String getDesignation() {
        return designation;
    }
}

// use Inheritance
class CloseFriend extends Recipient {

    private String birthDay;

    public CloseFriend(String name, String email, String birthDay) {
        super(name, email);
        this.birthDay = birthDay;
    }

    // use Encapsulation
    public String getBirthday() {
        return birthDay;
    }

}

class Personal extends CloseFriend {
    // atrributes name,nickname,email,birthday
    private String nickName;

    public Personal(String name, String nickName, String email, String birthDay) {
        super(name, email, birthDay);
        this.nickName = nickName;
    }

    // use Encapsulations
    public String getNickName() {
        return nickName;
    }

}

// use Inheritance
class OfficialFriend extends CloseFriend {
    // attributes name,email,designation,birthday
    private String designation;

    public OfficialFriend(String name, String email, String designation, String birthDay) {
        super(name, email, birthDay);
        this.designation = designation;
    }

    // use Encapsulation
    public String getDesignation() {
        return designation;
    }

}

class SerializationNew {

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
        try {
            String savefile = "seridata.ser";
            FileInputStream file = new FileInputStream(savefile);
            ObjectInputStream in = new ObjectInputStream(file);
            JavaMail.emaildata = (ArrayList<String>) in.readObject();
            in.close();
            file.close();

        } catch (Exception FileNotFoundException) {
            FileOutputStream file = new FileOutputStream("seridata.ser");
            file.close();
        }
        return JavaMail.emaildata;

    }
}

abstract class ReadWrite {

    static public void readPreviousData() throws IOException {
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
            FileWriter writer = new FileWriter("t.txt", true);
            writer.close();
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