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
}
