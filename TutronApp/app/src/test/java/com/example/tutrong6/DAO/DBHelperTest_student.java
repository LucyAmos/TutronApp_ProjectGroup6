/*
package com.example.tutrong6.DAO;


import org.junit.Test;


import static org.junit.Assert.assertTrue;

import com.example.tutrong6.BEANS.Address;
import com.example.tutrong6.BEANS.Complaint;
import com.example.tutrong6.BEANS.CreditCard;
import com.example.tutrong6.BEANS.Student;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static org.junit.Assert.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;



public class DBHelperTest_student {
    private DBHelper DBHelper;
    private SQLiteDatabase database;



    Context mMockContext;

    @Test
    public void testAddCreditCard() {
        CreditCard creditCard = new CreditCard();
        creditCard.setHolder_name("Samuel Champagne");
        creditCard.setNum_card("2000 0005 2361 2985");
        creditCard.setExp_date("2029-11-25");
        creditCard.setCvc(123);

        DBHelper.addCreditCard(creditCard);
    }

    @Test
    public void testAddAddress() {
        Address address = new Address();
        address.setStreet_address("563 javac street");
        address.setCity("Ottawa");
        address.setRegion("Ontario");
        address.setPostal_code("K5R 6E9");
        address.setCountry("Canada");

        DBHelper.addAddress(address);
    }

    @Test
    public void testAddStudent() {
        Student student = new Student();
        student.setRoleID(2);
        student.setFirst_name("Samuel");
        student.setLast_name("Champagne");
        student.setEmail("samy@gmail.com");
        student.setPassword("8888");

        DBHelper.addStudent(student);

    }

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
    public void testCheckEmail() {
        String existingEmail= "samy@gmail.com";

        boolean result= DBHelper.checkEmail(existingEmail);
        assertTrue(result);
    }

    @Test
    public void testCheckEmailPassword() {
        String email= "samy@gmail.com";
        String password= "8888";

        boolean result= DBHelper.checkEmailPassword(email, password);

        assertTrue(result);
    }

}
*/