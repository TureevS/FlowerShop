package sample;

public class story {

    private int id;
    private int empId;
    private int shopId;
    private int cost;

    public story(int id, int empId, int shopId, int cost) {
        this.id = id;
        this.empId = empId;
        this.shopId = shopId;
        this.cost = cost;
    }

    public story() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
