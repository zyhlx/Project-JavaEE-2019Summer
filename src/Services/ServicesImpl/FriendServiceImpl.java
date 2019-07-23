package Services.ServicesImpl;

import Services.FriendService;
import bean.FriendRelation;
import bean.User;
import dao.IFriendRelationDAO;
import dao.IUserDAO;
import dao.factory.DAOFactory;

import java.util.List;

public class FriendServiceImpl implements FriendService {
    private IFriendRelationDAO iFriendRelationDAO;
    private IUserDAO iUserDAO;

    public FriendServiceImpl(){

        iFriendRelationDAO = DAOFactory.getIFriendRelationDAOInstance();
        iUserDAO = DAOFactory.getIUserDAOInstance();
    }

    @Override
    public List<User> searchFriends(String name, int userID) {
        return iUserDAO.searchFriends(name,userID);
    }

    @Override
    public int insert(FriendRelation friendRelation) {
        return iFriendRelationDAO.insert(friendRelation);
    }

    @Override
    public int delete(int userID,int client) {
        return iFriendRelationDAO.delete(userID,client);
    }

    @Override
    public void deleteAll(int userID) {
        iFriendRelationDAO.deleteAll(userID);
    }

    @Override
    public int update(FriendRelation friendRelation) {
        return 0;
    }

    @Override
    public List<FriendRelation> getFriends(int userID, int accepted) {
        return iFriendRelationDAO.getFriends(userID, accepted);
    }

    @Override
    public boolean isFriends(int patronID, int clientID) {
        return iUserDAO.isFriend(patronID, clientID); }

    @Override
    public List<FriendRelation> getFriendRequests(int  clientID) {
        String selectFriendRequests = "SELECT * FROM friends WHERE clientID=" + "'" + clientID + "'";
        List<FriendRelation> friendRequests =  iFriendRelationDAO.getFriends(selectFriendRequests);
        for(FriendRelation friendRelation: friendRequests) {
            String selectForpatron = "SELECT * FROM users WHERE userID=" + "'" + friendRelation.getPatronID() + "'";
            friendRelation.setPatron(iUserDAO.getUser(selectForpatron).get(0));
        }
        return friendRequests;
    }

    @Override
    public List<FriendRelation> getAllFriends(int userID) {
        String selectFriendRequests = "SELECT * FROM friends WHERE patronID=" + "'" + userID + "'";
       return iFriendRelationDAO.getFriends(selectFriendRequests);
    }

    @Override
    public FriendRelation getFriendRealtion(int patronID, int clientID) {
        String selectFriend = "SELECT * FROM friends WHERE patronID=" + "'" + patronID + "'" + "AND clientID=" + "'" + clientID + "'";
        List<FriendRelation> results = iFriendRelationDAO.getFriends(selectFriend);
        if (results.isEmpty()) {
            return null;
        }
        else {
            return results.get(0);
        }
    }

}
