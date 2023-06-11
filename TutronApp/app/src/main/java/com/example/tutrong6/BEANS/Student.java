package com.example.tutrong6.BEANS;

public class Student extends User{
    //attributs
    private Address address;
    private CreditCard credit_card;
    private final static int ROLE = 2;

    //constructors
    public Student() {
        this.roleID = ROLE;
    }

    public Student(User user,Address address, CreditCard credit_card) {
        super(user.ID, user.first_name, user.last_name, user.email, user.password);
        this.address = address;
        this.credit_card = credit_card;
        this.roleID = ROLE;
    }

    public Student(Address address, CreditCard credit_card) {
        this.address = address;
        this.credit_card = credit_card;
        this.roleID = ROLE;
    }

    public Student(String first_name, String last_name, String email, String password, Address address, CreditCard credit_card) {
        super(first_name, last_name, email, password);
        this.address = address;
        this.credit_card = credit_card;
        this.roleID = ROLE;
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

    public static int getStaticRoleID()
    {
        return ROLE;
    }

    //class methods and functions

    @Override
    public String getRole() {
        return this.getClass().getSimpleName();
    }

}
