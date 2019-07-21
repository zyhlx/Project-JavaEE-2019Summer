package Services.ServicesImpl;

import Services.FriendService;
import bean.FriendRelation;
import dao.IFriendRelationDAO;
import dao.factory.DAOFactory;

import java.util.List;

public class FriendServiceImpl implements FriendService {
    private IFriendRelationDAO iFriendRelationDAO;

    public FriendServiceImpl(){
        iFriendRelationDAO = DAOFactory.getIFriendRelationDAOInstance();
    }

    @Override
    public List<FriendRelation> searchFriends(int userID) {
        return iFriendRelationDAO.searchFriends(userID);
    }

}
