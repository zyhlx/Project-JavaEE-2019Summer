package dao;


import bean.Favour;

import java.util.List;

public interface IFavourDAO {
    public List<Favour> getFavourByUserID(int userID);
    public Favour getFavourByFavourID(int favourID);
    public List<Favour> getShowFavour(int userID);
    public int insert(Favour favour);
    public int delete(int favourID);
    public int delete(String query);
    public int update(Favour favour);
    public Favour getOneFavour(int userID,int artworkID);

}