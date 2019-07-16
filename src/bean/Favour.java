package bean;

public class Favour {
    private int favourID;
    private int userID;
    private int paintingID;

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
