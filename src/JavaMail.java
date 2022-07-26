import java.io.FileNotFoundException;
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
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {

    public static ArrayList<String> emaildata = new ArrayList<String>();
    public static ArrayList<String> sentmail = new ArrayList<String>();

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

    public static void checkAndSend() throws ClassNotFoundException, IOException, FileNotFoundException {
        // String getdate = new
        // SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());

        String getdate = "2022/08/08";
        for (int i = 0; i < emaildata.size(); i++) {
            String[] emailDetails = JavaMail.emaildata.get(i).strip().split(",");
            if (emailDetails[emailDetails.length - 1].equalsIgnoreCase(getdate) && emailDetails[1]
                    .equalsIgnoreCase("Wish you a Happy BirthDay")) {

                // System.out.println(emailcount +
                // ").
                // ---------------------------------------------------------------------------------------------------\nTo\t:
                // "
                // + emailDetails[0]
                // + "\nSubject : " + emailDetails[1] + "\nContent : "
                // + emailDetails[2].strip());
                sentmail.add(JavaMail.emaildata.get(i).strip());
            }
        }

        for (int i = 0; i < Recipient.recipientArrayList.size(); i++) {
            System.out.println(Recipient.recipientArrayList.get(i));
        }

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
        SerializationNew.serialization(JavaMail.emaildata);
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
        FileWriter writer = new FileWriter("emai.txt", true);
        writer.write(saveData);
        writer.close();
        emaildata.add(saveData);
        return saveData;
    }

}
