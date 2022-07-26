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

    public static void sendMail(String sendToEmailAddress, String subject, String content) throws Exception {

        System.out.println("Preparing email !");
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        // prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        String myEmail = "shanukalakshan9817@gmail.com";
        String password = "sbylqcjerwdcewub";

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }
        });

        Message message = prepareMessage(session, myEmail, sendToEmailAddress, subject, content);
        Transport.send(message);
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

}
