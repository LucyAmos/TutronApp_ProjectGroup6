package com.example.tutrong6.DAO;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import com.example.tutrong6.BEANS.Administrator;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.Student;
import com.example.tutrong6.BEANS.User;

import org.junit.Before;

public class UserTest {
    private User user_test = new User("Solar","Sun", "solarsun@outlook.com", "password");
    private Administrator Admin = new Administrator();
    private Student student= new Student();
    private Tutor tutor= new Tutor();


    @Test
    public void TestRole(){
        // set to right role id to admin
        //get role admin
        //user_test

        // set to wrong role id : tutor
        // try to add ad admin

        // set to wrong role id : student
        // try to add ad admin

        //assert

    }

   @Test
    public void ChangeInfo(){

       user_test.setEmail("sunlight@outlook.com");
       assertEquals("sunlight@outlook.com",user_test.getEmail());

       user_test.setFirst_name("Laura");
       assertEquals("Laura",user_test.getFirst_name());

       user_test.setLast_name("Johnson");
       assertEquals("Johnson",user_test.getLast_name());

       user_test.setPassword("New Password");
       assertEquals("New Password",user_test.getPassword());

   }

   @Test
    public void ChangeInfo_F(){

       user_test.setEmail("sunlight@outlook.com");
       boolean result = "0utlook@outlook.com" == user_test.getEmail();
       assertFalse(result);

       user_test.setFirst_name("Laura");
       boolean result1 = "The Doctor" == user_test.getFirst_name();
       assertFalse(result1);


       user_test.setLast_name("Johnson");
       boolean result2 = "The Doctor" == user_test.getLast_name();
       assertFalse(result2);

       user_test.setPassword("New Password");
       boolean result3 = "pass" == user_test.getPassword();
       assertFalse(result3);
   }




}
