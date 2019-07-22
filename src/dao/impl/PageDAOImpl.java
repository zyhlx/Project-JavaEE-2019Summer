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
    public <T> List<T> findAlls(Class<T> clazz,String method_info,String input_info, int pageNum, int pageSize) {

        String type = getSqlBymethod(method_info, input_info);
        String sql = "select ImageFileName imageFileName,Title title,Description description,PaintingID paintingID,Gallery gallery,Artist artist, MSRP msrp  from paintings "+type+"  ORDER BY MSRP desc limit ?,?";
        // 创建一个集合来保存
        return DBUtil.get(clazz,sql,pageNum,pageSize);
    }

    private String getSqlBymethod(String method_info, String input_info) {
        String[] method = method_info.split("&");
        if (method.length==0){
            method = new String[1];
            method[0] = "title";
        }
        StringBuilder sql = new StringBuilder();
        if (input_info.equals("")){
            sql.append("");
        }else {
            sql.append(" where ").append(method[0]).append(" LIKE '%").append(input_info).append("%'");
            for (int i =1; i<method.length;i++){
                sql.append(" OR ").append(method[i]).append(" LIKE '%").append(input_info).append("%' ");
            }
        }
        return sql.toString();
    }

    public int getTotalCount( String method,String  input_info) {
        String type = getSqlBymethod(method,input_info);
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
