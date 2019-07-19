package bean;

public class Painting {
    private int paintingID;
    private String imageFileName;
    private String title;
    private int artistID;
    private String description;
    private int galleryID;
    private Gallery gallery;
    private int shapeID;
    private int width;
    private int height;
    private int cost;
    private int msrp;
    private int yearOfWork;
    private int postOfTime;

    public int getPostOfTime() {
        return postOfTime;
    }

    public void setPostOfTime(int postOfTime) {
        this.postOfTime = postOfTime;
    }

    public int getYearOfWork() {
        return yearOfWork;
    }

    public void setYearOfWork(int yearOfWork) {
        this.yearOfWork = yearOfWork;
    }

    public void setGallery(Gallery gallery) {
        this.gallery = gallery;
    }

    public Gallery getGallery() {
        return gallery;
    }

    public int getPaintingID() {
        return paintingID;
    }

    public void setPaintingID(int paintingID) {
        this.paintingID = paintingID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGalleryID() {
        return galleryID;
    }

    public void setGalleryID(int galleryID) {
        this.galleryID = galleryID;
    }

    public int getShapeID() {
        return shapeID;
    }

    public void setShapeID(int shapeID) {
        this.shapeID = shapeID;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMsrp() {
        return msrp;
    }

    public void setMsrp(int msrp) {
        this.msrp = msrp;
    }

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
