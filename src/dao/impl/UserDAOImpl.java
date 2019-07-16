package dao.impl;

import bean.Adimn;
import bean.NormalUser;
import bean.User;
import dao.IUserDAO;
import db.OpenConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    private Connection conn;
    private PreparedStatement pstmt;

    public UserDAOImpl(){
        conn = new OpenConnection().getConnection();
    }

    private static Connection getConnection() throws SQLException {
        return   new OpenConnection().getConnection();
    }

    public int insertUser(String name, String pwd, String email, String tel, String address) {
//        int rs = 0;
//        String query = "INSERT INTO users(name,password,email,tel,address,balance) values (" + "?,?,?,?,?,0)";
//        try {
//            Connection conn = this.conn;
//            PreparedStatement ptmt1 = conn.prepareStatement(query);
//            ptmt1.setString(1, name);
//            ptmt1.setString(2, pwd);
//            ptmt1.setString(3, email);
//            ptmt1.setString(4, tel);
//            ptmt1.setString(5, address);
//            rs = ptmt1.executeUpdate();
//            conn.commit();
//
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return rs;
        return 0;
    }



    public int getUserID(String userName) {
        int userID = 0;
        String searchForUserID = "select * from  users where name=?";
        try {
            //获取连接
            Connection conn = this.conn;
            PreparedStatement ptmt = conn.prepareStatement(searchForUserID);
            ptmt.setString(1, userName);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                userID = rs.getInt("userID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userID;
    }

    public List<User> getUser(String query) {
        List<User> users = new ArrayList<>();
        try {
            //获取连接
            Connection conn = this.conn;
            PreparedStatement ptmt = conn.prepareStatement(query);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                User user = null;
                // 设置user信息
                if (rs.getString("type").equals("normal")) {
                    user = new NormalUser();
                }
                else {
                    user = new Adimn();
                }
                user.setUserID(rs.getInt("userID"));
                user.setUsername(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setLoadTime(rs.getTimestamp("loadTime"));

                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return users;
    }

    /**
     * 更新 user 表（如改变用户权限）
     * @param user
     */
    public void update(User user) throws SQLException {
        String query = "UPDATE users SET name=?, email=?, password=?, loadTime=?, type=?" +
                "WHERE userID=?";

            Connection conn = this.conn;
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setString(1,user.getUsername());
            ptmt.setString(2,user.getEmail());
            ptmt.setString(3,user.getPassword());
            ptmt.setTimestamp(4, user.getLoadTime());
            if (user instanceof NormalUser) {
                ptmt.setString(5, "normal");
            }
            else {
                ptmt.setString(5, "admin");
            }
            ptmt.setInt(6, user.getUserID());
            int rs = ptmt.executeUpdate();
            conn.commit();

    }

    public boolean login(String userName, String pwd) {
        boolean isRight = false;
        String searchForUserID = "select * from  users where name=? and password =?";
        try {
            //获取连接
            Connection conn = this.conn;
            PreparedStatement ptmt = conn.prepareStatement(searchForUserID);
            ptmt.setString(1, userName);
            ptmt.setString(2, pwd);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                isRight = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isRight;
    }

    public int delete(String userID) {
        String deleteFromDB = "DELETE FROM users WHERE userID=?";
        try {
            //获取连接
            Connection conn = this.conn;
            PreparedStatement ptmt = conn.prepareStatement(deleteFromDB);
            ptmt.setInt(1, Integer.parseInt(userID));
            ptmt.execute();
            conn.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        return 1;
    }

}
