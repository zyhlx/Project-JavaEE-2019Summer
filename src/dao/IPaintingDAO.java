package dao;

import bean.Painting;

import java.util.List;

public interface IPaintingDAO {
    public Painting[] getHotPaintings();
    public Painting[] getNewPostPaintings();

}
