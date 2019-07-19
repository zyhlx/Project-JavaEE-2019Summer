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
    private Connection conn;
    private PreparedStatement pstmt;

    public PaintingDAOImpl() {
        conn = new OpenConnection().getConnection();
    }

    //
    public Painting[] getHotPaintings() {
//        String query = "SELECT * FROM paintings ORDER BY MSRP desc LIMIT 0 , 3";
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
        Connection conn = this.conn;
        try {
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
                temp.setGalleryID(rs.getInt("GalleryID"));
                String queryForGallery = "SELECT * FROM galleries WHERE GalleryID=" + "'" + temp.getGalleryID() + "'";
                List<Gallery> galleries = DAOFactory.getIGalleryDAOInstance().getGalleries(queryForGallery);
                if (!galleries.isEmpty()) {
                    temp.setGallery(DAOFactory.getIGalleryDAOInstance().getGalleries(queryForGallery).get(0));
                }
                temp.setYearOfWork(rs.getInt("YearOfWork"));


                results.add(temp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    @Override
    public int update(Painting painting) {
        Connection conn = this.conn;
        try {
            String updatePainting = "UPDATE paintings SET GalleryID=?, ImageFileName=?, Title=?, Description=?, YearOfWork=? WHERE PaintingID=?";
            //预编译SQL，减少sql执行
            PreparedStatement ptmt = conn.prepareStatement(updatePainting);
            ptmt.setInt(1, painting.getGalleryID());
            ptmt.setString(2, painting.getImageFileName());
            ptmt.setString(3, painting.getTitle());
            ptmt.setString(4, painting.getDescription());
            ptmt.setInt(5, painting.getYearOfWork());
            ptmt.setInt(6, painting.getPaintingID());

            // 执行
            ptmt.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
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

