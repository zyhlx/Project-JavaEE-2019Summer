package dao;

import bean.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    public int insertUser(String name, String pwd, String email, String tel, String address);

    public int getUserID(String userName);
    public boolean login(String userName, String pwd);
    public List<User> getUser(String query);
    public int delete(String userID);
    public void update(User user) throws SQLException;
}
