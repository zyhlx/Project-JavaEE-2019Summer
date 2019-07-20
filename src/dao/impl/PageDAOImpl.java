package dao.impl;

import com.mysql.cj.jdbc.util.ResultSetUtil;
import dao.IPageDAO;
import db.DBUtil;
import db.OpenConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PageDAOImpl implements IPageDAO {
    private ResultSet rs;
    @Override
    public <T> List<T> findAlls(Class<T> clazz,String tableName,String type, int pageNum, int pageSize) {
        String sql = "select ImageFileName imageFileName,Title title,Description description,PaintingID paintingID,Gallery gallery,Artist artist, MSRP msrp  from "+tableName+type+"  ORDER BY MSRP desc limit ?,?";
        // 创建一个集合来保存
        return DBUtil.get(clazz,sql,pageNum,pageSize);
    }
    @Override
    public int getTotalCount( String type ) {
        String sql = "select count(*) as count from paintings "+ type;
        int count = 0;
        try {
            PreparedStatement preparedStatement =   new OpenConnection().getConnection().prepareStatement(sql);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                count = rs.getInt("count");
            }
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return count;
    }
}
