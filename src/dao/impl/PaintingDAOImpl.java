package dao.impl;

import bean.Painting;
import dao.IPaintingDAO;
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

    public PaintingDAOImpl(){
        conn = new OpenConnection().getConnection();
    }

//
    public Painting[] getHotPaintings(){
        Painting[] results = new Painting[3];
        //获取连接
        Connection conn = this.conn;
        try {
            String query = "SELECT * FROM paintings  WHERE PaintingID <=> NULL ORDER BY MSRP desc LIMIT 0 , 3";
            //预编译SQL，减少sql执行
            PreparedStatement ptmt = conn.prepareStatement(query);
            //执行
            ResultSet rs = ptmt.executeQuery();
            int i =0;
            while (rs.next()) {
                Painting temp = new Painting();
                temp.setImageFileName(rs.getString("ImageFileName"));
                temp.setTitle(rs.getString("Title"));
                temp.setArtistID(rs.getInt("ArtistID"));
                temp.setDescription(rs.getString("Description"));
                temp.setGalleryID(rs.getInt("GalleryID"));
                temp.setPaintingID(rs.getInt("PaintingID"));
                results[i] = temp;
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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
                results.add(temp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
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


}
