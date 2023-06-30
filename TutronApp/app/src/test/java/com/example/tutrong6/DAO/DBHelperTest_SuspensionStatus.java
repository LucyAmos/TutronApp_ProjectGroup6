/*
package com.example.tutrong6.DAO;

import static org.junit.Assert.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.tutrong6.BEANS.Complaint;

import org.junit.Test;


import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class DBHelperTest_SuspensionStatus {

    private DBHelper DBHelper;
    private SQLiteDatabase database;



    Context mMockContext;


    @Test
    public void testActiveComplaintsList() {
        ArrayList<Complaint> activeComplaint= DBHelper.activeComplaintsList();
        assertNotNull(activeComplaint);
        assertNotNull(activeComplaint.size()>0);

    }

    @Test
    public void testTutorSuspensionStatusByUserID() {
        int tutorID= 4;

        tutorID = 3;

        boolean result= DBHelper.tutorSuspensionStatusByUserID(tutorID);

        assertFalse(result);
    }

    // public void testGetDecisionByDecisionID() {
    //  }

    @Test
    public boolean testInfoEtatSsuspension() {
        int tutorID= 5;
        tutorID = 6;

        Map<String, Date> result= DBHelper.infoEtatSsuspension(tutorID);

        assertNotNull(result);
        //assertFalse(result.isEmpty());
        return result.isEmpty();
    }

    @Test
    public void testUpdateTutorSuspensionState() {
        int tutorID= 5;
        tutorID = 6;

        boolean isSuspendedState= true;

        boolean result= DBHelper.updateTutorSuspensionState(tutorID, isSuspendedState);
        assertTrue(result);
    }







}
*/
