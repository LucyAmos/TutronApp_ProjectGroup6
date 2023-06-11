package com.example.tutrong6.BEANS;

public class Address {
    //attributs
    private int ID;
    private String street_address;
    private String city;

    private String region;
    private String postal_code;
    private String country;

    //constructors

    public Address() {

    }

    public Address(String street_address, String city, String region, String postal_code, String country) {
        this.street_address = street_address;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
        this.region= region;
    }

    //getters & setters


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
