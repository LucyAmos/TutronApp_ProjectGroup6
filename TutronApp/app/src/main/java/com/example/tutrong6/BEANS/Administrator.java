package com.example.tutrong6.BEANS;

public class Administrator extends User{
    //attributes
    private final static int ROLE = 1;

    //constructors

    public Administrator() {
        this.roleID = ROLE;
    }

    public Administrator(User user) {
        super( user.ID, user.first_name, user.last_name, user.email, user.password);
        this.roleID = ROLE;
    }

    public Administrator(String first_name, String last_name, String email, String password) {
        super(first_name, last_name, email, password);
        this.roleID = ROLE;
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


    @Override
    public String toString() {
        return "Administrator{" +
                "ID=" + ID +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleID=" + roleID +
                '}';
    }
}
