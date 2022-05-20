package kz.assel.joshapp.models;

public class Product {
    private int id;
    private String name;
    private String company;
    private String status;
    private String details;

    public Product(){}

    public Product(int id, String name, String company, String status, String details){
        this.id = id;
        this.name = name;
        this.company = company;
        this.status = status;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
