package dao;

import bean.Letter;

import java.util.List;

public interface ILetterDAO {
    public List<Letter> getReceiveList(int userID);
    public List<Letter> getSendLetter(int userID);
    public Letter get(int letterID);
    public void updateRead(int letterID);
    public void insert(int senderID,int receiverID,String contents);
}
