package dao.impl;

import bean.Favour;
import dao.IFavourDAO;
import db.OpenConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavourDAOImpl implements IFavourDAO {


    public FavourDAOImpl(){
//        conn = new OpenConnection().getConnection();
    }

    private static Connection getConnection() throws SQLException {
        return   new OpenConnection().getConnection();
    }
    @Override
    public List<Favour> getFavour(String query) {
        List<Favour> favours = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(query);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Favour favour = null;
                // 设置user信息
               favour.setFavourID(rs.getInt("favourID"));
               favour.setUserID(rs.getInt("userID"));
               favour.setPaintingID(rs.getInt("paintingID"));
               favours.add(favour);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {

            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return favours;

    }

    public int insert(Favour favour) {
        int rs = 1;
        String query = "INSERT INTO favours(userID,artworkID) VALUES (?,?)";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt1 = conn.prepareStatement(query);
            ptmt1.setInt(1,favour.getUserID());
            ptmt1.setInt(2,favour.getPaintingID());
            rs = ptmt1.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {

            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return rs;
    }
}
