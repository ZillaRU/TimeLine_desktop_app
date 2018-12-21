package model;

/**
 * @author: zilla0148
 * @date: 2018/12/17 14:41
 */
public class User {
    //todo check if the username has existed before registration
    private String userID ;
    private String password;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
