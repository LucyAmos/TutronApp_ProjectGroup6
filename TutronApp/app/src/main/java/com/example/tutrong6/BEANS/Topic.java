package com.example.tutrong6.BEANS;

public class Topic {
    //attributes
    private int ID;
    private int tutorID;
    private String name;
    private int years_of_experience;
    private String description;
    private boolean is_offered ;
    private static final int OFFERING_MAX =5;
    private static final int CREATION_MAX = 20;

    //constructions
    public Topic() {

    }
    public Topic(String name, int years_of_experience, String description) {
        this.name = name;
        this.years_of_experience = years_of_experience;
        this.description = description;
    }
    public Topic(int tutorID, String name, int years_of_experience, String description, boolean is_offered) {
        this.tutorID = tutorID;
        this.name = name;
        this.years_of_experience = years_of_experience;
        this.description = description;
        this.is_offered = is_offered;
    }

    public Topic(int ID, int tutorID, String name, int years_of_experience, String description, boolean is_offered) {
        this.ID = ID;
        this.tutorID = tutorID;
        this.name = name;
        this.years_of_experience = years_of_experience;
        this.description = description;
        this.is_offered = is_offered;
    }

    //getters and setters

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(int years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIs_offered() {
        return is_offered;
    }

    public void setIs_offered(boolean is_offered) {
        this.is_offered = is_offered;
    }

    public int getTutorID() {
        return tutorID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }

    public static int getOFFERING_MAX(){return OFFERING_MAX;}

    public static int getCREATION_MAX(){return CREATION_MAX;}

    @Override
    public String toString() {
        return "Topic{" +
                "ID=" + ID +
                ", tutorID=" + tutorID +
                ", name='" + name + '\'' +
                ", years_of_experience=" + years_of_experience +
                ", description='" + description + '\'' +
                ", is_offered=" + is_offered +
                '}';
    }
}
