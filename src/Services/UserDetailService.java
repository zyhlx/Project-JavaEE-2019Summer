package Services;
import bean.Favour;
import bean.Painting;
import bean.User;

import java.util.List;

public interface UserDetailService {
    public User getUser(int userID);
    public int updateUserInformation(int userID,String newName,String oldName,String pwd,String email,String signature);
//    public List<User> getFriend(int userID);
//    public List<Favour> getFavourPaintings(int userID);
}
