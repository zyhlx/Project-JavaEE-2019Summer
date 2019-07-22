package Services.ServicesImpl;

import Services.FavoursService;
import bean.Favour;
import dao.IFavourDAO;
import dao.factory.DAOFactory;

import java.util.List;

public class FavoursServiceImpl implements FavoursService {
    private IFavourDAO iFavourDAO;

    public FavoursServiceImpl() {
        iFavourDAO = DAOFactory.getIFavourDAOInstance();
    }

    @Override
    public List<Favour> getFavourByUserID(int userID) {
        return iFavourDAO.getFavourByUserID(userID);
    }

    @Override
    public Favour getFavourByFavourID(int favourID) {
        return iFavourDAO.getFavourByFavourID(favourID);
    }

    @Override
    public void changeOpen(Favour favour) {
        if (favour.getOpen() == 0) {
            favour.setOpen(1);
        } else {
            favour.setOpen(0);
        }
        iFavourDAO.update(favour);
    }

    @Override
    public int insert(Favour favour) {
        return iFavourDAO.insert(favour);
    }

    @Override
    public int delete(int favourID) {
        return iFavourDAO.delete(favourID);
    }

    @Override
    public int delete(String query) {
        return iFavourDAO.delete(query);
    }

    @Override
    public int update(Favour favour) {
        return iFavourDAO.update(favour);
    }

    @Override
    public List<Favour> getShowFavours(int userID) {
        return iFavourDAO.getShowFavour(userID);
    }

    public boolean isFavoured(int userID, int artworkID) {
        if (iFavourDAO.getOneFavour(userID, artworkID) != null) {
            return true;
        }
        return false;
    }
}
