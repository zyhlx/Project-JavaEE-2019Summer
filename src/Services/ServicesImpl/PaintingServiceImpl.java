package Services.ServicesImpl;

import Services.PaintingService;
import bean.Painting;
import dao.IPaintingDAO;
import dao.factory.DAOFactory;

public class PaintingServiceImpl implements PaintingService {
    private IPaintingDAO iPaintingDAO;

    public PaintingServiceImpl(){
        iPaintingDAO = DAOFactory.getIPaintingDAOInstance();
    }
    @Override
    public Painting getOnePainting(int artworkID) {
        return iPaintingDAO.getOnePaintingByArtworkID(artworkID);
    }
    public Painting[] getHotPaintings(){
        return iPaintingDAO.getHotPaintings();
    }

    @Override
    public Painting[] getNewPostPaintings() {
        return iPaintingDAO.getNewPostPaintings();
    }

    @Override
    public void update(Painting painting) {
        iPaintingDAO.update(painting);
    }

    @Override
    public int delete(int paintingID) {
        return iPaintingDAO.delete(paintingID);
    }
}
