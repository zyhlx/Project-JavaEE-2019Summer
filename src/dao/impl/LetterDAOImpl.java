package dao.impl;

import bean.Letter;

import dao.ILetterDAO;
import db.DBUtil;
import db.OpenConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.time.LocalDateTime.now;


public class LetterDAOImpl implements ILetterDAO {
    @Override
    public List<Letter> getSendLetter(int userID){
        String query = "SELECT * FROM letters WHERE senderID ="+ userID+" ORDER BY timeReleased";
        List<Letter> letters = DBUtil.get(Letter.class,query);
        for (Letter letter : letters) {
            String queryForClient = "SELECT name FROM users WHERE userID=" + "'" + letter.getReceiverID() + "'";
            letter.setReceiveName(DBUtil.getForValue(queryForClient));
            String queryForSender = "SELECT name FROM users WHERE userID=" + "'" + letter.getSenderID() + "'";
            letter.setSenderName(DBUtil.getForValue(queryForSender));
        }
        return letters;
    }

    @Override
    public List<Letter> getReceiveList(int userID) {
        String query = "SELECT * FROM letters WHERE receiverID ="+ userID+" ORDER BY timeReleased";
        List<Letter> letters = DBUtil.get(Letter.class,query);
        for (Letter letter : letters) {
            String queryForClient = "SELECT name FROM users WHERE userID=" + "'" + letter.getReceiverID() + "'";
            letter.setReceiveName(DBUtil.getForValue(queryForClient));
            String queryForSender = "SELECT name FROM users WHERE userID=" + "'" + letter.getSenderID() + "'";
            letter.setSenderName(DBUtil.getForValue(queryForSender));
        }
        return letters;
    }

    @Override
    public Letter get(int letterID){
        String query = "SELECT * FROM letters WHERE letterID ="+ letterID;
        Letter letter =  DBUtil.getT(Letter.class,query);
        assert letter != null;
        String queryForClient = "SELECT name FROM users WHERE userID=" + "'" + letter.getReceiverID() + "'";
        letter.setReceiveName(DBUtil.getForValue(queryForClient));
        String queryForSender = "SELECT name FROM users WHERE userID=" + "'" + letter.getSenderID() + "'";
        letter.setSenderName(DBUtil.getForValue(queryForSender));
        return letter;
    }

    public void updateRead(int letterID){
        String query = "UPDATE letters SET status=1" +
                "WHERE letterID=?";
        Connection conn = null;
        try {
            conn = getConnection();
            PreparedStatement ptmt = conn.prepareStatement(query);
            ptmt.setInt(1, letterID);
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
    };

    @Override
    public void insert(int senderID,int receiverID,String contents){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());
        String sql = "INSERT INTO letters(senderID,receiverID,contents,status,timeReleased ) values (?,?,?,'0','"+ date+"')";
//        DBUtil.insert(sql,senderID,receiverID,contents);
        Connection conn = null;
        int rs =-1;
        try {
            conn = getConnection();
            PreparedStatement ptmt1 = conn.prepareStatement(sql);
            ptmt1.setInt(1, senderID);
            ptmt1.setInt(2, receiverID);
            ptmt1.setString(3, contents);
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

//        return rs;
    }

    private static Connection getConnection() throws SQLException {
        return   new OpenConnection().getConnection();
    }


}
