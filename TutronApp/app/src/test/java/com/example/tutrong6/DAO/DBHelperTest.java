package com.example.tutrong6.DAO;

import android.database.sqlite.SQLiteDatabase;

import junit.framework.TestCase;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import com.example.tutrong6.BEANS.Address;
import com.example.tutrong6.BEANS.Complaint;
import com.example.tutrong6.BEANS.CreditCard;
import com.example.tutrong6.BEANS.Student;
import com.example.tutrong6.BEANS.Tutor;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;


public class DBHelperTest extends TestCase {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

    //@Before
    public void setUp() throws Exception {
        //super.setUp();


    }

   // public void tearDown() throws Exception {
   // }

   // public void testOnCreate() {
    //}

   // public void testOnUpgrade() {
   // }

    @Test
    public void testAddCreditCard() {
        CreditCard creditCard= new CreditCard();
        creditCard.setHolder_name("Samuel Champagne");
        creditCard.setNum_card("2000 0005 2361 2985");
        creditCard.setExp_date("2029-11-25");
        creditCard.setCvc(123);

        dbHelper.addCreditCard(creditCard);
    }

    @Test
    public void testAddAddress() {
        Address address= new Address();
        address.setStreet_address("563 javac street");
        address.setCity("Ottawa");
        address.setRegion("Ontario");
        address.setPostal_code("K5R 6E9");
        address.setCountry("Canada");

        dbHelper.addAddress(address);
    }

    @Test
    public void testAddStudent() {
        Student student= new Student();
        student.setRoleID(2);
        student.setFirst_name("Samuel");
        student.setLast_name("Champagne");
        student.setEmail("samy@gmail.com");
        student.setPassword("8888");

        dbHelper.addStudent(student);

        //addStudent operation
       // boolean result= DBHelper.addStudent(student);

        //assert result
        // assertTrue(result);
    }

   // public void testGetAddressByID() {

   // }

   // public void testGetCreditCardByID() {
  //  }

    @Test
    public void testAddTutor() {
        Tutor tutor= new Tutor();

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

        assertNotNull(dbHelper);

        Boolean result= dbHelper.addTutor(tutor);

        assertTrue(result);
        //dbHelper.addTutor(tutor);

    }


    @Test
    public void testCheckEmail() {
        String existingEmail= "samy@gmail.com";

        boolean result= dbHelper.checkEmail(existingEmail);
        assertTrue(result);
    }

    @Test
    public void testCheckEmailPassword() {
        String email= "samy@gmail.com";
        String password= "8888";

        boolean result= dbHelper.checkEmailPassword(email, password);

        assertTrue(result);
    }

   // public void testGetUserLoggedInfoByEmail() {
  //  }

   // public void testGetUserbyID() {
   // }

   // public void testGetRoleNameByRoleID() {
  //  }

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

        boolean result= dbHelper.addComplaint(complaint);

        assertTrue(result);
    }

    @Test
    public void testMake_complaint_decision() {
        int Complaint= 1;
        int decisionID= 2;
        Date dateRetourSuspension= new Date();

        //calling the method to make a complaint decision
        boolean result= dbHelper.make_complaint_decision(Complaint, decisionID, dateRetourSuspension);

        //Complaint.Decisions decisions= Complaint.Decisions.DISMISSED;
       // java.util.Date dateRetourSuspension= new java.util.Date();

       // boolean result= dbHelper.make_complaint_decision(complaintId, Complaint.Decisions, dateRetourSuspension);

        assertTrue(result);
    }

    //private Complaint getComplaintById(int complaintId) {
    //}

   // }

    @Test
    public void testActiveComplaintsList() {
        ArrayList<Complaint> activeComplaint= dbHelper.activeComplaintsList();
        assertNotNull(activeComplaint);
        assertNotNull(activeComplaint.size()>0);

    }

    @Test
    public void testTutorSuspensionStatusByUserID() {
        int tutorID= 4;

        tutorID = 3;

        boolean result= dbHelper.tutorSuspensionStatusByUserID(tutorID);

        assertFalse(result);
    }

   // public void testGetDecisionByDecisionID() {
  //  }

    @Test
    public boolean testInfoEtatSsuspension() {
        int tutorID= 5;
        tutorID = 6;

        Map<String, Date> result= dbHelper.infoEtatSsuspension(tutorID);

        assertNotNull(result);
        //assertFalse(result.isEmpty());
        return result.isEmpty();
    }

    @Test
    public void testUpdateTutorSuspensionState() {
        int tutorID= 5;
        tutorID = 6;

        boolean isSuspendedState= true;

        boolean result= dbHelper.updateTutorSuspensionState(tutorID, isSuspendedState);
        assertTrue(result);
    }
}