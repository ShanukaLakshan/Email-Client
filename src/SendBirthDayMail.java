import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;

import javax.management.monitor.Monitor;

public class SendBirthDayMail {

    public static void sendBirthDayWishes() throws Exception {
        String[] date = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime()).split("/");

        int currentDay = Integer.parseInt(date[2]);
        int currentMonth = Integer.parseInt(date[1]);

        for (Recipient a : Recipient.recipientArrayList) {
            int day, month;

            if (a instanceof Personal) {
                String[] birthDay = ((Personal) a).getBirthday().split("/");
                day = Integer.parseInt(birthDay[2]);
                month = Integer.parseInt(birthDay[1]);

                if (checkIsBirthdayToday(day, month, currentDay, currentMonth)) {
                    System.out.println("Send to Personal " + a.getEmail());
                    // JavaMail.sendMail(a.getEmail(),
                    // "Wish you a Happy BirthDay",
                    // "hugs and love on your birthday.Shanu for personal recipients.");
                }

            } else if (a instanceof OfficialFriend) {
                String[] birthDay = ((OfficialFriend) a).getBirthday().split("/");
                day = Integer.parseInt(birthDay[2]);
                month = Integer.parseInt(birthDay[1]);

                if (checkIsBirthdayToday(day, month, currentDay, currentMonth)) {
                    System.out.println("Send to OfficialFriend " + a.getEmail());
                    // JavaMail.sendMail(a.getEmail(),
                    // "Wish you a Happy BirthDay",
                    // "Shanu for an office friend, and hugs and love on your birthday. ");
                }
            }
        }
    }

    private static boolean checkIsBirthdayToday(int day, int month, int currentDay, int currentMonth) {
        return (day == currentDay) && (month == currentMonth);
    }

}
