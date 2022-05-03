package sample.Products;

public class products {
    private int id;
    private String productTitle;
    private String productCategory;
    private int productQuantity;
    private int productPrice;
    private String shopName;

    public products(int id, String title, String category, int quantity, int price,  String shopName) {
        this.id = id;
        this.productTitle = title;
        this.productCategory = category;
        this.productQuantity = quantity;
        this.productPrice = price;
        this.shopName = shopName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}