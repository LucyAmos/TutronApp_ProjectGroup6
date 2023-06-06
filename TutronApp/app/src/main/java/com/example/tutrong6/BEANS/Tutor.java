package com.example.tutrong6.BEANS;

public class Tutor extends User{
    //attributs
    private String native_language;
    private String description;
    private byte[] profile_picture;

    //contructors

    public Tutor(){}

    public Tutor(String native_language, String description, byte[] profile_picture) {
        this.native_language = native_language;
        this.description = description;
        this.profile_picture = profile_picture;
    }

    public Tutor(int id, String first_name, String last_name, String email, String password, String native_language, String description, byte[] profile_picture) {
        super(id, first_name, last_name, email, password);
        this.native_language = native_language;
        this.description = description;
        this.profile_picture = profile_picture;
    }

    // getters and setters


    public String getNative_language() {
        return native_language;
    }

    public void setNative_language(String native_language) {
        this.native_language = native_language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(byte[] profile_picture) {
        this.profile_picture = profile_picture;
    }

    //class methods and functions

    @Override
    public String getRole() {
        return this.getClass().getSimpleName();
    }
}
