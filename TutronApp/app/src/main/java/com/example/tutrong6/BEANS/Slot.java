package com.example.tutrong6.BEANS;

import java.time.LocalTime;

public class Slot {

    //attributs
    private int ID;
    private int avaibilityID;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean is_reserved;

    private final static String TIME_FORMAT = "hh:mm";
    //constructors

    public Slot(){}

    public Slot(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Slot(int avaibilityID, LocalTime startTime, LocalTime endTime)
    {
        this.avaibilityID = avaibilityID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Slot(int id, int avaibilityID, LocalTime startTime, LocalTime endTime)
    {
        this.ID = id;
        this.avaibilityID = avaibilityID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //getters and setters


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getAvaibilityID() {
        return avaibilityID;
    }

    public void setAvaibilityID(int avaibilityID) {
        this.avaibilityID = avaibilityID;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean getIs_reserved() {
        return is_reserved;
    }

    public void setIs_reserved(boolean is_reserved) {
        this.is_reserved = is_reserved;
    }

    public static String getTIME_FORMAT() {
        return TIME_FORMAT;
    }

    public static double getSlotDuration(Slot slot)
    {

        double result = -1;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalTime diff = slot.endTime.minusNanos(slot.startTime.toNanoOfDay());
            double temp1 = diff.getHour();
            double temp2 = diff.getMinute() /60.0;
            result = temp1+temp2;
        }

        return result;
    }

    @Override
    public String toString() {
        return "Slot{" +
                "avaibilityID=" + avaibilityID +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
