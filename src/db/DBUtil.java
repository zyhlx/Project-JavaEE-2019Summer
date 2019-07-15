package db;

import bean.Painting;

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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }





    //可以否
    public static ArrayList<Object> getUser(String userName, String type) {
        ArrayList<Object> user = new ArrayList<>();
        String searchForUserID = "select * from  users where name=?";
        try {
            //获取连接
            Connection conn = new OpenConnection().getConnection();
            PreparedStatement ptmt = conn.prepareStatement(searchForUserID);
            ptmt.setString(1, userName);
            ResultSet rs = ptmt.executeQuery();
            int i = 0;
            while (rs.next()) {
                user.add(rs.getObject(type));
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public static List<Integer> getID(String query, String nameOfID) {
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
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return results;

    }

    public static boolean isExisted(String query) {
        //获取连接
        Connection conn = new OpenConnection().getConnection();
        try {
            //预编译SQL，减少sql执行
            PreparedStatement ptmt = conn.prepareStatement(query);
            //执行
            ResultSet rs = ptmt.executeQuery();
            if (!rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;

    }


}
