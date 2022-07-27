import java.io.Console;
import java.util.*;

public class Email_Client {

    public static void main(String[] args) throws Exception {

        loop: while (true) {
            Console console = System.console();
            System.out.println("Enter option type: \n"
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application\n"
                    + "6 - Exit");
            int option = Integer.parseInt(console.readLine());
            switch (option) {
                case 1:
                    // Official: nimal,nimal@gmail.com,ceo
                    // Office_friend: kamal,kasunssckinfo@gmail.com,clerk,2000/2/4
                    // Personal: sunil,nickname,kasunssckinfo@gmail.com,2000/2/6
                    System.out.println(
                            "give the detail following format\n  *  Official: nimal,nimal@gmail.com,ceo\n  *  Office_friend: kamal,kasunssckinfo@gmail.com,clerk,2000/2/4\n  *  Personal: sunil,<nick name>,kasunssckinfo@gmail.com,2000/2/6 ");
                    ReadWrite.write();
                    break;
                case 2:
                    // shanukalakshan4567@gmail.com, Testing1 , This is testing email
                    // input format - email, subject, content
                    // code to send an email
                    System.out.println(
                            "Enter detail following format to send an email :\n * input format - (email, subject, content) :");

                    String[] dataInputArray = console.readLine().split(",");
                    String emailAddres = dataInputArray[0];
                    String subject = dataInputArray[1];
                    String content = dataInputArray[2];
                    JavaMail.sendMail(emailAddres, subject, content); // send a mail for given data

                    break;
                case 3:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print recipients who have birthdays on the given date
                    System.out.println("Enter date following format\n * input format - yyyy/MM/dd (ex: 2018/09/17) :");
                    String givenDate = console.readLine();
                    Recipient.getRecipientBirthday(givenDate);
                    JavaMail.sendBirthDayWishes();

                    break;
                case 4:
                    // input format - yyyy/MM/dd (ex: 2018/09/17)
                    // code to print the details of all the emails sent on the input date
                    System.out.println("Enter date following format\n * input format - yyyy/MM/dd (ex: 2018/09/17) :");
                    String getdate = console.readLine();
                    ReadWrite.read(getdate);

                    break;

                case 5:
                    // code to print the number of recipient objects in the application
                    System.out.println(
                            "Client List include number of " + Recipient.numberOfrecipient + " recipient objects.");
                    break;
                case 611:
                    // end program
                    Serialization.serialization();
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