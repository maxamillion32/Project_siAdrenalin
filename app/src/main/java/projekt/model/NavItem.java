package projekt.model;

/**
 * Created by Mitja on 12.5.2015.
 */
public class NavItem {
    private int imageId;

    public NavItem(int imageId) {
        this.imageId = imageId;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
