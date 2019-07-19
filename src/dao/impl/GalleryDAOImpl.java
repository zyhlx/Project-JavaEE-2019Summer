package dao.impl;

import bean.Gallery;
import dao.IGalleryDAO;
import db.OpenConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GalleryDAOImpl implements IGalleryDAO {
    private Connection conn;
    private PreparedStatement pstmt;

    public GalleryDAOImpl() {
        conn = new OpenConnection().getConnection();
    }
    @Override
    public List<Gallery> getGalleries(String query) {
        List<Gallery> results = new ArrayList<>();
        //获取连接
        Connection conn = this.conn;
        try {
            //预编译SQL，减少sql执行
            PreparedStatement ptmt = conn.prepareStatement(query);
            //执行
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                Gallery temp = new Gallery();
                temp.setGalleryID(rs.getInt("GalleryID"));
                temp.setAddress(rs.getString("GalleryAddress"));
                temp.setGalleryName(rs.getString("GalleryName"));
                temp.setNativeName(rs.getString("GalleryNativeName"));
                results.add(temp);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    @Override
    public int getGalleryID(String galleryName) {
        //获取连接
        Connection conn = this.conn;
        int galleryID = 0;
        try {
            String queryForGallery = "SELECT GalleryID FROM galleries WHERE GalleryName=?";
            //预编译SQL，减少sql执行
            PreparedStatement ptmt = conn.prepareStatement(queryForGallery);
            ptmt.setString(1, galleryName);
            //执行
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                galleryID = rs.getInt("GalleryID");
                return galleryID;
            }
            else {
                String insertGallery = "INSERT INTO galleries (GalleryName) VALUES (?)";
                PreparedStatement ptmt1 = conn.prepareStatement(insertGallery);
                ptmt1.setString(1,galleryName);
                ptmt1.executeUpdate();
                conn.commit();
                galleryID = getGalleryID(galleryName);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return galleryID;
    }
}
