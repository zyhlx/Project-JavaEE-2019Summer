package dao.impl;

import bean.Gallery;
import bean.Painting;
import dao.IPaintingDAO;
import dao.factory.DAOFactory;
import db.DBUtil;
import db.OpenConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaintingDAOImpl implements IPaintingDAO {
    public PaintingDAOImpl() {

    }

    @Override
    public Painting getOnePaintingByArtworkID(int artworkID) {
        String query = "SELECT * FROM paintings WHERE PaintingID=" + "'" + artworkID + "'";
        List<Painting> paintings = getPaintings(query);
        if (paintings.isEmpty()){
            return null;
        }
        return paintings.get(0);
    }

    private static Connection getConnection() throws SQLException {
        return   new OpenConnection().getConnection();
    }
    //
    public Painting[] getHotPaintings() {
        String query = "SELECT ImageFileName imageFileName,Title title,Description description,PaintingID paintingID FROM paintings ORDER BY MSRP desc LIMIT 0 , 3";
        Painting[] results = new Painting[3];
        List<Painting> temp = DBUtil.get(Painting.class, query);
        results[0] = temp.get(0);
        results[1] = temp.get(1);
        results[2] = temp.get(2);
        return results;


    }

    public Painting[] getNewPostPaintings() {
        Painting[] results = new Painting[3];
        String query = "SELECT ImageFileName imageFileName,Title title,Description description,PaintingID paintingID FROM paintings ORDER BY postTime desc LIMIT 0 , 3";


//        String query = "SELECT * FROM paintings ORDER BY postTime desc LIMIT 0 , 3";
        List<Painting> temp = DBUtil.get(Painting.class, query);

        results[0] = temp.get(0);
        results[1] = temp.get(1);
        results[2] = temp.get(2);
        return results;

    }



    public List<Painting> getPaintings(String query) {
        List<Painting> results = new ArrayList<>();
        //获取连接
        Connection conn = null;
        try {
            conn = getConnection();
            //预编译SQL，减少sql执行
            PreparedStatement ptmt = conn.prepareStatement(query);
            //执行
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Painting temp = new Painting();
                temp.setImageFileName(rs.getString("ImageFileName"));
                temp.setTitle(rs.getString("Title"));
                temp.setDescription(rs.getString("Description"));
                temp.setPaintingID(rs.getInt("PaintingID"));
                temp.setGallery(rs.getString("Gallery"));
                temp.setMsrp(rs.getBigDecimal("MSRP"));
                temp.setYearOfWork(rs.getInt("YearOfWork"));
                temp.setArtist(rs.getString("Artist"));
                temp.setVideoPath(rs.getString("VideoPath"));


                results.add(temp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

//    public List<Painting> getPaintings(String query) {
//        List<Painting> results = new ArrayList<>();
//        //获取连接
//        Connection conn = this.conn;
//        try {
//            //预编译SQL，减少sql执行
//            PreparedStatement ptmt = conn.prepareStatement(query);
//            //执行
//            ResultSet rs = ptmt.executeQuery();
//            while (rs.next()) {
//                Painting temp = new Painting();
//                temp.setImageFileName(rs.getString("ImageFileName"));
//                temp.setTitle(rs.getString("Title"));
//                temp.setDescription(rs.getString("Description"));
//                temp.setPaintingID(rs.getInt("PaintingID"));
//
//                results.add(temp);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return results;
//    }


    @Override
    public int update(Painting painting) {
        Connection conn = null;
        try {
            conn = getConnection();

//            String updatePainting = "UPDATE paintings SET Gallery=?, ImageFileName=?, Title=?, Description=?, YearOfWork=? WHERE PaintingID=?";
//            //预编译SQL，减少sql执行
//            PreparedStatement ptmt = conn.prepareStatement(updatePainting);
//            ptmt.setString(1, painting.getGallery());

            // 新的painting
            String query = null;
            if (painting.getPaintingID() == 0) {
                query = "INSERT INTO paintings(Gallery, ImageFileName, Title, Description, YearOfWork, Artist, VideoPath) VALUES (?,?,?,?,?,?,?) ";
            }
            else {
                query = "UPDATE paintings SET Gallery=?, ImageFileName=?, Title=?, Description=?, YearOfWork=?, Artist=?, VideoPath=?, MSRP=? WHERE PaintingID=?";
            }
            //预编译SQL，减少sql执行
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setString(1, painting.getGallery());

            ptmt.setString(2, painting.getImageFileName());
            ptmt.setString(3, painting.getTitle());
            ptmt.setString(4, painting.getDescription());
            ptmt.setInt(5, painting.getYearOfWork());
            ptmt.setString(6, painting.getArtist());
            ptmt.setString(7, painting.getVideoPath());
            ptmt.setBigDecimal(8,painting.getMsrp());

            if (painting.getPaintingID()!=0) {
                ptmt.setInt(9, painting.getPaintingID());
            }

            // 执行
            ptmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    @Override
    public int delete(int paintingID) {
        Connection conn = null;
        try {
            conn = getConnection();
            String deleteQuery = "DELETE FROM paintings WHERE PaintingID=?";
            PreparedStatement ptmt = conn.prepareStatement(deleteQuery);
            ptmt.setInt(1, paintingID);
            ptmt.executeUpdate();
            conn.commit();
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 1;
    }

//    @Override
//    public List<Painting> getFavourPaintings(int userID) {
//        List<Painting> paintings = new ArrayList<>();
//        String queryForFavours = "SELECT * FROM favours WHERE userID=" + "'" + userID + "'";
//        Connection conn = null;
//        try {
//            conn = getConnection();
//            PreparedStatement ptmt = conn.prepareStatement(queryForFavours);
//            ResultSet rs = ptmt.executeQuery();
//            while (rs.next()) {
//                int paintingID = rs.getInt("artworkID");
//                String queryForPainting = "SELECT * FROM paintings WHERE PaintingID=" + "'" + paintingID + "'";
//                List<Painting> results = getPaintings(queryForPainting);
//                if (!results.isEmpty()) {
//                    paintings.add(results.get(0));
//                }
//                else {
//                    System.out.println("Found no painting!");
//                }
//
//            }
//
//        }
//        catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return paintings;
//    }
}



        //
//    public void add(Painting painting) {
//
//        String sql = "insert into paintings values(null,?,?,?)";
//        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);) {
//
//            ps.setString(1, hero.name);
//            ps.setFloat(2, hero.hp);
//            ps.setInt(3, hero.damage);
//
//            ps.execute();
//
//            ResultSet rs = ps.getGeneratedKeys();
//            if (rs.next()) {
//                int id = rs.getInt(1);
//                hero.id = id;
//            }
//        } catch (SQLException e) {
//
//            e.printStackTrace();
//        }
//    }

