package Services;

import bean.Painting;
import bean.User;

import java.util.List;

public interface RecommendService {
    public List<User> friendRecommend(int userID);
    public List<Painting> paintingRecommend(int userID);
}
