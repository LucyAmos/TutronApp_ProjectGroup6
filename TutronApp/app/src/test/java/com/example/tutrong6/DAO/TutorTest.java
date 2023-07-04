package com.example.tutrong6.DAO;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import com.example.tutrong6.BEANS.Administrator;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.Student;
import com.example.tutrong6.BEANS.User;

public class TutorTest {
    private User user_test = new User("Solar","Sun", "solarsun@outlook.com", "password");
    private Tutor tutor= new Tutor();



    /**
     * Test Case : Native Language
     * Description: check if the language by tutor is properly registered
     * Expected: 3
     */
    @Test
    public void NativeLanguage() {
        String Language = "English";
        tutor.setNative_language(Language);

        assertEquals(Language, tutor.getNative_language());
    }

    /**
     * Test Case : StaticRole
     * Description: Static role of student == 3
     * Expected: 3
     */
    @Test
    public void StaticRole() {
        int expected= 3;
        int actual = Tutor.getStaticRoleID();

        assertEquals(expected, actual);
    }

}
