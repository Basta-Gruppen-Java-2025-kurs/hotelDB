package models;

public class Customer {
    private int customerId;
    private String name;
    private String city;
    private String email;

    public Customer(int customerId, String name, String city, String email) {
        this.customerId = customerId;
        this.name = name;
        this.city = city;
        this.email = email;
    }

    public Customer(String name, String city, String email) {
        this.name = name;
        this.city = city;
        this.email = email;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
