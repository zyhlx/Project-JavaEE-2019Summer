package bean;

public class Favour {
    private int favourID;
    private int userID;
    private int paintingID;
    private int open;

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
