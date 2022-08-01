import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {

    public static ArrayList<String> emaildata = new ArrayList<String>();

    public static void sendMail(String sendToEmailAddress, String subject, String content) throws Exception {

        System.out.println("Preparing email !");
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        // prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
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

        System.out.println("Mail Successfuly Sent!");
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

    public static void sendBirthDayWishes() throws Exception {
        String[] getdate = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()).split("/");

        for (Recipient a : Recipient.recipientArrayList) {

            if (a instanceof Personal) {
                String[] birthDay = ((Personal) a).getBirthday().split("/");

                if (checkIsBirthdayToday(birthDay, getdate)) {
                    JavaMail.sendMail(a.getEmail(),
                            "Wish you a Happy BirthDay",
                            "hugs and love on your birthday.Shanu for personal recipients.");
                }

            } else if (a instanceof OfficialFriend) {
                String[] birthDay = ((OfficialFriend) a).getBirthday().split("/");

                if (checkIsBirthdayToday(birthDay, getdate)) {
                    JavaMail.sendMail(a.getEmail(),
                            "Wish you a Happy BirthDay",
                            "Shanu for an office friend and hugs and love on your birthday.");
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

    // public static boolean checkIsBirthdayToday(int day, int month, int
    // currentDay, int currentMonth) {
    // return (day == currentDay) && (month == currentMonth);
    // }

    public static String printDetail(String sendToEmailAddress, String subject, String content) throws IOException {

        String date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        String saveData = sendToEmailAddress + "," + subject + "," + content + "," + date;
        // FileWriter writer = new FileWriter("emai.txt", true); // need to remove
        // writer.write(saveData + "\n");
        // writer.close();
        emaildata.add(saveData);
        return saveData;
    }

}
