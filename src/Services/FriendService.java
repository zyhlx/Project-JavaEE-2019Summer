package Services;

import bean.FriendRelation;
import bean.User;

import java.util.List;

public interface FriendService {
    public List<FriendRelation> getFriends(int userID, int accepted);
    public List<FriendRelation> getFriendRequests (int clientID);
    public List<User> searchFriends(String name, int userID);
    public FriendRelation getFriendRealtion (int patronID, int clientID);
    public int insert(FriendRelation friendRelation);

    public int delete(int userID,int clientID);
    public int update(FriendRelation friendRelation);
    public void deleteAll(int userID);
    public boolean isFriends(int patronID,int clientID);

}
