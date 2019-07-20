package dao.impl;

import dao.IUserDAO;
import db.OpenConnection;
import bean.User;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    public UserDAOImpl() {
//        conn = new OpenConnection().getConnection();
    }

    private static Connection getConnection() throws SQLException {
        return   new OpenConnection().getConnection();
    }

    public int insertUser(String name, String pwd, String email, String tel, String address) {
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
        String query = "UPDATE users SET name=?, email=?, password=?, loadTime=?, type=?" +
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
            ptmt.setInt(6, user.getUserID());
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

    public List<User> login(String userName, String pwd) {
        String searchForUserID = "select * from  users where name='"+userName+"' and password ='"+pwd+"'";
        return getUser(searchForUserID);
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
    public List<User> getFriends(int userID) {
        List<User> friends = new ArrayList<>();
        String queryForFriends = "SELECT * FROM friends WHERE patronID=" + "'" + userID + "'";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(queryForFriends);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
                int clientID = rs.getInt("clientID");
                String queryForUser = "SELECT * FROM users WHERE userID=" + "'" + clientID + "'";
                List<User> results = getUser(queryForUser);
                if (!results.isEmpty()) {
                    friends.add(results.get(0));
                }
                else {
                    System.out.println("Found no user!");
                }

            }

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return friends;
    }

    @Override
    public boolean isFriend(int patronID, int clientID) {
        boolean check = false;
        String queryForFriends = "SELECT * FROM friends WHERE patronID=? AND clientID=?";
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
