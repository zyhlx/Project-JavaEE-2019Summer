package bean;

public class Gallery {
    private String galleryName;
    private String nativeName;
    private String city;
    private String address;
    private String country;
    private int galleryID;

    public int getGalleryID() {
        return galleryID;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getGalleryName() {
        return galleryName;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setGalleryID(int galleryID) {
        this.galleryID = galleryID;
    }

    public void setGalleryName(String galleryName) {
        this.galleryName = galleryName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
