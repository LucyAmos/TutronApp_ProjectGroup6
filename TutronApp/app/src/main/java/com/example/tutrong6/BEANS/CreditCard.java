package com.example.tutrong6.BEANS;

public class CreditCard {
    //attribut
    private int ID;
    private String num_carte;
    private String holder_name;
    private String exp_date;
    private int cvc;

    //constructors
    public CreditCard() {

    }

    public CreditCard(int ID, String num_carte, String holder_name, String exp_date, int cvc) {
        this.ID = ID;
        this.num_carte = num_carte;
        this.holder_name = holder_name;
        this.exp_date = exp_date;
        this.cvc = cvc;
    }

    //getters & setters


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNum_carte() {
        return num_carte;
    }

    public void setNum_carte(String num_carte) {
        this.num_carte = num_carte;
    }

    public String getHolder_name() {
        return holder_name;
    }

    public void setHolder_name(String holder_name) {
        this.holder_name = holder_name;
    }

    public String getExp_date() {
        return exp_date;
    }

    public void setExp_date(String exp_date) {
        this.exp_date = exp_date;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }
}
