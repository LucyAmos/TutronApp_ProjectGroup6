package com.example.tutrong6.BEANS;

import java.time.Duration;
import java.util.Date;

public class Lesson {
    // attributes
    private static final String DATE_FORMAT = "dd/MM/yyyy hh:mm";
    private static final String RATING_DATE_FORMAT = "dd/MM/yyyy";
    private enum Status{PENDING, APPROVED, REJECTED};
    private int ID;
    private int studentID;
    private int tutorID;
    private int topicID;
    private Status status;
    private Date date_time_appointment;
    private Duration duration;
    private double rating;
    private double price;
    private boolean is_rating_anonymous;
    private Date rating_date;

    //constructors

    public Lesson() {}

    public Lesson(int studentID, int tutorID, int topicID, Status status, Date date_time_appointment, Duration duration) {
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.topicID = topicID;
        this.status = status;
        this.date_time_appointment = date_time_appointment;
        this.duration = duration;
    }
    public Lesson(int ID, int studentID, int tutorID, int topicID, Status status, Date date_time_appointment, Duration duration, double rating) {
        this.ID = ID;
        this.studentID = studentID;
        this.tutorID = tutorID;
        this.topicID = topicID;
        this.status = status;
        this.date_time_appointment = date_time_appointment;
        this.duration = duration;
        this.rating = rating;
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

    public Date getDate_time_appointment() {
        return date_time_appointment;
    }

    public void setDate_time_appointment(Date date_time_appointment) {
        this.date_time_appointment = date_time_appointment;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public boolean getIs_rating_anonymous() {
        return is_rating_anonymous;
    }

    public void setIs_rating_anonymous(boolean is_rating_anonymous) {
        this.is_rating_anonymous = is_rating_anonymous;
    }

    public Date getRating_date() {
        return rating_date;
    }

    public void setRating_date(Date rating_date) {
        this.rating_date = rating_date;
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
                ", date_time_appointment=" + date_time_appointment +
                ", duration=" + duration +
                ", rating=" + rating +
                ", price=" + price +
                ", is_rating_anonymous=" + is_rating_anonymous +
                ", rating_date=" + rating_date +
                '}';
    }
}
