package se.gritacademy.openchat.models;

public class UserModel {

    private String username, name, uid, email;
    private Long lastLogin;

    public UserModel() {
    }

    public UserModel(String username, String uid, String email, Long lastLogin) {
        this.username = username;
        this.uid = uid;
        this.email = email;
        this.lastLogin = lastLogin;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Long lastLogin) {
        this.lastLogin = lastLogin;
    }
}
