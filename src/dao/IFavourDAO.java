package dao;


import bean.Favour;

import java.util.List;

public interface IFavourDAO {
    public List<Favour> getFavour(String query);
    public int insert(Favour favour);
    public int delete(int favourID);
    public int update(Favour favour);

}