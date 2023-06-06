package com.example.tutrong6.BEANS;

public class Student extends User{
    //attributs
    private Address address;
    private CreditCard credit_card;

    //constructors
    public Student() {
    }
    public Student(Address address, CreditCard credit_card) {
        this.address = address;
        this.credit_card = credit_card;
    }

    public Student(int id, String first_name, String last_name, String email, String password, Address address, CreditCard credit_card) {
        super(id, first_name, last_name, email, password);
        this.address = address;
        this.credit_card = credit_card;
    }


    //getters & setters


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public CreditCard getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(CreditCard credit_card) {
        this.credit_card = credit_card;
    }

    //class methods and functions

    @Override
    public String getRole() {
        return this.getClass().getSimpleName();
    }
}
