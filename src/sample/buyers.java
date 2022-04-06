package sample;

public class buyers {

    private int id;
    private String fio;
    private String date;
    private int receiptId;

    public buyers(int id, String fio, String date, int receiptId) {
        this.id = id;
        this.fio = fio;
        this.date = date;
        this.receiptId = receiptId;
    }

    public buyers() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }
}
