package dao;

import bean.Letter;

import java.util.List;

public interface ILetterDAO {
    public List<Letter> getList(String query,Object... args);
    public Letter get(String query,Object... args);
}
