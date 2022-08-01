// use Inheritance
public class CloseFriend extends Recipient {

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
