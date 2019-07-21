package bean;

import dao.factory.DAOFactory;

import java.sql.Timestamp;

public class Favour {
    private int favourID;
    private int userID;
    private int paintingID;
    private int open;
    private Painting painting;
    private Timestamp favouredTime;
    private String displayTime;

    public String getDisplayTime() {
        return displayTime;
    }

    public void setDisplayTime(String displayTime) {
        this.displayTime = displayTime;
    }

    public void setFavouredTime(Timestamp favouredTime) {

        this.favouredTime = favouredTime;
        setDisplayTime(favouredTime.toString());
    }

    public Timestamp getFavouredTime() {
        return favouredTime;
    }

    public Painting getPainting() {
        return painting;
    }

    public void setPainting(Painting painting) {
        this.painting = painting;
    }



    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public int getFavourID() {
        return favourID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public int getPaintingID() {
        return paintingID;
    }

    public void setFavourID(int favourID) {
        this.favourID = favourID;
    }

    public void setPaintingID(int paintingID) {
        this.paintingID = paintingID;
    }
}
