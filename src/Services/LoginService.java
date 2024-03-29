package Services;

import bean.User;

public interface LoginService {

    public User login(String name,String pwd);
    public void updateLoadTime(int userID);
    public int insertUser(String name, String pwd, String email);
    public int getUserID(String userName);
}
