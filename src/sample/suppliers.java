package sample;

public class suppliers {

    private int id;
    private String address;
    private String contacts;


    public suppliers(int id, String address, String contacts) {
        this.id = id;
        this.address = address;
        this.contacts = contacts;
    }

    public suppliers() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}

