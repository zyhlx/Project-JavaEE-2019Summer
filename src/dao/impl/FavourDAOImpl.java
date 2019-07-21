package dao.impl;

import bean.Favour;
import bean.Painting;
import dao.IFavourDAO;
import dao.factory.DAOFactory;
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
                Favour favour = new Favour();
                // 设置favour信息
               favour.setFavourID(rs.getInt("favourID"));
               favour.setUserID(rs.getInt("userID"));
               favour.setPaintingID(rs.getInt("artworkID"));
                favour.setOpen(rs.getInt("open"));
                favour.setFavouredTime(rs.getTimestamp("favouredTime"));
                favour.setDisplayTime(favour.getFavouredTime().toString());
                String queryForPainting = "SELECT * FROM paintings WHERE PaintingID=" + "'" + favour.getPaintingID() + "'";
                List<Painting> results = DAOFactory.getIPaintingDAOInstance().getPaintings(queryForPainting);
                if (!results.isEmpty()) {
                    favour.setPainting(results.get(0));
                }
                else {
                    System.out.println("found no painting!");
                }
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
        String query = "INSERT INTO favours(userID,artworkID,open) VALUES (?,?,?)";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt1 = conn.prepareStatement(query);
            ptmt1.setInt(1,favour.getUserID());
            ptmt1.setInt(2,favour.getPaintingID());
            ptmt1.setInt(3, favour.getOpen());
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

    @Override
    public int delete(int favourID) {
        String query = "DELETE FROM favours WHERE favourID=" + "'" + favourID + "'";
        return delete(query);
    }

    @Override
    public int delete(String query) {
        int rs = 1;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt1 = conn.prepareStatement(query);
            ptmt1.execute();
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

    @Override
    public int update(Favour favour) {
        int rs = 1;
        String query = "UPDATE favours SET open=? WHERE favourID=?";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt1 = conn.prepareStatement(query);
            ptmt1.setInt(1,favour.getOpen());
            ptmt1.setInt(2,favour.getFavourID());
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
