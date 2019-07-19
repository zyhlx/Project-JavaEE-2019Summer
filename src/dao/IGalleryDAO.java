package dao;

import bean.Gallery;

import java.util.List;

public interface IGalleryDAO {
    public List<Gallery> getGalleries(String query);
    public int getGalleryID(String galleryName);
}
