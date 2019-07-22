package Services;

import bean.Favour;

import java.util.List;

public interface FavoursService {
    public List<Favour> getFavourByUserID(int userID);
    public int insert(Favour favour);
    public int delete(int favourID);
    public int delete(String query);
    public int update(Favour favour);
    public List<Favour> getShowFavours(int userID);
    public boolean isFavoured(int userID,int artworkID);
    public Favour getFavourByFavourID(int favourID);
    public void changeOpen(Favour favour);
}
