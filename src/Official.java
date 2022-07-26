// use Inheritance
public class Official extends Recipient {

    private String designation;

    public Official(String name, String email, String designation) {
        super(name, email);
        this.designation = designation;
    }

    // use Encapsulation
    public String getDesignation() {
        return designation;
    }
}
