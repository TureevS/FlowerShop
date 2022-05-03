package sample.Employees;

public class employees {

    private int id;
    private String fio;
    private String position;
    private int salary;
    private String shopName;

    public employees(int id, String fio, String position, int salary, String shopName) {
        this.id = id;
        this.fio = fio;
        this.position = position;
        this.salary = salary;
        this.shopName = shopName;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
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