package dao;

import bean.FriendRelation;

import java.util.List;

public interface IFriendRelationDAO {
    public int insert(FriendRelation friendRelation);
    public int update(FriendRelation friendRelation);
    public int delete(String query);
    public int delete(int userID,int client);
    public List<FriendRelation> getFriends(String query);
    public List<FriendRelation> getFriends(int userID, int accepted);
    public void deleteAll(int userID);
    public List<Integer> getHotFriends();

//    public List<FriendRelation> getFriendsByUserID(int userID);
}
