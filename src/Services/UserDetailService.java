package Services;
import bean.Favour;
import bean.Painting;
import bean.User;

import java.util.List;

public interface UserDetailService {
    public User getUser(int userID);
    public List<User> getFriend(int userID);
    public List<Favour> getFavourPaintings(int userID);
}
