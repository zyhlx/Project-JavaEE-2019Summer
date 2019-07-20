package dao.impl;

import bean.Favour;
import bean.FriendRelation;
import bean.Painting;
import bean.User;
import dao.IFriendRelationDAO;
import dao.factory.DAOFactory;
import db.OpenConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FriendRelationDAOImpl implements IFriendRelationDAO {
    private static Connection getConnection() throws SQLException {
        return   new OpenConnection().getConnection();
    }
    @Override
    public int delete(int friendID) {
        int rs = 1;
        Connection conn = null;
        try {
            conn = getConnection();
            String deleteFriend = "DELETE FROM friends WHERE friendID=?";
            PreparedStatement ptmt = conn.prepareStatement(deleteFriend);
            ptmt.setInt(1, friendID);
            rs = ptmt.executeUpdate();
            conn.commit();

        }
        catch (SQLException e) {
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

    @Override
    public int insert(FriendRelation friendRelation) {
        int rs = 1;
        Connection conn = null;
        try {
            conn = getConnection();
            String insertFriend = "INSERT INTO friends ( patronID,clientID ) VALUES (?,?)";
            PreparedStatement ptmt = conn.prepareStatement(insertFriend);
            ptmt.setInt(1,friendRelation.getPatronID());
            ptmt.setInt(2,friendRelation.getClientID());
            rs = ptmt.executeUpdate();
            conn.commit();

            }
        catch (SQLException e) {
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

    @Override
    public List<FriendRelation> getFriends(String query) {
        List<FriendRelation> friends = new ArrayList<>();
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(query);
            ResultSet rs = ptmt.executeQuery();
            while (rs.next()) {
               FriendRelation friendRelation = new FriendRelation();
               friendRelation.setFriendID(rs.getInt("friendID"));
               friendRelation.setPatronID(rs.getInt("patronID"));
               friendRelation.setClientID(rs.getInt("clientID"));
               String queryForClient = "SELECT * FROM users WHERE userID=" + "'" + friendRelation.getClientID() + "'";
               List<User> results = DAOFactory.getIUserDAOInstance().getUser(queryForClient);
               if (!results.isEmpty()) {
                   User user = results.get(0);
                   friendRelation.setClient(user);
               }
               else {
                   System.out.println("found no user!");
               }
               friends.add(friendRelation);
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
        return friends;

    }
}
