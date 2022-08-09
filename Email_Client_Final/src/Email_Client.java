// The email client has two types of recipients, official and personal. Some official recipients are close friends.
// Details of the recipient list should be stored in a text file.  An official recipient’s record in the text file has the following format: official: <name>, <email>,<designation>. A sample record for official recipients in the text file looks as follows:
// Official: nimal,nimal@gmail.com,ceo
// A sample record for official friends in the text file looks as follows (last value is the recipient's birthday):
// Office_friend: kamal,kamal@gmail.com,clerk,2000/12/12
// A sample record for personal recipients in the text file looks as follows (last value is the recipient's birthday):
// Personal: sunil,<nick-name>,sunil@gmail.com,2000/10/10
// The user should be given the option to update this text file, i.e. the user should be able to add a new recipient through command-line, and these details should be added to the text file. [file handling will be covered in the 11th week]
// When the email client is running, an object for each email recipient should be maintained in the application. For this, you will have to load the recipient details from the text file into the application. For each recipient having a birthday, a birthday greeting should be sent on the correct day. Official friends and personal recipients should be sent different messages (e.g. Wish you a Happy Birthday. <your name> for an office friend, and hugs and love on your birthday. <your name> for personal recipients). But all personal recipients receive the same message, and all office friends should receive the same message.  A list of recipients to whom a birthday greeting should be sent is maintained in the application, when it is running. When the email client is started, it should traverse this list, and send a greeting email to anyone having their birthday on that day.
// The system should be able to keep a count of the recipient objects. Use static members to keep this count.
// All the emails sent out by the email client should be saved into the hard disk, in the form of objects – object serialization can be used for this. The user should be able to retrieve information of all the mails sent on a particular day by using a command-line option. [object serialization will be covered in the 11th week]
// You only have to send out messages. No need to implement the logic to receive messages.
// Command-line options should be available for:

// Adding a new recipient
// Sending an email
// Printing out all the names of recipients who have their birthday set to current date
// Printing out details (subject and recipient) of all the emails sent on a date specified by user input
// Printing out the number of recipient objects in the application
// You may use the code given in this link to implement the basic functionality of the mail client (But it is perfectly ok to use a different code as well). In the given code, note that it imports the javax.mail package. This package is included in the javax.mail.jar, which can be downloaded from here.

// You are given marks for the

// Correct implementation of the mail sending functions (i.e. sending a birthday greeting, sending an email based on the instructions given through command-line, ability to serialize email objects,  etc).
// Correct use of OOP principles
// Use of coding best practices
// Use the given Email_Client.java file as the starting point.

// Save the recipient data into clientList.txt.

//===============================================================================================================================

import java.util.*;
import java.io.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// pre-built code bases

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

//===============================================================================================================================

class Color {
    static String RED = "\u001B[31m";
    static String GREEN = "\u001B[32m";
    static String YELLOW = "\u001B[33m";
    static String PURPLE = "\u001B[35m";
    static String CYAN = "\u001B[36m";
    static String WHITE = "\u001B[37m";
}

// ===============================================================================================================================

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
                    // Use a single input to get all the details of a recipient
                    // code to add a new recipient
                    // store details in clientList.txt file
                    System.out.println(
                            "give the detail following format\n  *  Official: nimal,nimal@gmail.com,ceo\n  *  Office_friend: kamal,newmail@gmail.com,clerk,2000/2/4\n  *  Personal: sunil,<nick name>,newmail@gmail.com,2000/2/6 ");
                    ReadWrite.write();
                    break;
                case 2:
                    // input format - email, subject, content
                    // code to send an email
                    System.out.println(
                            "Enter detail following format to send an email \t * input format - (email, subject, content) :");
                    String[] dataInputArray = console.readLine().split(",");
                    String emailAddres = dataInputArray[0];
                    String subject = dataInputArray[1];
                    String content = dataInputArray[2];
                    JavaMail.sendMail(emailAddres, subject, content); // send new mail for given data

                    break;
                case 3:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print recipients who have birthdays on the given date
                    System.out.println("Enter date following format\t * input format - yyyy/MM/dd (ex: 2018/09/17) :");
                    String givenDate = console.readLine();
                    Recipient.getRecipientBirthday(givenDate);

                    break;
                case 4:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
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
                                    + "\nSubject : " + emailDetails[1] + "\nContent : "
                                    + emailDetails[2].strip());
                            counter++;
                        }

                    }
                    if (counter == 1)
                        System.out.println(Color.RED + "No emails sent on " + getdate + Color.WHITE);
                    break;

                case 5:
                    // code to print the number of recipient objects in the application
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

// ===============================================================================================================================

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
                    } else {
                        String name = birthDayPerson.getName().strip().substring(0, 1).toUpperCase()
                                + birthDayPerson.getName().strip().substring(1) + ".";
                        System.out.println(Color.CYAN + "Birthday email already sent to " + name);
                    }

                }
            } else if (birthDayPerson instanceof OfficialFriend) {
                String[] birthDay = ((OfficialFriend) birthDayPerson).getBirthday().split("/");
                if (checkIsBirthdayToday(birthDay, getdate)) {
                    if (!sentmail.contains(birthDayPerson.getEmail())) {
                        JavaMail.sendMail(birthDayPerson.getEmail(),
                                "Wish you a Happy BirthDay",
                                "Shanu for an office friend and hugs and love on your birthday.");
                    } else {
                        String name = birthDayPerson.getName().strip().substring(0, 1).toUpperCase()
                                + birthDayPerson.getName().strip().substring(1) + ".";
                        System.out.println(Color.CYAN + "Birthday email already sent to " + name);
                    }

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

    public static void printDetail(String sendToEmailAddress, String subject, String content) throws IOException {
        String date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        String saveData = sendToEmailAddress + "," + subject + "," + content + "," + date + "\n";
        // FileWriter writer = new FileWriter("emai.txt", true);
        // writer.write(saveData);
        // writer.close();
        emaildata.add(saveData);
    }
}

// ===============================================================================================================================
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
                    String name = person.getName().strip().substring(0, 1).toUpperCase()
                            + person.getName().strip().substring(1);
                    System.out.println(personNumber + ")." + name);
                    personNumber++;
                }
            }
        }
        if (personNumber == 1)
            System.out.println(Color.RED + "Today has no birthdays." + Color.WHITE);
    }

}

// ===============================================================================================================================
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

// ===============================================================================================================================
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

// ===============================================================================================================================
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

// ===============================================================================================================================
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

// ===============================================================================================================================

class SerializationNew {

    // use singleton design pattern
    private static SerializationNew instance;

    // Encapsulate ReadWrite
    private SerializationNew() {
    }

    // Creating single object
    public static SerializationNew getInstance() throws ClassNotFoundException, IOException, FileNotFoundException {
        if (instance == null) {
            instance = new SerializationNew();
        }
        return instance;
    }

    static void serialization(ArrayList<String> data) throws ClassNotFoundException, IOException,
            FileNotFoundException, IOException {
        String savefile = "SerializedEmailData.ser";
        FileOutputStream file = new FileOutputStream(savefile);
        ObjectOutputStream out = new ObjectOutputStream(file);
        out.writeObject(data);
        out.close();
        file.close();
    }

    static ArrayList<String> deserialization() throws ClassNotFoundException, IOException, FileNotFoundException {
        try {
            String savefile = "SerializedEmailData.ser";
            FileInputStream file = new FileInputStream(savefile);
            ObjectInputStream in = new ObjectInputStream(file);
            JavaMail.emaildata = (ArrayList<String>) in.readObject();
            in.close();
            file.close();

        } catch (Exception FileNotFoundException) {
            FileOutputStream file = new FileOutputStream("SerializedEmailData.ser");
            file.close();
        }
        return JavaMail.emaildata;

    }
}

// ===============================================================================================================================

class ReadWrite {

    // use singleton design pattern
    private static ReadWrite instance;

    // Encapsulate ReadWrite
    private ReadWrite() {
    }

    // Creating single object
    public static ReadWrite getInstance() throws IOException {
        if (instance == null) {
            instance = new ReadWrite();
        }
        return instance;
    }

    static public void readPreviousData() throws IOException {
        try {
            File data = new File("clientList.txt");
            try (Scanner input = new Scanner(data)) {
                String line;
                while (input.hasNextLine()) {
                    line = input.nextLine();
                    createObject(line);
                }
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter("clientList.txt", true);
            writer.close();
        }
    }

    static public void write() {
        Console console = System.console();
        String data = console.readLine();
        try {
            FileWriter writer = new FileWriter("clientList.txt", true);
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

// ===============================================================================================================================

// end