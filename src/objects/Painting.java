package objects;

public class Painting {
    private String imageFileName;
    private String title;
    private int artistID;

    public int getArtistID() {
        return artistID;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public String getTitle() {
        return title;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
