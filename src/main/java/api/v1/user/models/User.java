package api.v1.user.models;

public class User {
    private String uid = "";
    private UserType userType = UserType.PERSON;
    private boolean hasAvatar = false;
    private boolean hasBanner = false;
    private Skill[] skills;
    private Profile profile;

    // Constructor
    public User(String uid, UserType userType, boolean hasAvatar, boolean hasBanner) {
        this.uid = uid;
        this.userType = userType;
        this.hasAvatar = hasAvatar;
        this.hasBanner = hasBanner;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUid() {
        return uid;
    }

    public UserType getUserType() {
        return userType;
    }

    public boolean isHasAvatar() {
        return hasAvatar;
    }

    public boolean isHasBanner() {
        return hasBanner;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setHasAvatar(boolean hasAvatar) {
        this.hasAvatar = hasAvatar;
    }

    public void setHasBanner(boolean hasBanner) {
        this.hasBanner = hasBanner;
    }

}
