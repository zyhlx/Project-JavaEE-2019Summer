package Services.ServicesImpl;

import Services.UserDetailService;
import bean.Painting;
import bean.User;
import dao.IPaintingDAO;
import dao.IUserDAO;
import dao.impl.PaintingDAOImpl;
import dao.impl.UserDAOImpl;

import java.util.List;

public class UserDetailServiceImpl implements UserDetailService {
    private IUserDAO dao_user;
    private IPaintingDAO dao_painting;
    public UserDetailServiceImpl(){
        dao_user = new UserDAOImpl();
        dao_painting = new PaintingDAOImpl();
    }
    public User getUser(int userID){
        return dao_user.getUserInformation(userID);
    };

    public List<Painting> getFavourPaintings(int userID){
        return dao_painting.getFavourPaintings(userID);
    }

    public List<User> getFriend(int userID){
        return dao_user.getFriends(userID);
    };
}
