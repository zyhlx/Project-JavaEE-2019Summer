package dao;

import bean.FriendRelation;

import java.util.List;

public interface IFriendRelationDAO {
    public int insert(FriendRelation friendRelation);
    public int delete(int friendID);
    public List<FriendRelation> getFriends(String query);
}
