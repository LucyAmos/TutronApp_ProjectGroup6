package com.example.tutrong6.DAO;


import org.junit.Test;
import static org.junit.Assert.*;

import com.example.tutrong6.BEANS.CreditCard;
import com.example.tutrong6.BEANS.Student;
import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.BEANS.Address;



public class StudentTest {

    private User user_test = new User("Solar","Sun", "solarsun@outlook.com", "password");
    private Student student= new Student();
    private Address address = new Address();


    /**
     * Test Case : StudentAddress
     * Description: verify student address can be added to database
     * Expected: student Address in database
     */
    @Test
    public void StudentAddress(){
        //Step 1: set adress information
        address.setStreet_address("563 javac street");
        address.setCity("Ottawa");
        address.setRegion("Ontario");
        address.setPostal_code("K5R 6E9");
        address.setCountry("Canada");


        student.setAddress(address);

        //Step 2: assert Address
        assertEquals(address, student.getAddress());

    }

    /**
     * Test Case : Studentcard
     * Description: verify studentcredit card can be added to database
     * Expected: student credit card in database
     */
    @Test
    public void StudentCCard() {
        CreditCard creditCard = new CreditCard("2000 0005 2361 2985","Samuel Champagne","2029-11-25",123 );
        student.setCredit_card(creditCard);

        assertEquals(creditCard, student.getCredit_card());
    }


    /**
     * Test Case : StaticRole
     * Description: Static role of student == 2
     * Expected: 2
     */
    @Test
    public void StaticRole() {
        int expectedRoleID = 2;
        int actualRoleID = Student.getStaticRoleID();

        assertEquals(expectedRoleID, actualRoleID);
    }

}
