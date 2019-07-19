package dao;

import bean.Painting;

import java.util.List;

public interface IPaintingDAO {
    public Painting[] getHotPaintings();
    public Painting[] getNewPostPaintings();
    public int update(Painting painting);
    public List<Painting> getPaintings(String query);

    public int delete(int paintingID);


}
