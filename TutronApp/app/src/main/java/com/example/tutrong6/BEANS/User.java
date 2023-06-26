package com.example.tutrong6.BEANS;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    //attributs
    protected int ID;
    protected String first_name;
    protected String last_name;
    protected String email;
    protected String password;
    protected int roleID;

    protected boolean is_suspended;

    //constructors

    public User() {
    }

    public User(String first_name, String last_name, String email, String password) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }

    public User(int id, String first_name, String last_name, String email, String password) {
        this.ID = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
    }


    //getters and setters


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }


    public boolean getIs_suspended() {
        return is_suspended;
    }

    public void setIs_suspended(boolean is_suspended) {
        this.is_suspended = is_suspended;
    }


    //class methods and functions

    public String getRole()
    {
        return this.getClass().getSimpleName();
    }


    public static boolean isValidEmailAddressFormat(String email) {
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = java.util.regex.Pattern.compile(emailPattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleID=" + roleID +
                '}';
    }
}
