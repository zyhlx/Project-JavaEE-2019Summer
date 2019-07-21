package Services.ServicesImpl;

import Services.LoginService;
import bean.User;
import dao.IUserDAO;
import dao.factory.DAOFactory;
import dao.impl.UserDAOImpl;

public class LoginServiceImpl implements LoginService {
    private IUserDAO userDAO;

    public LoginServiceImpl() {
        this.userDAO = DAOFactory.getIUserDAOInstance();
    }

    public User login(String name, String pwd){
        return userDAO.login(name,pwd);
    }

    @Override
    public void updateLoadTime(int userID) {
        userDAO.updateLoadTime(userID);
    }

    @Override
    public int insertUser(String name, String pwd, String email, String tel, String address) {
        return userDAO.insertUser(name,pwd,email,tel,address);
    }

    @Override
    public int getUserID(String userName) {
        return userDAO.getUserID(userName);
    }
}
