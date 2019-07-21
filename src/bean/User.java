package bean;

import dao.factory.DAOFactory;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class User {
    private int userID;
    private String username;
    private String email;
    private Timestamp loadTime;
    private String password;
    private List<Favour> favours;
    private String signature;
    private String type;
    private int isFriend;
    private List<User> friends;

    public int getIsFriend() {
        return isFriend;
    }

    public void setIsFriend(int isFriend) {
        this.isFriend = isFriend;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setFavours(List<Favour> favours) {
        this.favours = favours;
    }

    public List<Favour> getFavours() {
        return favours;
    }

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


    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getLoadTime() {
        return loadTime;
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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public void changePermission() throws SQLException {
        if (type.equals("normal")) {
            type = "admin";
        }
        else {
            type = "normal";
        }
        DAOFactory.getIUserDAOInstance().update(this);
    };
}
