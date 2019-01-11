import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class SumItem {

    private String imgid;
    private String itemname;
    private String description;
    private String releasedate;
    private boolean bought;
    private int price;
    private ImageView imageview;
    private Image image;

    public SumItem(String imgid, String itemname, String description, String releasedate, boolean bought, int price) {

        this.imgid = imgid;
        this.image = new Image(imgid);
        imageview = new ImageView();
        imageview.setImage(image);
        imageview.setPreserveRatio(true);
        imageview.setFitHeight(100);
        imageview.setFitWidth(100);
        this.itemname = itemname;
        this.description = description;
        this.releasedate = releasedate;
        this.bought = bought;
        this.price = price;
    }

//    public int getId() {
//        return id;
//    }

    public String getImgid() {
        return imgid;
    }

    public ImageView getImageview() {
        return imageview;
    }

    public String getItemname() {
        return itemname;
    }

    public String getDescription() {
        return description;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public boolean getBought() {
        return bought;
    }

    public int getPrice() {
        return price;
    }
}
