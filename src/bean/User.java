package bean;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public abstract class User {
    private int userID;
    private String username;
    private String email;
    private Timestamp loadTime;
    private String password;


    public int getUserID() {
        return userID;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getLoadTime() {
        return loadTime;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoadTime(Timestamp loadTime) {
        this.loadTime = loadTime;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public abstract User changePermission() throws SQLException;
}
