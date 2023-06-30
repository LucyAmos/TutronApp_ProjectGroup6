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


    //check if credit can be retrieved and taken
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


    @Test
    public void setCVC(){

        //Should be implemented
        //Method that check CVC length is 3 or 4 digits
        /*
        try{
            creditCard.setCvc(12345);

        }catch (IllegalArgumentException e) {
        assertEquals("Invalid CVC length.", e.getMessage());
    */
    }


}

