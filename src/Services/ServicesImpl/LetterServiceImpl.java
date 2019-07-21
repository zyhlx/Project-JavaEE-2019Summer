package Services.ServicesImpl;

import Services.LetterService;
import bean.Letter;
import dao.ILetterDAO;
import dao.IUserDAO;
import dao.factory.DAOFactory;
import db.DBUtil;

import java.util.List;

public class LetterServiceImpl implements LetterService {
    private ILetterDAO letterDAO;
    private IUserDAO userDAO;
    public LetterServiceImpl(){
        letterDAO = DAOFactory.getILetterDAOInstance();
        userDAO = DAOFactory.getIUserDAOInstance();
    }

    @Override
    public List<Letter> getSendLetters(int userID) {
        return letterDAO.getSendLetter(userID);
    }

    @Override
    public List<Letter> getReceiveLetter(int userID) {
        return letterDAO.getReceiveList(userID);
    }

    @Override
    public Letter getLetter(int letterID) {

        return letterDAO.get(letterID);
    }
    public void read(int letterID){
        letterDAO.updateRead(letterID);
    }

    @Override
    public int sendLetter(int userID,String receive,String content){
        int receiveID = userDAO.getUserID(receive);
        if (receiveID == 0){
            return -1;
        }
        letterDAO.insert(userID,receiveID,content);
        return 1;
    }

}
