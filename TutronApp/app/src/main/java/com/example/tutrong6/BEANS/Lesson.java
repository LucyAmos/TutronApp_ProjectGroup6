package com.example.tutrong6.BEANS;


import java.util.Date;

public class Lesson {
    // attributes
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private enum Status{PENDING, APPROVED, REJECTED, COMPLETED};
    private int ID;
    private int studentID;
    private int tutorID;
    private int topicID;
    private Status status;
    private Date date_appointment;
    private Slot slot;
    private double price;

    private ReviewSystem review_system;

    //constructors

    public Lesson() {}
    public Lesson(int studentID, int tutorID, int topicID,  Date date_time_appointment, Slot slot,  ReviewSystem review_system) {
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.topicID = topicID;
        this.date_appointment = date_time_appointment;
        this.slot = slot;
        this.review_system = review_system;
    }

    public Lesson(int studentID, int tutorID, int topicID,  Date date_time_appointment, Slot slot, double price, ReviewSystem review_system) {
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.topicID = topicID;
        this.date_appointment = date_time_appointment;
        this.slot = slot;
        this.price = price;
        this.review_system = review_system;
    }
    public Lesson(int ID, int studentID, int tutorID, int topicID, Date date_time_appointment, Slot slot, double price, ReviewSystem review_system) {
        this.ID = ID;
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.topicID = topicID;
        this.date_appointment = date_time_appointment;
        this.slot = slot;
        this.price = price;
        this.review_system = review_system;

    }

    //getters and setters

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getTutorID() {
        return tutorID;
    }

    public void setTutorID(int tutorID) {
        this.tutorID = tutorID;
    }

    public int getTopicID() {
        return topicID;
    }

    public void setTopicID(int topicID) {
        this.topicID = topicID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate_appointment() {
        return date_appointment;
    }

    public void setDate_appointment(Date date_appointment) {
        this.date_appointment = date_appointment;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }


    public static String DATE_FORMAT()
    {
        return DATE_FORMAT;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ReviewSystem getReview_system() {
        return review_system;
    }

    public void setReview_system(ReviewSystem review_system) {
        this.review_system = review_system;
    }

    public static int getstatusIDByEnum(Status status)
    {

        switch(status){
            case PENDING:
                return 1;
            case APPROVED:
                return 2;
            case REJECTED:
                return 3;
            case COMPLETED:
                return 4;

        }
        return 0;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "ID=" + ID +
                ", studentID=" + studentID +
                ", tutorID=" + tutorID +
                ", topicID=" + topicID +
                ", status=" + status +
                ", date_time_appointment=" + date_appointment +
                ", slot=" + slot +
                ", Review_system=" + review_system +
                '}';
    }
}
