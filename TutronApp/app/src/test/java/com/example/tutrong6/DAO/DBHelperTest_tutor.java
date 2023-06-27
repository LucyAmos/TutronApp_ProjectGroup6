package com.example.tutrong6.DAO;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;
import com.example.tutrong6.BEANS.Tutor;


import static org.junit.Assert.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import static org.junit.Assert.*;

public class DBHelperTest_tutor {

    private DBHelper DBHelper;
    private SQLiteDatabase database;

    @Mock
    Context mMockContext;

    @Test
    public void testAddTutor() {
        Tutor tutor = new Tutor();

        tutor.setRoleID(3);
        tutor.setFirst_name("Kris");
        tutor.setLast_name("Klool");
        tutor.setEmail("kk@gmail.com");
        tutor.setPassword("0000");
        tutor.setEducation_level("Master");
        tutor.setNative_language("english");
        tutor.setDescription("extremely patient tutor");
        tutor.setIs_suspended(false);

        tutor.setRoleID(3);
        tutor.setFirst_name("Gordon");
        tutor.setLast_name("Loutou");
        tutor.setEmail("gl@gmail.com");
        tutor.setPassword("0000");
        tutor.setEducation_level("Bachelor");
        tutor.setNative_language("french");
        tutor.setDescription("provide clear and simple explanation");
        // tutor.setIs_suspended(true);

        tutor.setRoleID(3);
        tutor.setFirst_name("Roodie");
        tutor.setLast_name("Clok");
        tutor.setEmail("rc@gmail.com");
        tutor.setPassword("9999");
        tutor.setEducation_level("Master");
        tutor.setNative_language("french");
        tutor.setDescription("adore teaching");
        //tutor.setIs_suspended(true);


        tutor.setRoleID(3);
        tutor.setFirst_name("Puistas");
        tutor.setLast_name("Coukap");
        tutor.setEmail("tut@gmail.com");
        tutor.setPassword("5555");
        tutor.setEducation_level("Phd");
        tutor.setNative_language("french");
        tutor.setDescription("science enthousiast at your service");
        //tutor.setIs_suspended(true);

        assertNotNull(DBHelper);

        Boolean result = DBHelper.addTutor(tutor);

        assertTrue(result);


    }



}