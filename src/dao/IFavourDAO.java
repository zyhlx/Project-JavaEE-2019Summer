package dao;


import bean.Favour;

import java.util.List;

public interface IFavourDAO {
    public List<Favour> getFavour(String query);
    public int insert(Favour favour);

}