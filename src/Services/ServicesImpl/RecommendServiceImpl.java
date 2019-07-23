package Services.ServicesImpl;

import Services.RecommendService;
import bean.Favour;
import bean.FriendRelation;
import bean.Painting;
import bean.User;
import dao.IFavourDAO;
import dao.IFriendRelationDAO;
import dao.IPaintingDAO;
import dao.IUserDAO;
import dao.factory.DAOFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecommendServiceImpl implements RecommendService {
    private IFriendRelationDAO iFriendRelationDAO;
    private IPaintingDAO iPaintingDAO;
    private IFavourDAO iFavourDAO;
    private IUserDAO iUserDAO;

    public RecommendServiceImpl() {
        iFriendRelationDAO = DAOFactory.getIFriendRelationDAOInstance();
        iPaintingDAO = DAOFactory.getIPaintingDAOInstance();
        iFavourDAO = DAOFactory.getIFavourDAOInstance();
        iUserDAO = DAOFactory.getIUserDAOInstance();
    }

    public List<User> friendRecommend(int userID) {
        List<FriendRelation> friendRelations = iFriendRelationDAO.getFriends(userID,1);
        List<User> users = new ArrayList<>();
        for (FriendRelation friendRelation : friendRelations) {
            User user = friendRelation.getClient();
            List<FriendRelation> friendRelationsOfFriends = iFriendRelationDAO.getFriends(user.getUserID(),1);
            for(FriendRelation friendRelation1 : friendRelationsOfFriends){
                users.add(friendRelation1.getClient());
            }
        }
        if (users.isEmpty()){
            for (Integer searchID:iFriendRelationDAO.getHotFriends()){
                users.add(iUserDAO.getUserInformation(userID));
            };
        }
        return users;
    }

    ;

    public List<Painting> paintingRecommend(int userID) {
        List<FriendRelation> friendRelations = iFriendRelationDAO.getFriends(userID, 1);
        List<Painting> paintings = new ArrayList<>();
        for (FriendRelation friendRelation : friendRelations) {
            User user = friendRelation.getClient();
            List<Favour> favours = iFavourDAO.getFavourByUserID(user.getUserID());
            for (Favour favour : favours) {
                paintings.add(iPaintingDAO.getOnePaintingByArtworkID(favour.getFavourID()));
            }
        }
        if (paintings.isEmpty()){
            paintings = Arrays.asList(iPaintingDAO.getHotPaintings());
        }
        return paintings;

    }

    ;
}
