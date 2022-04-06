package sample;

public class flowerinorder {

    private int id;
    private String title;
    private int price;
    private int quantity;
    private int suppliersId;
    private int storeId;

    public flowerinorder(int id, String title, int price, int quantity, int suppliersId, int storeId) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.quantity = quantity;
        this.suppliersId = suppliersId;
        this.storeId = storeId;
    }

    public flowerinorder() {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(int suppliersId) {
        this.suppliersId = suppliersId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}