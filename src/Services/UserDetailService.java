package Services;
import bean.Painting;
import bean.User;

import java.util.List;

public interface UserDetailService {
    public User getUser(int userID);
    public List<User> getFriend(int userID);
    public List<Painting> getFavourPaintings(int userID);
}
