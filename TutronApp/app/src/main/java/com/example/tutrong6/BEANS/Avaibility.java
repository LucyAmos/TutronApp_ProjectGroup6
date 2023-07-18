package com.example.tutrong6.BEANS;

import android.os.Build;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Avaibility {

    //attribut

    private int ID;
    private int TutorID;
    private Date date;
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    private ArrayList<Slot> slots;

    // constructors


    public Avaibility() {
    }

    public Avaibility( Date date, ArrayList<Slot> slots) {
        this.date = date;
        this.slots = slots;
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

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
//minus number would decrement the days
        return cal.getTime();
    }

    public static ArrayList<Avaibility> DefaultAvaibility()
    {
        ArrayList<Avaibility> result = new ArrayList<Avaibility>();
        ArrayList<Slot> slots = new ArrayList<Slot>();
        //8:00-10:00, 10:30-12:30, 13:00-15:00, 15:30-17:30, 18:00-20:00, 20:30-22:30
        //Call requires API level 26
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            slots.add(new Slot(LocalTime.parse("08:00"),LocalTime.parse("10:00")));
            slots.add(new Slot(LocalTime.parse("10:30"),LocalTime.parse("12:30")));
            slots.add(new Slot(LocalTime.parse("13:00"),LocalTime.parse("15:00")));
            slots.add(new Slot(LocalTime.parse("15:30"),LocalTime.parse("17:30")));
            slots.add(new Slot(LocalTime.parse("18:00"),LocalTime.parse("20:00")));
            slots.add(new Slot(LocalTime.parse("20:30"),LocalTime.parse("22:30")));

            int duration_in_days = 21;

            ArrayList<Date> dates = new ArrayList<Date>();


            Date myDate = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
            String date_str = String.valueOf(formatter.format(myDate));

            try {
                myDate = new SimpleDateFormat(DATE_FORMAT).parse(date_str);

                for(int i =0; i <duration_in_days; i++)
                {
                    myDate = addDays(myDate, 1);
                    System.out.println(myDate);
                    dates.add(myDate);
                }
            }
            catch (Exception e) {
                 Log.e("ParseException", "activeComplaintsList: "+ e.getStackTrace() );

            }

            for (Date date : dates)
            {
                Avaibility temp = new Avaibility(date, slots);
                result.add(temp);
            }


        }



        return result;
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
