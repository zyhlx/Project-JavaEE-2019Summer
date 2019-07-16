package bean;

import dao.factory.DAOFactory;

import java.sql.SQLException;
import java.sql.Timestamp;

public class NormalUser extends User {
    @Override
    public User changePermission() throws SQLException {
            User user = new Adimn(getUserID(), getUsername(), getEmail(), getLoadTime(), getPassword());
        DAOFactory.getIUserDAOInstance().update(user);
            return user;
    }

    public NormalUser(int userID, String username, String email, Timestamp loadTine, String password) {
       setUserID(userID);
       setEmail(email);
       setUsername(username);
       setLoadTime(loadTine);
       setPassword(password);
    }
    public NormalUser(){}
}
