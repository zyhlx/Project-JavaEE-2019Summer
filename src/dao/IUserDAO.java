package dao;

import bean.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public int insertUser(String name, String pwd, String email);
    public int getUserID(String userName);
    public User login(String userName, String pwd);
    public List<User> getUser(String query);
    public int delete(String userID);
    public void update(User user) throws SQLException;
    public boolean isFriend(int patronID, int clientID);
    public void updateLoadTime(int userID);
    public void updateInformation(int userID,String newName,String email,String signature);
    public User getUserInformation(int userID);
    public List<User> searchFriends(String name,int userID);
    public List<User> getUserAll();
}
