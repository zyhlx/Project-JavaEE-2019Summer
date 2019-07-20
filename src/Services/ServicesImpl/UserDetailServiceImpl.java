package Services.ServicesImpl;

import Services.UserDetailService;
import bean.Favour;
import bean.Painting;
import bean.User;
import dao.IFavourDAO;
import dao.IFriendRelationDAO;
import dao.IPaintingDAO;
import dao.IUserDAO;
import dao.impl.FavourDAOImpl;
import dao.impl.FriendRelationDAOImpl;
import dao.impl.PaintingDAOImpl;
import dao.impl.UserDAOImpl;

import java.util.List;

public class UserDetailServiceImpl implements UserDetailService {
    private IUserDAO dao_user;
    private IFriendRelationDAO dao_friend;
    private IFavourDAO dao_favour;
    public UserDetailServiceImpl(){
        dao_user = new UserDAOImpl();
        dao_favour = new FavourDAOImpl();
        dao_friend = new FriendRelationDAOImpl();
    }
    public User getUser(int userID){
        return dao_user.getUserInformation(userID);
    };

    public List<Favour> getFavourPaintings(int userID){

        return dao_favour.getFavour();
    }

    public List<User> getFriend(int userID){
        return dao_friend.getFriends(userID);
    };
}
