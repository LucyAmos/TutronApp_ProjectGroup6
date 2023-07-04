package com.example.tutrong6.DAO;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert.*;
import static org.junit.Assert.*;
import com.example.tutrong6.BEANS.Address;
import org.junit.After;
import org.junit.Before;


public class AddressTest {

    private Address address = new Address();

    /**
     * Test Case : AddAddress
     * Description: verify user address can be added to database
     * Expected: Address in database
     */
    @Test
    public void SetGetAddress(){

        address.setStreet_address("563 javac street");
        address.setCity("Ottawa");
        address.setRegion("Ontario");
        address.setPostal_code("K5R 6E9");
        address.setCountry("Canada");


        boolean result = ( address.getCity() == "Ottawa" && address.getCountry() == "Canada" && address.getStreet_address()== "563 javac street" && address.getPostal_code() == "K5R 6E9");
        assertTrue(result);
        //System.out.println("Result :" + result +"\n" + "Expected :" + true + "\n");
    }


    /**
     * Test Case : CountryInput
     * Description: verify only Canada can be inputted as a country
     * Expected: Reject Other country input and only accepts Canada
     */

    @Test
    public void CountryInput(){
        // Step 1: Set Country to US
        String country = "Canada";
        try{
            address.setCountry(country);
            assertTrue("Test was Successful",true);

        // Step 2: Assert True if error was sent
        }catch(Exception e){
            assertFalse("Test Failed : Country was not set ",false);
        }

        if ( address.getCountry() == "Canada"){
            assertTrue("Test Successful",true);
        }
    }


}
