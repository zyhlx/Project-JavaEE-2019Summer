package Services;
import bean.Favour;
import bean.Painting;
import bean.User;

import java.util.List;

public interface UserDetailService {
    public User getUser(int userID);
    public List<User> getUserAll();
    public int delete(String userID);
    public int getUserID(String name);
    public List<User> searchUsers(String name);
    public int updateUserInformation(int userID,String newName,String oldName,String pwd,String email,String signature);
//    public List<User> getFriend(int userID);
//    public List<Favour> getFavourPaintings(int userID);
}
