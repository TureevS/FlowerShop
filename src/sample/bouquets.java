package sample;

public class bouquets {

    private int id;
    private String title;
    private int price;
    private int shopId;


    public bouquets(int id, String title, int price, int shopId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.shopId = shopId;
    }

    public bouquets() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }
}
