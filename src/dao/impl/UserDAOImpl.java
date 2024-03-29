package dao.impl;

import dao.IUserDAO;
import db.DBUtil;
import db.OpenConnection;
import bean.User;


import java.sql.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;


public class UserDAOImpl implements IUserDAO {
    public UserDAOImpl() {
//        conn = new OpenConnection().getConnection();
    }

    private static Connection getConnection() throws SQLException {
        return   new OpenConnection().getConnection();
    }

    public int insertUser(String name, String pwd, String email) {
        int rs = 0;
        String query = "INSERT INTO users(name,password,email) values (" + "?,?,?)";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt1 = conn.prepareStatement(query);
            ptmt1.setString(1, name);
            ptmt1.setString(2, pwd);
            ptmt1.setString(3, email);
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

    public void updateLoadTime(int userID) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        String query = "UPDATE users SET loadTime = '" + date+
                "' WHERE userID=?";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setInt(1, userID);
            ptmt.executeUpdate();
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

    }
    public List<User> searchFriends(String name,int userID){
        String query = "SELECT * FROM users WHERE name LIKE '%" + name + "%' AND userID <>" + "'" + userID + "'" ;
        return getUser(query);
    }


    public int getUserID(String userName) {
        int userID = 0;
        String searchForUserID = "select * from  users where name=?";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(searchForUserID);
            ptmt.setString(1, userName);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                userID = rs.getInt("userID");
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

        return userID;
    }

    @Override
    public List<User> getUserAll() {
        String query = "SELECT * FROM users";
        return getUser(query);
    }

    public User getUserInformation(int userID){
        String sql = "select userID userID,name username,email email,loadTime loadTime,password password,type type,signature from  users where userID=?";
        return DBUtil.getT(User.class,sql,userID);
    }

    public List<User> getUser(String query) {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(query);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setUserID(rs.getInt("userID"));
                user.setUsername(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setLoadTime(rs.getTimestamp("loadTime"));
                user.setType(rs.getString("type"));
                user.setSignature(rs.getString("signature"));

                users.add(user);
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

        return users;
    }

    /**
     * 更新 user 表（如改变用户权限）
     *
     * @param user
     */
    public void update(User user) {
        String query = "UPDATE users SET name=?, email=?, password=?, loadTime=?, type=?, signature=?" +
                "WHERE userID=?";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setString(1, user.getUsername());
            ptmt.setString(2, user.getEmail());
            ptmt.setString(3, user.getPassword());
            ptmt.setTimestamp(4, user.getLoadTime());
            ptmt.setString(5, user.getType());
            ptmt.setString(6,user.getSignature());
            ptmt.setInt(7, user.getUserID());
            ptmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {

            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void updateInformation(int userID,String newName,String email,String signature){
        String query = "UPDATE users SET name=?, email=?, signature=?" +
                "WHERE userID=?";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setString(1, newName);
            ptmt.setString(2, email);
            ptmt.setString(3, signature);
            ptmt.setInt(4, userID);
            ptmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {

            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    private User getOneUser(String query){
        List<User> users = getUser(query);
        if (users.size()>0){
            return users.get(0);
        }
        return null;
    }


    public User login(String userName, String pwd) {
        String searchForUserID = "select * from  users where name='"+userName+"' and password ='"+pwd+"'";
        return getOneUser(searchForUserID);
    }

    public int delete(String userID) {
        String deleteFromDB = "DELETE FROM users WHERE userID=?";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(deleteFromDB);
            ptmt.setInt(1, Integer.parseInt(userID));
            ptmt.execute();
            conn.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

        return 1;
    }


    @Override
    public boolean isFriend(int patronID, int clientID) {
        boolean check = false;
        String queryForFriends = "SELECT * FROM friends WHERE patronID=? AND clientID=? AND accepted='1'";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(queryForFriends);
            ptmt.setInt(1, patronID);
            ptmt.setInt(2, clientID);
            ResultSet rs = ptmt.executeQuery();
            if (rs.next()) {
                check = true;
            } else {
                check = false;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return check;
    }
}
