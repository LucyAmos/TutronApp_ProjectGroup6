package com.example.tutrong6.GUI.ADMIN_interfaces;

public class ComplaintModel {
    String title;
    String sneak_peek;
    String complaint_id;


    public ComplaintModel(String title, String sneak_peek, String complaint_id) {
        this.title = title;
        this.sneak_peek = sneak_peek;
        this.complaint_id = complaint_id;
    }

    public String getTitle() {
        return title;
    }

    public String getSneak_peek() {
        return sneak_peek;
    }

    public String getComplaint_id() {
        return complaint_id;
    }
}
