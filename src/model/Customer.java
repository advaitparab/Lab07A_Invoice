package model;

public class Customer {
    private String name;
    private Address address;

    public Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String toString() {
        return name + "\n" + address.toString();
    }
}
