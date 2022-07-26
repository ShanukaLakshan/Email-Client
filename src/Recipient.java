import java.util.ArrayList;

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
        for (Recipient person : recipientArrayList) {
            if ((person instanceof CloseFriend) && givenday.equals(((CloseFriend) person).getBirthday())) {
                System.out.println(person.getName());
            }
        }
    }

}
