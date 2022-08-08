import java.util.ArrayList;

// use Abstraction
public abstract class Recipient {

    static ArrayList<Recipient> recipientArrayList = new ArrayList<Recipient>();
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
