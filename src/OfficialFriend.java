
public class OfficialFriend extends CloseFriend {
    // attributes name,email,designation,birthday
    private String designation;

    public OfficialFriend(String name, String email, String designation, String birthDay) {
        super(name, email, birthDay);
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }
}
