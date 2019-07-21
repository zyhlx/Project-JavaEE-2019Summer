package dao.impl;

import bean.Letter;
import bean.Painting;
import dao.ILetterDAO;
import db.DBUtil;

import java.util.List;

public class LetterDAOImpl implements ILetterDAO {
    public List<Letter> getList(String query, Object... args){
        return DBUtil.get(Letter.class, query,args);
    }

    public Letter get(String query,Object... args){
        return DBUtil.getT(Letter.class, query,args);
    }


}
