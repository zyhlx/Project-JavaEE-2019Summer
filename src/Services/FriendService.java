package Services;

import bean.FriendRelation;

import java.util.List;

public interface FriendService {
    public List<FriendRelation> searchFriends(int userID);
}
