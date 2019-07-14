package db;

import com.mysql.cj.protocol.Resultset;
import objects.Painting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
    public static void insert(String query) {
        //获取连接
        Connection conn = new OpenConnection().getConnection();
        try {

            //预编译
            PreparedStatement ptmt = conn.prepareStatement(query); //预编译SQL，减少sql执行

            //执行
            ptmt.execute();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public List<Integer> select(int userID) throws SQLException{
        List<Integer> results = new ArrayList<>();
        //获取连接
        Connection conn = new OpenConnection().getConnection();
        //sql, 每行加空格
        String sql = "select * from favours where userId=?";
        //预编译SQL，减少sql执行
        PreparedStatement ptmt = conn.prepareStatement(sql);
        //传参
        ptmt.setInt(1, userID);
        //执行
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()){
            results.add(rs.getInt("artworkID"));
        }
        return results;

    }

    public static List<ResultSet> getResult(String query) {
        List<ResultSet> results = new ArrayList<>();
        //获取连接
        Connection conn = new OpenConnection().getConnection();
        try {
            //预编译SQL，减少sql执行
            PreparedStatement ptmt = conn.prepareStatement(query);
            //执行
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                results.add(rs);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }

    public static int getUserID(String userName) {
        int userID = 0;
        String searchForUserID = "select * from  users where name=?";
        try {
            //获取连接
            Connection conn = new OpenConnection().getConnection();
            PreparedStatement ptmt = conn.prepareStatement(searchForUserID);
            ptmt.setString(1, userName);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                userID = rs.getInt("userID");
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userID;
    }

    public static List<Integer> getID (String query, String nameOfID) {
        List<Integer> results = new ArrayList<>();
        //获取连接
        Connection conn = new OpenConnection().getConnection();
        try {
            //预编译SQL，减少sql执行
            PreparedStatement ptmt = conn.prepareStatement(query);

            //执行
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                results.add(rs.getInt(nameOfID));
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;

    }

    public static List<Painting> getPaintings(String query) {
        List<Painting> results = new ArrayList<>();
        //获取连接
        Connection conn = new OpenConnection().getConnection();
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
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;
    }




}
