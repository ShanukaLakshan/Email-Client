import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Email_Client {

    public static void main(String[] args) throws Exception {

        ReadWrite.readPreviousData();
        // JavaMail.sendBirthDayWishes();

        loop: while (true) {
            Console console = System.console();
            System.out.println("\nEnter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application\n"
                    + "6 - Exit\n");
            int option = Integer.parseInt(console.readLine());
            switch (option) {
                case 1:
                    System.out.println(
                            "give the detail following format\n  *  Official: nimal,nimal@gmail.com,ceo\n  *  Office_friend: kamal,kasunssckinfo@gmail.com,clerk,2000/2/4\n  *  Personal: sunil,<nick name>,kasunssckinfo@gmail.com,2000/2/6 ");
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

                    SerializationNew.deserialization(JavaMail.emaildata);
                    for (int i = 0; i < JavaMail.emaildata.size(); i++) {
                        String[] emailDetails = JavaMail.emaildata.get(i).strip().split(",");
                        if (emailDetails[emailDetails.length - 1].equals(getdate)) {
                            System.out.println(JavaMail.emaildata.get(i).strip());
                        }
                    }
                    break;

                case 5:
                    System.out.println(
                            "Client List include number of " + Recipient.numberOfrecipient + " recipient objects.");
                    break;
                case 611:
                    SerializationNew.serialization(JavaMail.emaildata);
                    System.out.println("Program End!");
                    break loop;
            }
        }
    }
}

// Personal: sunil,sunna,kasunssckinfo@gmail.com,1998/2/4
// Office_friend: amal,kasunssckinfo@gmail.com,clerk,1900/2/6
// Official:nimal,nimal @gmail.com,ceo
// Office_friend: cahal,ssckinfo@gmail.com,clerk,1995/2/4
// Personal: Shanuka,shanu,shanukalakshan456@gmail.com,1998/7/27

// JavaMail.sendMail("sandundissanayake2000124@gmail.com", "Awada", "Ade Ube
// Ange Hati Balapanko!");
// JavaMail.sendMail("chalanganaOnline@gmail.com", "Awada", "Ado uber podi eka
// bng!");