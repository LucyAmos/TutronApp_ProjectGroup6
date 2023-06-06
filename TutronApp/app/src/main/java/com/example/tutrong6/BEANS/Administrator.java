package com.example.tutrong6.BEANS;

public class Administrator extends User{

    //constructors

    public Administrator() {
    }

    public Administrator(int id, String first_name, String last_name, String email, String password) {
        super(id, first_name, last_name, email, password);
    }

    //class methods and functions


    @Override
    public String getRole() {
        return this.getClass().getSimpleName();
    }
}
