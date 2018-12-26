package model;

/**
 * @author: zilla0148
 * @date: 2018/12/17 14:41
 */
public class User {
    private String userID ;
    private String password;

    public User(String userID) {
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }
}
