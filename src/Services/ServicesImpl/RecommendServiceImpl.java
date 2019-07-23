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
        List<User> users = new ArrayList<>();
        if (userID==0){
            for (Integer searchID:iFriendRelationDAO.getHotFriends()){
                users.add(iUserDAO.getUserInformation(searchID));
            }
            users = ifNullGetUsers(users);

            return users;
        }
        List<FriendRelation> friendRelations = iFriendRelationDAO.getFriends(userID,1);

        for (FriendRelation friendRelation : friendRelations) {
            User user = friendRelation.getClient();
            List<FriendRelation> friendRelationsOfFriends = iFriendRelationDAO.getFriends(user.getUserID(),1);
            for(FriendRelation friendRelation1 : friendRelationsOfFriends){
                users.add(friendRelation1.getClient());
            }
        }
        if (users.isEmpty()){
            for (Integer searchID:iFriendRelationDAO.getHotFriends()){
                users.add(iUserDAO.getUserInformation(searchID));
            };
        }
        users = ifNullGetUsers(users);
        return users;
    }

    private List<User> ifNullGetUsers(List<User> users) {
        if (users.isEmpty()){
            List<User> users1 = iUserDAO.getUserAll();
            if (users1.size()<3){
                users =users1;
            }else {
                users.add(users1.get(0));
                users.add(users1.get(1));
                users.add(users1.get(2));
            }
        }
        return users;
    }

    ;

    public List<Painting> paintingRecommend(int userID) {
        List<Painting> paintings = new ArrayList<>();
        if (userID==0){
            paintings = Arrays.asList(iPaintingDAO.getHotPaintings());
            return paintings;
        }
        List<FriendRelation> friendRelations = iFriendRelationDAO.getFriends(userID, 1);
        for (FriendRelation friendRelation : friendRelations) {
            User user = friendRelation.getClient();
            List<Favour> favours = iFavourDAO.getFavourByUserID(user.getUserID());
            for (Favour favour : favours) {
                paintings.add(favour.getPainting());
            }
        }
        if (paintings.isEmpty()){
            paintings = Arrays.asList(iPaintingDAO.getHotPaintings());
        }
        return paintings;

    }

    ;
}
