package sample.Shops;

public class shops {

    private String shopName;
    private int rent;

    public shops(String shopName, int rent) {
        this.shopName = shopName;
        this.rent = rent;
    }
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

}
