
package bean;

import java.math.BigDecimal;

public class Painting {
    private int paintingID;
    private String imageFileName = "";
    private String title;
    private String artist = "Unknown";
    private String description;
    private String gallery ="Unknown";
    private BigDecimal msrp = new BigDecimal(900.0000);
    private int yearOfWork = 0;
    private int postOfTime;
    private String videoPath = "";



    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

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
    public String getGallery() {
        return gallery;
    }

    public void setGallery(String gallery) {
        this.gallery = gallery;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
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


    public BigDecimal getMsrp() {
        return msrp;
    }

    public void setMsrp(BigDecimal msrp) {
        this.msrp = msrp;
    }


    public String getImageFileName() {
        return imageFileName;
    }

    public String getTitle() {
        return title;
    }


    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}