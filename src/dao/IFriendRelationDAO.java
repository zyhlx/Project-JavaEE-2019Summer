package dao;

import bean.FriendRelation;

import java.util.List;

public interface IFriendRelationDAO {
    public int insert(FriendRelation friendRelation);
    public int delete(String query);
    public List<FriendRelation> getFriends(String query);
    public List<FriendRelation> searchFriends(int userID);
//    public List<FriendRelation> getFriendsByUserID(int userID);
}
