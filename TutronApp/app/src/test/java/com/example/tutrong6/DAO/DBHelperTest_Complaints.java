package com.example.tutrong6.DAO;

import static org.junit.Assert.*;
import android.content.Context;


import android.database.sqlite.SQLiteDatabase;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;

import com.example.tutrong6.BEANS.Complaint;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class DBHelperTest_Complaints {

    private DBHelper DBHelper;
    private SQLiteDatabase database;


    @Mock
    Context mMockContext;


    @Test
    public void testAddComplaint() {
        Complaint complaint = new Complaint();

        complaint.setStudentID(7);
        complaint.setTutorID(3);
        complaint.setTitle("NO NO");
        complaint.setDescription("always make mistake in his explanation");
        complaint.setIs_processed(false);
        //complaint.setDecisionID(null);
        complaint.setSuspension_end_date(null);

        complaint.setStudentID(7);
        complaint.setTutorID(4);
        complaint.setTitle("Extremely mediocre as a tutor");
        complaint.setDescription("Not patient at all");
        complaint.setIs_processed(false);
        //complaint.setDecisionID(null);
        complaint.setSuspension_end_date(null);

        complaint.setStudentID(7);
        complaint.setTutorID(5);
        complaint.setTitle("Horrible Experience");
        complaint.setDescription("always yelling at me and make me feel stupid");
        complaint.setIs_processed(true);
        // complaint.setDecisionID(null);
        complaint.setSuspension_end_date(null);

        complaint.setStudentID(7);
        complaint.setTutorID(6);
        complaint.setTitle("SPEACHLESS");
        complaint.setDescription("zero patience");
        complaint.setIs_processed(true);
        // complaint.setDecisionID(null);
        complaint.setSuspension_end_date(null);

        boolean result= DBHelper.addComplaint(complaint);

        assertTrue(result);
    }

    @Test
    public void testMake_complaint_decision() {
        int Complaint= 1;
        int decisionID= 2;
        Date dateRetourSuspension= new Date();

        //calling the method to make a complaint decision
        boolean result= DBHelper.make_complaint_decision(Complaint, decisionID, dateRetourSuspension);

        //Complaint.Decisions decisions= Complaint.Decisions.DISMISSED;
        // java.util.Date dateRetourSuspension= new java.util.Date();

        // boolean result= dbHelper.make_complaint_decision(complaintId, Complaint.Decisions, dateRetourSuspension);

        assertTrue(result);
    }



}