package com.example.tutrong6.GUI.ADMIN_interfaces;

public class ComplaintModel {
    int tutorID;
    int studentID;


    public ComplaintModel(int tutorID, int studentID) {
        this.tutorID = tutorID;
        this.studentID = studentID;
    }

    public int getTutorID() {
        return tutorID;
    }

    public int getStudentID() {
        return studentID;
    }
}
