package com.example.tutrong6.DAO;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import com.example.tutrong6.BEANS.Avaibility;
import com.example.tutrong6.BEANS.Tutor;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import com.example.tutrong6.BEANS.Slot;


public class AvailabilityTest {
    private Avaibility AvailabilityTest = new Avaibility();
    private Tutor tutor= new Tutor();

    @Test
    public void SetGetAvailability(){

        //Step 1: Initialize and set variables
        int Expected_tutorid = 15;
        int Expected_Availabilityid = 1;

        Date Expected_testDate = new Date();
        ArrayList<Slot> Expected_testSlots = new ArrayList<>();

        AvailabilityTest.setID(Expected_Availabilityid);
        AvailabilityTest.setTutorID(Expected_tutorid);
        AvailabilityTest.setDate(Expected_testDate);
        AvailabilityTest.setSlots(Expected_testSlots);

        //Step 2: Get variable
        int actualID = AvailabilityTest.getID();
        int actualTutorID = AvailabilityTest.getTutorID();
        Date actualDate = AvailabilityTest.getDate();
        ArrayList<Slot> actualSlots = AvailabilityTest.getSlots();

        //Step 3: Assert that value set are the value received
        assertEquals(Expected_Availabilityid, actualID);
        assertEquals(Expected_tutorid, actualTutorID);
        assertEquals(Expected_testDate, actualDate);
        assertEquals(Expected_testSlots, actualSlots);
    }
    @Test
    public void TestSlot(){

            //Step 1: Initialize and set variables for slot
            Avaibility Availability = new Avaibility(1, new Date(), new ArrayList<>());
            Slot slot = new Slot(LocalTime.of(10, 0), LocalTime.of(18, 0));

            //Step 2:  Add variable to slot
            Availability.addSlot(slot);
            ArrayList<Slot> slots = Availability.getSlots();

            //Step 3: Assert that value set are the value received
            assertTrue("Slot not added",slots.contains(slot));

        }

}
