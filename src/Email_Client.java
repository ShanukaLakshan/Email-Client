import java.io.Console;
import java.io.FileNotFoundException;

public class Email_Client {

    public static void main(String[] args) throws Exception, FileNotFoundException {

        String BLACK = "\u001B[30m", RED = "\u001B[31m";
        String GREEN = "\u001B[32m";
        String YELLOW = "\u001B[33m";
        String BLUE = "\u001B[343m";
        String PURPLE = "\u001B[35m";
        String CYAN = "\u001B[36m";
        String WHITE = "\u001B[37m";

        JavaMail.emaildata = SerializationNew.deserialization();
        ReadWrite.readPreviousData();

        // JavaMail.sendBirthDayWishes();
        JavaMail.checkAndSend();

        loop: while (true) {
            Console console = System.console();
            System.out.println(PURPLE + "\nEnter option type: \n" + WHITE
                    + "1 - Adding a new recipient\n"
                    + "2 - Sending an email\n"
                    + "3 - Printing out all the recipients who have birthdays\n"
                    + "4 - Printing out details of all the emails sent\n"
                    + "5 - Printing out the number of recipient objects in the application\n"
                    + RED + "6 - Exit\n" + WHITE);
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
                    // String getdate = "2022/08/02";
                    int counter = 1;
                    for (int i = 0; i < JavaMail.emaildata.size(); i++) {
                        String[] emailDetails = JavaMail.emaildata.get(i).strip().split(",");
                        if (emailDetails[emailDetails.length - 1].equalsIgnoreCase(getdate)) {
                            System.out.println(counter +
                                    "). ---------------------------------------------------------------------------------------------------\nTo\t: "
                                    + emailDetails[0]
                                    + "\nSublect : " + emailDetails[1] + "\nContent : "
                                    + emailDetails[2].strip());
                            counter++;
                        }

                    }
                    break;

                case 5:
                    System.out.println(
                            "Client List include number of " + GREEN + Recipient.numberOfrecipient + WHITE
                                    + " recipient objects.");

                    break;
                case 6:
                    // SerializationNew.serialization(JavaMail.emaildata);
                    System.out.println(RED + "Program End!" + WHITE);
                    break loop;

            }
        }
    }
}
