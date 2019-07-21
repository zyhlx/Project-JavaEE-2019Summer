package Services;

import bean.User;

public interface LoginService {

    public User login(String name,String pwd);
    public void updateLoadTime(int userID);
}
