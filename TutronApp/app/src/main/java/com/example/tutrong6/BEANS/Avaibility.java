package com.example.tutrong6.BEANS;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Avaibility {

    //attribut

    private int ID;
    private int TutorID;
    private Date date;
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private ArrayList<Slot> slots;

    // constructors


    public Avaibility() {
    }

    public Avaibility(int tutorID, Date date, ArrayList<Slot> slots) {
        TutorID = tutorID;
        this.date = date;
        this.slots = slots;
    }

    public Avaibility(int ID, int tutorID, Date date, ArrayList<Slot> slots) {
        this.ID = ID;
        TutorID = tutorID;
        this.date = date;
        this.slots = slots;
    }

    //getters & setters

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTutorID() {
        return TutorID;
    }

    public void setTutorID(int tutorID) {
        TutorID = tutorID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ArrayList<Slot> getSlots() {
        return slots;
    }

    public void setSlots(ArrayList<Slot> slots) {
        this.slots = slots;
    }

    public void addSlot(Slot slot)
    {
        this.slots.add(slot);
    }

    @Override
    public String toString() {
        return "Avaibility{" +
                "ID=" + ID +
                ", TutorID=" + TutorID +
                ", date=" + date +
                ", slots=" + slots +
                '}';
    }
}
