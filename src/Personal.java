
public class Personal extends CloseFriend {
    // atrributes name,nickname,email,birthday
    private String nickName;

    public Personal(String name, String nickName, String email, String birthDay) {
        super(name, email, birthDay);
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

}
