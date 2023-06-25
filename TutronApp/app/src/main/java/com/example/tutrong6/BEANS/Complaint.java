package com.example.tutrong6.BEANS;

import java.util.Date;

public class Complaint {

    // attibutes

    private static final String DATE_FORMAT = "dd/MM/yyyy";
    public enum Decisions{DISMISSED,TemporarilySuspended,PermanentSuspended};
    private int ID;
    private int StudentID;
    private int TutorID;
    private String title;

    private String description;
    private int decisionID;
    private boolean is_processed;
    private Date suspension_end_date;

    //constructors

    public Complaint() {

    }
    public Complaint( int studentID, int tutorID, String title, String description,int decision, boolean id_processed, Date suspension_end_date) {
        StudentID = studentID;
        TutorID = tutorID;
        this.title = title;
        this.description = description;
        this.decisionID = decision;
        this.is_processed = id_processed;
        this.suspension_end_date = suspension_end_date;
    }

    //getters & setters


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int studentID) {
        StudentID = studentID;
    }

    public int getTutorID() {
        return TutorID;
    }

    public void setTutorID(int tutorID) {
        TutorID = tutorID;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDecisionID() {
        return decisionID;
    }

    public void setDecisionID(int decisionID) {
        this.decisionID = decisionID;
    }

    public boolean getIs_processed() {
        return is_processed;
    }

    public void setIs_processed(boolean is_processed) {
        this.is_processed = is_processed;
    }

    public Date getSuspension_end_date() {
        return suspension_end_date;
    }

    public void setSuspension_end_date(Date suspension_end_date) {
        this.suspension_end_date = suspension_end_date;
    }

    public static String getDATE_FORMAT() {
        return DATE_FORMAT;
    }


    @Override
    public String toString() {
        return "Complaint{" +
                "ID=" + ID +
                ", StudentID=" + StudentID +
                ", TutorID=" + TutorID +
                ", title='" + title + '\'' +
                ", description=" + description +
                ", decision=" + decisionID +
                ", id_processed=" + is_processed +
                ", suspension_end_date=" + suspension_end_date +
                '}';
    }
}
