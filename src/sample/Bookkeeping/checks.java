package sample.Bookkeeping;

public class checks {
    private int id;
    private String productTitle;
    private int quantity;
    private String date;
    private String employee;
    private String shopName;

    public checks(int id, String productTitle, int quantity, String date, String employee, String shopName) {
        this.id = id;
        this.productTitle = productTitle;
        this.quantity = quantity;
        this.date = date;
        this.employee = employee;
        this.shopName = shopName;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getQuantityString() {
        return Integer.toString(quantity);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
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
