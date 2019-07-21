package Services;

import bean.Letter;

import java.util.List;

public interface LetterService {
    public List<Letter> getSendLetters(int userID);
    public List<Letter> getReceiveLetter(int userID);
    public Letter getLetter(int letterID);
    public int sendLetter(int userID,String receive,String content);
    public void read(int letterID);
}
