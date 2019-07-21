package Services.ServicesImpl;

import Services.UserDetailService;
import bean.Favour;
import bean.Painting;
import bean.User;
import dao.IFavourDAO;
import dao.IFriendRelationDAO;
import dao.IPaintingDAO;
import dao.IUserDAO;
import dao.factory.DAOFactory;
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
        dao_user = DAOFactory.getIUserDAOInstance();
        dao_favour = DAOFactory.getIFavourDAOInstance();
        dao_friend = DAOFactory.getIFriendRelationDAOInstance();
    }
    public User getUser(int userID){
        return dao_user.getUserInformation(userID);
    };
    public int updateUserInformation(int userID,String newName,String oldName,String pwd,String email,String signature){
        if (dao_user.login(oldName,pwd)==null){
            return 0;
        }
        if ((!oldName.equals(newName))&&dao_user.getUserID(newName)!=0){
            return -1;
        }
        dao_user.updateInformation(userID,newName,email,signature);
        return 1;

    };

    @Override
    public List<User> getUserAll() {
        return dao_user.getUserAll();
    }

    @Override
    public int delete(String userID) {
        return dao_user.delete(userID);
    }

    @Override
    public int getUserID(String name) {
        return dao_user.getUserID(name);
    }
    //
//    public List<Favour> getFavourPaintings(int userID){
//
//        return dao_favour.getFavour();
//    }

//    public List<User> getFriend(int userID){
//        return dao_friend.getFriends(userID);
//    };
}
