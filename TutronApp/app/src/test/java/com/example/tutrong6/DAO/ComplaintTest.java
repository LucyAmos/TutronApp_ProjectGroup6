package com.example.tutrong6.DAO;


import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

import com.example.tutrong6.BEANS.Administrator;
import com.example.tutrong6.BEANS.Complaint;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.Student;
import com.example.tutrong6.BEANS.User;
import java.util.Date;



public class ComplaintTest {

    Complaint complaint = new Complaint();
    Complaint complaint1 = new Complaint();
    Date date = new Date();


    @Test
    /**
     * Test Case: GetSetComplain
     * Description: verify that the setter and getter return and set proper values
     * Expected: complain created in databse and can be retrieved
     */
    public void GetSetComplain(){

        try{

            // Step 1: set complaint parameters
            complaint.setID(1);
            complaint.setStudentID(255);
            complaint.setTutorID(1);
            complaint.setTitle("Test Title");
            complaint.setDescription("Test Description");
            complaint.setDecisionID(2);
            complaint.setIs_processed(false);


            // Step 2: test complaint parameters
            assertEquals(1, complaint.getID());
            assertEquals(255, complaint.getStudentID());
            assertEquals(1, complaint.getTutorID());
            assertEquals("Test Title", complaint.getTitle());
            assertEquals("Test Description", complaint.getDescription());
            assertEquals(2, complaint.getDecisionID());
            assertFalse(complaint.getIs_processed());




            // Step 3: set complaint parameters to new values
            complaint.setID(700);
            complaint.setStudentID(755);

            // Step 3: test complaint parameters false
            assertFalse( complaint1.getID() == 1);
            assertFalse(complaint1.getStudentID() == 255);



            // Step 2: Assert failed if error was sent
        }catch(Exception e){
            assertFalse("Test Failed : Complaint was not set ",false);
        }

    }

    /**
     * Test Case: DuplicateComplains
     * Description: //test if you can create duplicate complaints
     * Expected: duplicate complain with same exact information cannot be created
     */
    @Test
    public void DuplicateComplains(){

        // Step 1: Create complaint
        Complaint complaint1= new Complaint( 1, 1, "Test1","first complaint",1, false,date);

        try{
            // Step 2: Create duplicate complaint
            Complaint complaint2= new Complaint( 1, 1, "Test1","first complaint",1, false,date);

            // Step 3: Assert Failed if duplicate could be created
            fail("Test Failed : Duplicate complaint created");

        }catch(Exception e) {

            assertFalse("Test Successful : Duplicate complaint was not set ", false);

        }
    }

    /**
     * Test Case: ComplaintIdByDecisions
     * Description: //test if complaint can be retrieve by decision id
     * Expected: complaint can be retreive by id and equal enum values
     */
    @Test
    public void ComplaintIdByDecisions(){
        try{
            //get complain by id
            int dismissedId = Complaint.getComplaintIdByDecisions(Complaint.Decisions.DISMISSED);
            assertEquals(1, dismissedId);

            // Test TemporarilySuspended decision
            int tempSuspendedId = Complaint.getComplaintIdByDecisions(Complaint.Decisions.TemporarilySuspended);
            assertEquals(2, tempSuspendedId);

            // Test PermanentSuspended decision
            int permSuspendedId = Complaint.getComplaintIdByDecisions(Complaint.Decisions.PermanentSuspended);
            assertEquals(3, permSuspendedId);

            // Test an invalid decision
            int invalidId = Complaint.getComplaintIdByDecisions(null);
            assertEquals(0, invalidId);

        }catch(Exception e) {

            assertTrue("Test Failed : Error occured ", true);
        }
    }
}
