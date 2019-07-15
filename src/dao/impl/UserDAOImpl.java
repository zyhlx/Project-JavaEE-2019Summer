package dao.impl;

import dao.IUserDAO;
import db.OpenConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        int rs = 0;
        String query = "INSERT INTO users(users.name,password,email,tel,address,balance) values (" + "?,?,?,?,?,0)";
        try {
            Connection conn = this.conn;
            PreparedStatement ptmt1 = conn.prepareStatement(query);
            ptmt1.setString(1, name);
            ptmt1.setString(2, pwd);
            ptmt1.setString(3, email);
            ptmt1.setString(4, tel);
            ptmt1.setString(5, address);
            rs = ptmt1.executeUpdate();
            conn.commit();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rs;
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
}
