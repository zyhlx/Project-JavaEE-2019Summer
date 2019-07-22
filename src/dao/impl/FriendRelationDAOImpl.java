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
    public int delete(String query) {
        int rs = 1;
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(query);
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
            String insertFriend = "INSERT INTO friends ( patronID,clientID,accepted ) VALUES (?,?,?)";
            PreparedStatement ptmt = conn.prepareStatement(insertFriend);
            ptmt.setInt(1,friendRelation.getPatronID());
            ptmt.setInt(2,friendRelation.getClientID());
            ptmt.setInt(3,friendRelation.getAccepted());
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
//
//    public List<FriendRelation> getFriendsByUserID(int userID){
//        String sql = "SELECT * FROM friends WHERE userID=" +  userID;
//    }

    public List<FriendRelation> getFriends(int userID, int accepted){
        String queryForFriends = "SELECT * FROM friends WHERE patronID=" + "'" + userID + "' AND accepted='" + accepted + "'";
        return getFriends(queryForFriends);
    }

    @Override
    public void deleteAll(int userID) {
        String deleteFromFriends = "DELETE FROM friends WHERE patronID=" + "'" + userID + "' OR clientID=" + "'" + userID + "'";
        delete(deleteFromFriends);
    }

    @Override
    public int delete(int userID, int clientID) {
        String deleteFriend = "DELETE FROM friends WHERE patronID=" + "'" + userID + "'" + "AND clientID=" + "'" + clientID + "'";
        return delete(deleteFriend);
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
