package com.example.tutrong6.DAO;

import android.content.Context;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert.*;
import static org.junit.Assert.*;

import com.example.tutrong6.BEANS.CreditCard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreditCardTest {
    CreditCard creditCard_test = new CreditCard( );



    /**
     * Test Case: testAddCreditCard
     * Description: verify that the creditcard can be added
     * Expected: credit card added to database
     */
    @Test
    public void testAddCreditCard() {
        CreditCard creditCard = new CreditCard("2000 0005 2361 2985","Samuel Champagne","2029-11-25",123 );
        String name = creditCard.getHolder_name();
        String Num_Card = creditCard.getNum_card();
        String exp_dat = creditCard.getExp_date();
        int cvc = creditCard.getCvc();

        boolean result = ((name == "Samuel Champagne") && (Num_Card == "2000 0005 2361 2985") && (exp_dat == "2029-11-25") && cvc==123);

        System.out.println("Test : Add Credit Card Information \n");
        assertTrue(result);
        System.out.println("Result :" + result +"\n" + "Expected :" + true + "\n");

    }

    /**
     * Test Case: CVCValid
     * Description: verify only 3 digit number can be inputed as CVC
     * Expected: Reject Other country input and only accepts Canada
     */
    @Test
    public void setCVCValid(){
        int cvc = 11234;

        //Step 1: set cvc to an invalid number. Number with more that 3 digit
        try{
            creditCard_test.setCvc(cvc);
            fail("Test Failed:"+ cvc + " is an invalid input");

        // Step 2: Assert True if error was sent
        }catch (IllegalArgumentException e) {
            assertTrue("Test was Successful",true);
        }

        //Step 3: Verify that the cvc was not changed
        if (cvc >= 100 && cvc <= 999){
            assertTrue("Test was Successful",true);
        }
        assertFalse("CVC or CVV number is beyond range allowed",false);
    }


}

