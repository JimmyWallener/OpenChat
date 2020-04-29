package se.gritacademy.openchat.models;

public class UserModel {

    private String username, uid, email;
    private Long timeStamp;

    public UserModel() {
    }

    public UserModel(String username, String uid, String email, Long timeStamp) {
        this.username = username;
        this.uid = uid;
        this.email = email;
        this.timeStamp = timeStamp;
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

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
