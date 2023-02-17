package domain.user;

public class User {
    private String uid = "";
    private UserType userType = UserType.PERSON;
    private boolean hasAvatar = false;
    private boolean hasBanner = false;

    public User(String uid, UserType userType, boolean hasAvatar, boolean hasBanner) {
        this.uid = uid;
        this.userType = userType;
        this.hasAvatar = hasAvatar;
        this.hasBanner = hasBanner;
    }
}
