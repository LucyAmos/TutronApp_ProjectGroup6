package com.example.tutrong6.BEANS;

import java.util.Arrays;

public class Tutor extends User{
    //attributs
    private String education_level;
    private String native_language;
    private String description;
    private byte[] profile_picture;
    private static final int ROLE = 3;

    //private Blob test;

    //contructors

    public Tutor(){this.roleID = ROLE;}

    public Tutor(User user,String education_level,String native_language, String description, byte[] profile_picture )
    {
        super( user.ID,user.first_name, user.last_name, user.email, user.password);
        this.education_level = education_level;
        this.native_language = native_language;
        this.description = description;
        this.profile_picture = profile_picture;
        this.roleID = ROLE;
    }

    public Tutor(String education_level,String native_language, String description, byte[] profile_picture) {
        this.education_level = education_level;
        this.native_language = native_language;
        this.description = description;
        this.profile_picture = profile_picture;
        this.roleID = ROLE;
    }

    public Tutor(String first_name, String last_name, String email, String password, String education_level,String native_language, String description, byte[] profile_picture) {
        super( first_name, last_name, email, password);
        this.education_level = education_level;
        this.native_language = native_language;
        this.description = description;
        this.profile_picture = profile_picture;
        this.roleID = ROLE;
    }



    // getters and setters


    public String getEducation_level() {
        return education_level;
    }

    public void setEducation_level(String education_level) {
        this.education_level = education_level;
    }

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
        return "Tutor{" +
                "education_level='" + education_level + '\'' +
                ", native_language='" + native_language + '\'' +
                ", description='" + description + '\'' +
                ", profile_picture=" + Arrays.toString(profile_picture) +
                ", ID=" + ID +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", roleID=" + roleID +
                '}';
    }
}
