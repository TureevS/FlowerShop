package sample;

public class shop {

    private int id;
    private String title;
    private String address;
    private int revenue;

    public shop(int id, String title, String address, int revenue) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.revenue = revenue;
    }

    public shop() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
}
