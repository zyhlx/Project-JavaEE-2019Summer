package Services;

import bean.Painting;

public interface PaintingService {
    public Painting getOnePainting(int artworkID);
    public Painting[] getHotPaintings();
    public Painting[] getNewPostPaintings();
    public void update(Painting painting);
    public int delete(int paintingID);
}
