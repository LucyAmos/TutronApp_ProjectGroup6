package com.example.tutrong6.DAO;


import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;
import com.example.tutrong6.BEANS.Slot;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

import com.example.tutrong6.BEANS.Slot;

public class SlotTest {

    Slot slot = new Slot();

    @Test
    public void testGetSet() {

        int expectedID = 1;
        int expectedAvailabilityID = 2;


        LocalTime StartTime = LocalTime.of(9, 0);
        LocalTime EndTime = LocalTime.of(10, 0);


        slot.setAvaibilityID(expectedAvailabilityID);
        slot.setStartTime(StartTime);
        slot.setEndTime(EndTime);
        slot.setIs_reserved(true);

        int actualID = slot.getID();
        int actualAvailabilityID = slot.getAvaibilityID();
        LocalTime actualStartTime = slot.getStartTime();
        LocalTime actualEndTime = slot.getEndTime();
        boolean actualIsReserved = slot.getIs_reserved();

        assertEquals(expectedID, actualID);
        assertEquals(expectedAvailabilityID, actualAvailabilityID);
        assertEquals(StartTime, actualStartTime);
        assertEquals(EndTime, actualEndTime);
        assertEquals(true,actualIsReserved);
    }

    @Test
    public void testGetDuration() {
        LocalTime startTime = LocalTime.of(14, 30);
        LocalTime endTime = LocalTime.of(22, 30);

        Slot slot_test = new Slot(startTime, endTime);

        double expectedDuration = 1.0;
        double actualDuration = Slot.getSlotDuration(slot_test);

        assertEquals(expectedDuration, actualDuration,0.1);
    }

    @Test
    public void testToString() {
        int availabilityID = 1;
        LocalTime startTime = LocalTime.of(7, 0);
        LocalTime endTime = LocalTime.of(15, 0);

        Slot slot_test3 = new Slot(availabilityID, startTime, endTime);

        String expectedString = "Slot{" + "avaibilityID=" + availabilityID + ", startTime=" + startTime + ", endTime=" + endTime + '}';
        String actualString = slot_test3.toString();

        assertEquals(expectedString, actualString);
    }
}

