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

    private Address Address_test = new Address();

    @Test
    public void AddAddress(){
        Address address= new Address();
        address.setStreet_address("563 javac street");
        address.setCity("Ottawa");
        address.setRegion("Ontario");
        address.setPostal_code("K5R 6E9");
        address.setCountry("Canada");


        boolean result = ( address.getCity() == "Ottawa" && address.getCountry() == "Canada" && address.getStreet_address()== "563 javac street" && address.getPostal_code() == "K5R 6E9");
        assertTrue(result);
        System.out.println("Result :" + result +"\n" + "Expected :" + true + "\n");
    }


    @Test
    public void uniqueID(){
        //check if unique id in database
    }

    @Test
    public void CountryList(){
        // if dropdown we have to check if country in list
    }


}
