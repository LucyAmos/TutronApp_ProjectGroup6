package com.example.tutrong6.BEANS;

import java.util.Date;

public class ReviewSystem {
    //attributes
    private double rating;
    private boolean is_rating_anonymous;
    private Date rating_date;
    private boolean is_topic_reviewed;
    private String review;

    //constructors

    public ReviewSystem() {
    }

    public ReviewSystem(double rating, boolean is_rating_anonymous, Date rating_date, boolean is_topic_reviewed, String review) {
        this.rating = rating;
        this.is_rating_anonymous = is_rating_anonymous;
        this.rating_date = rating_date;
        this.is_topic_reviewed = is_topic_reviewed;
        this.review = review;
    }

    ///getters & setters

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
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

    public boolean getIs_topic_reviewed() {
        return is_topic_reviewed;
    }

    public void setIs_topic_reviewed(boolean is_topic_reviewed) {
        this.is_topic_reviewed = is_topic_reviewed;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    //

    @Override
    public String toString() {
        return "ReviewSystem{" +
                "rating=" + rating +
                ", is_rating_anonymous=" + is_rating_anonymous +
                ", rating_date=" + rating_date +
                ", is_topic_reviewed=" + is_topic_reviewed +
                ", review='" + review + '\'' +
                '}';
    }
}
