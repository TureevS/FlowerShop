package sample;

public class staff {

    private int id;
    private String fio;
    private String position;
    private int id2;
    private int salary;

    public staff(int id, String fio, String position, int id2, int salary) {
        this.id = id;
        this.fio = fio;
        this.position = position;
        this.id2 = id2;
        this.salary = salary;
    }

    public staff() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId2() {
        return id2;
    }

    public void setId2(int id2) {
        this.id2 = id2;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

}