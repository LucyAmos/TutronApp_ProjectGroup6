package com.example.tutrong6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tutrong6.BEANS.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DBHelper extends SQLiteOpenHelper {

//attribut
    private static final String DATABASE_NAME = "tutronDB";
    private static final int DATABASE_VERSION = 2;

    // creation de la base de donnee
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


//creation des differentes tables

        //table carte de credit
        sqLiteDatabase.execSQL("CREATE TABLE creditcard (\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  holder_name TEXT NOT NULL,\n" +
                "  card_number TEXT NOT NULL,\n" +
                "  expiration_date TEXT NOT NULL,\n" +
                "  cvc INTEGER NOT NULL\n" +
                ")");

        //table address
        sqLiteDatabase.execSQL("CREATE TABLE address (\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  street_address TEXT NOT NULL,\n" +
                "  city TEXT NOT NULL,\n" +
                "  region TEXT NOT NULL,\n" +
                "  postal_code TEXT NOT NULL,\n" +
                "  country TEXT NOT NULL\n" +
                ")");

        //table role
        sqLiteDatabase.execSQL("CREATE TABLE role (\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  name TEXT NOT NULL\n" +
                ")");

        //table user
        sqLiteDatabase.execSQL("CREATE TABLE user (\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  roleID INTEGER NOT NULL,\n" +

                "  first_name TEXT NOT NULL,\n" +
                "  last_name TEXT NOT NULL,\n" +
                "  email TEXT NOT NULL,\n" +
                "  password TEXT NOT NULL,\n" +

                "  education_level TEXT DEFAULT NULL,\n" +
                "  native_language TEXT DEFAULT NULL,\n" +
                "  description TEXT DEFAULT NULL,\n" +
                "  profile_picture BLOB DEFAULT NULL,\n" +

                "  addressID INTEGER DEFAULT NULL,\n" +
                "  credit_card_id INTEGER DEFAULT NULL,\n" +

                "  is_suspended BOOLEAN DEFAULT 0,\n" +


                "  FOREIGN KEY (roleID) REFERENCES role (ID),\n" +
                "  FOREIGN KEY (credit_card_id) REFERENCES creditcard (ID),\n" +
                "  FOREIGN KEY (addressID) REFERENCES address (ID)\n" +
                "\n" +
                ")");

        //decision table

        sqLiteDatabase.execSQL("CREATE TABLE decision (\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  name TEXT NOT NULL\n" +
                ")");

        // complaint table
        sqLiteDatabase.execSQL("CREATE TABLE complaint(\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  StudentID INTEGER NOT NULL,\n" +
                "  TutorID INTEGER NOT NULL,\n" +
                "  title TEXT NOT NULL,\n" +
                "  description TEXT NOT NULL,\n" +
                "  is_processed BOOLEAN NOT NULL,\n" +
                "  DecisionsID INTEGER DEFAULT NULL,\n" +
                "  suspension_end_date TEXT DEFAULT NULL,\n" +
                "  FOREIGN KEY (StudentID) REFERENCES user (ID),\n" +
                "  FOREIGN KEY (TutorID) REFERENCES user (ID),\n" +
                "  FOREIGN KEY (DecisionsID) REFERENCES decision (ID)\n" +
                ")");




        //POPULER LES TABLES

        //add the 3 roles of an user in the database
        sqLiteDatabase.execSQL("INSERT INTO role (name) VALUES\n" +
                "(\"Administrator\"),\n" +
                "(\"Student\"),\n" +
                "(\"Tutor\")");

        //populate some admins in the database
        sqLiteDatabase.execSQL("INSERT INTO user (roleID, first_name, last_name, email, password, education_level, native_language, description, profile_picture, addressID, credit_card_id) VALUES\n" +
                "( 1, 'Admin1', 'Java', 'admin1@tutron.ca', '1234', NULL, NULL, NULL, NULL, NULL, NULL),\n" +
                "( 1, 'Admin2', 'Android', 'admin2@tutron.ca', '5678', NULL, NULL, NULL, NULL, NULL, NULL)");


        //add 3 possibles decision for a complaint
        sqLiteDatabase.execSQL("INSERT INTO decision (name) VALUES\n" +
                "(\"DISMISSED\"),\n" +
                "(\"TEMPORARILY SUSPENDED\"),\n" +
                "(\"PERMANENT SUSPENDED\")");


        //populate some tutors
        sqLiteDatabase.execSQL("INSERT INTO user (roleID, first_name, last_name, email, password, education_level, native_language, description, profile_picture, addressID, credit_card_id, is_suspended) VALUES\n" +
                "( 3, 'Kris', 'Klool','kk@gmail.com', '0000', 'Master', 'english', 'extremely patient tutor', NULL, NULL,NULL, 0),\n" +
                "( 3, 'Gordon', 'Loutou', 'gl@gmail.com', '0000', 'Bachelor', 'french', 'provide clear and simple explanation', NULL, NULL,NULL, 0),\n" +
                "( 3, 'Puistas', 'Coukap', 'tut@gmail.com', '5555', 'Phd', 'english', 'science enthousiast at your service', NULL, NULL,NULL, 1),\n" +
                "( 3, 'Roodie', 'Clok','rc@gmail.com', '9999', 'Master', 'french', 'adore teaching ', NULL, NULL,NULL, 1)");

        //populate A student

        //create address

        sqLiteDatabase.execSQL("INSERT INTO creditcard (holder_name, card_number, expiration_date, cvc) VALUES\n" +
                "                ('Samuel Champagne','2000 0005 2361 2985', '2029-11-25', 123)");

        //create credit card
        sqLiteDatabase.execSQL("INSERT INTO address (street_address, city, region, postal_code, country) VALUES\n" +
                "                ('563 javac street','Ottawa', 'Ontario', 'K5R 6E9', 'Canada')");

        //create student
        sqLiteDatabase.execSQL("INSERT INTO user (roleID, first_name, last_name, email, password, education_level, native_language, description, profile_picture, addressID, credit_card_id, is_suspended) VALUES\n" +
                "( 2, 'Samuel', 'Champagne','samy@gmail.com', '8888', NULL, NULL, NULL, NULL, 1,1, 0)");

        // populate some complaintS
        sqLiteDatabase.execSQL("INSERT INTO complaint (StudentID, TutorID, title, description, is_processed, DecisionsID, suspension_end_date) VALUES\n" +
                "(7, 3, 'NO NO', 'always make mistake in his explanation', 0, NULL, NULL),\n" +
                "(7, 4, 'Extremely mediocre as a tutor', 'Not patient at all', 0, NULL, NULL),\n" +
                "(7, 5, 'Horrible Experience', 'always yelling at me and make me feel stupid', 1, 3, NULL),\n" +
                "(7, 6, 'SPEACHLESS', 'zero patience', 1, 2, '28/12/2023')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists creditcard");
        sqLiteDatabase.execSQL("drop table if exists address");
        sqLiteDatabase.execSQL("drop table if exists role");
        sqLiteDatabase.execSQL("drop table if exists user");

    }


    //region FUNCTIONS DELIVERABLE 1

    /**
     *
     * @param credit_card insert this credit card Object in the DB
     */
    public void addCreditCard(CreditCard credit_card)
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("holder_name",credit_card.getHolder_name());
        contentValues.put("card_number",credit_card.getNum_card());
        contentValues.put("expiration_date",credit_card.getExp_date());
        contentValues.put("cvc",credit_card.getCvc());
        long result = MyData.insert("creditcard",null,contentValues);

    }

    /**
     *
     * @param address add an address in te DB
     */
    public void addAddress(Address address) {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("street_address",address.getStreet_address());
        contentValues.put("city",address.getCity());
        contentValues.put("region",address.getRegion());
        contentValues.put("postal_code",address.getPostal_code());
        contentValues.put("country ",address.getCountry());
        long result = MyData.insert("address",null,contentValues);

    }

    /**
     *
     * @param student student object that we will add in the DB
     * @return true if the student was successfully added and false if not
     */
    public Boolean addStudent(Student student)
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        //create the credit card
        this.addCreditCard(student.getCredit_card());
        //get the last creditCard object create
        int credit_card_id = 0;
        Cursor cursor = MyData.rawQuery("SELECT * FROM creditcard ORDER BY ID DESC LIMIT 1 ", null);
        if (cursor.moveToFirst()){
            do {
                // Passing values
                credit_card_id = cursor.getInt(0);
            } while(cursor.moveToNext());
        }

        //create the address
        this.addAddress(student.getAddress());
        //get the last creditCard object create
        int address_id = 0;
        cursor = MyData.rawQuery("SELECT * FROM address ORDER BY ID DESC LIMIT 1 ", null);
        if (cursor.moveToFirst()){
            do {
                // Passing values
                address_id = cursor.getInt(0);
            } while(cursor.moveToNext());
        }

        //adding student's info in the dataBase

        ContentValues contentValues = new ContentValues();

        contentValues.put("roleID",student.getStaticRoleID());
        contentValues.put("first_name",student.getFirst_name());
        contentValues.put("last_name",student.getLast_name());
        contentValues.put("email",student.getEmail());
        contentValues.put("password",student.getPassword());

        contentValues.put("addressID",address_id);
        contentValues.put("credit_card_id",credit_card_id);

        long result = MyData.insert("user",null,contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    /**
     *
     * @param id
     * @return the address of the
     */
    public Address getAddressByID(int id)
    {
        Address address = new Address();

        SQLiteDatabase MyData = this.getWritableDatabase();

        Cursor res = MyData.rawQuery("SELECT * FROM address WHERE ID = ? ",new String[] {String.valueOf(id)});
        while (res.moveToNext())

        {
            address.setID(res.getInt(0));
            address.setStreet_address(res.getString(1));
            address.setCity(res.getString(2));
            address.setRegion(res.getString(3));
            address.setPostal_code(res.getString(4));
            address.setCountry(res.getString(5));
        }


        return address;

    }

    /**
     *
     * @param id
     * @return
     */
    public CreditCard getCreditCardByID(int id)
    {
        CreditCard creditCard = new CreditCard();

        SQLiteDatabase MyData = this.getWritableDatabase();

        Cursor res = MyData.rawQuery("SELECT * FROM creditcard WHERE ID = ? ",new String[] {String.valueOf(id)});
        while (res.moveToNext())

        {
            creditCard.setID(res.getInt(0));
            creditCard.setHolder_name(res.getString(1));
            creditCard.setNum_card(res.getString(2));
            creditCard.setExp_date(res.getString(3));
            creditCard.setCvc(res.getInt(4));
        }

        return creditCard;

    }


    /**
     *
     * @param tutor tutor object that we will add in the DB
     * @return true if the tutor was successfully added and false if not
     */
    public Boolean addTutor(Tutor tutor)
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.i("INSERT ", "DANS ADD-TUTOR: ");

        contentValues.put("roleID",tutor.getStaticRoleID());
        contentValues.put("first_name",tutor.getFirst_name());
        contentValues.put("last_name",tutor.getLast_name());
        contentValues.put("email",tutor.getEmail());
        contentValues.put("password",tutor.getPassword());

        contentValues.put("education_level",tutor.getEducation_level());
        contentValues.put("native_language",tutor.getNative_language());
        contentValues.put("description",tutor.getDescription());

        if(tutor.getProfile_picture() !=null)
        {
            contentValues.put("profile_picture",tutor.getProfile_picture());
        }

        Log.i("INSERT ", "FINI LES CONTENT VALUES: ");

        long result = MyData.insert("user",null,contentValues);
        Log.i("INSERT ", "FINI D INSERER ");
        if(result==-1) return false;
        else
            return true;
    }



    /**
     *
     * @param email paramatre to check
     * @return true if the email exist in the DB
     */
    public Boolean checkEmail(String email){
        Log.i("email Value=", " IN CHECK EMAIL"+email);
        SQLiteDatabase MyData = this.getWritableDatabase();

        //SELECT email FROM user WHERE email = "admin1@mealer.ca"
        Cursor cursor = MyData.rawQuery("SELECT email FROM user WHERE email  = ?",new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


    /**
     *
     * @param email email of the user
     * @param password password of the user
     * @return true if there is a user in a the DB with this emial and password
     */
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyData = this.getWritableDatabase();
        //SELECT email, password FROM user WHERE email = "admin1@mealer.ca" AND PASSWORD ="admin1"
        Cursor cursor = MyData.rawQuery("SELECT email, password FROM user WHERE email = ? and password = ?",new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }


    /**
     *
     * @param email email of the logged user
     * @return all the informations of the user who has this email address
     */
    public User getUserLoggedInfoByEmail(String email)
    {
        User logged_user = new User();
        ArrayList<Object> infos_logged_user = new ArrayList<Object>();
        int logged_user_roleID =0;

        //int userID =0;

        SQLiteDatabase MyData = this.getWritableDatabase();
        //SELECT ID, nom FROM user WHERE email = "admin1@mealer.ca"

        Cursor res = MyData.rawQuery("SELECT * FROM user WHERE email = ? ",new String[] {email});
        while (res.moveToNext()) {

            infos_logged_user.add(res.getInt(0));    //ID
           // userID = res.getInt(0);
            infos_logged_user.add(res.getInt(1));   //roleID

            logged_user_roleID = res.getInt(1);

            infos_logged_user.add(res.getString(2));    //first_name
            infos_logged_user.add(res.getString(3));    //last_name
            infos_logged_user.add(res.getString(4));    //email
            infos_logged_user.add(res.getString(5));    //password

            infos_logged_user.add(res.getString(6));    //education_level
            infos_logged_user.add(res.getString(7));    //native_language
            infos_logged_user.add(res.getString(8));    //description
            infos_logged_user.add(res.getBlob(9));      //profile_picture

            infos_logged_user.add(res.getInt(10));  //addressID
            infos_logged_user.add(res.getInt(11));  //credit_card_id

        }

        User tempUser = new User( String.valueOf(infos_logged_user.get(2)),
                String.valueOf(infos_logged_user.get(3)),
                String.valueOf(infos_logged_user.get(4)),
                String.valueOf(infos_logged_user.get(5))
        );

        tempUser.setID((int) infos_logged_user.get(0));
        Log.e("user ID" ,  "ici " + infos_logged_user.get(0) + "userID= " );
        tempUser.setRoleID(logged_user_roleID);


      /*  if(logged_user_roleID == Administrator.getStaticRoleID())
        {
            logged_user = new Administrator(tempUser);
            return logged_user;
        }
        else if(logged_user_roleID == Student.getStaticRoleID())
        {

            logged_user = new Student(tempUser,
                    this.getAddressByID((int)infos_logged_user.get(10)),
                    this.getCreditCardByID((int)infos_logged_user.get(11))
            );

            return logged_user;
        }
        else if(logged_user_roleID == Tutor.getStaticRoleID())
        {
            logged_user = new Tutor(tempUser,
                    String.valueOf(infos_logged_user.get(6)),
                    String.valueOf(infos_logged_user.get(7)),
                    String.valueOf(infos_logged_user.get(8)),
                    (byte[])infos_logged_user.get(9)
            );
            return logged_user;
        }

        return logged_user;*/

        return tempUser;
    }

    /**
     *
     * @param id
     * @return ll the informations of the user with this id
     */
    public User getUserbyID(int id)
    {
        User logged_user = new User();
        ArrayList<Object> infos_logged_user = new ArrayList<Object>();
        int logged_user_roleID =0;
        int userID =0;

        SQLiteDatabase MyData = this.getWritableDatabase();
        //SELECT ID, nom FROM user WHERE email = "admin1@mealer.ca"
        Cursor res = MyData.rawQuery("SELECT * FROM user WHERE ID = ? ",new String[] {String.valueOf(id)});

        while (res.moveToNext()) {

            infos_logged_user.add(res.getInt(0));    //ID

            userID= res.getInt(0);

            infos_logged_user.add(res.getInt(1));   //roleID

            logged_user_roleID = res.getInt(1);

            infos_logged_user.add(res.getString(2));    //first_name
            infos_logged_user.add(res.getString(3));    //last_name
            infos_logged_user.add(res.getString(4));    //email
            infos_logged_user.add(res.getString(5));    //password

            infos_logged_user.add(res.getString(6));    //education_level
            infos_logged_user.add(res.getString(7));    //native_language
            infos_logged_user.add(res.getString(8));    //description
            infos_logged_user.add(res.getBlob(9));      //profile_picture

            infos_logged_user.add(res.getInt(10));  //addressID
            infos_logged_user.add(res.getInt(11));  //credit_card_id

        }

        User tempUser = new User( String.valueOf(infos_logged_user.get(2)),
                String.valueOf(infos_logged_user.get(3)),
                String.valueOf(infos_logged_user.get(4)),
                String.valueOf(infos_logged_user.get(5))
        );
        tempUser.setRoleID(logged_user_roleID);

        tempUser.setID((int) infos_logged_user.get(0));
        Log.e("user ID" ,  "ici " + infos_logged_user.get(0));

/*        if(logged_user_roleID == Administrator.getStaticRoleID())
        {
            logged_user = new Administrator(tempUser);
            return logged_user;
        }
        else if(logged_user_roleID == Student.getStaticRoleID())
        {

            logged_user = new Student(tempUser,
                    this.getAddressByID((int)infos_logged_user.get(10)),
                    this.getCreditCardByID((int)infos_logged_user.get(11))
            );

            return logged_user;
        }
        else if(logged_user_roleID == Tutor.getStaticRoleID())
        {
            logged_user = new Tutor(tempUser,
                    String.valueOf(infos_logged_user.get(6)),
                    String.valueOf(infos_logged_user.get(7)),
                    String.valueOf(infos_logged_user.get(8)),
                    (byte[])infos_logged_user.get(9)
            );
            return logged_user;
        }

        return logged_user;*/
            return tempUser;
    }
    public static String getRoleNameByRoleID(int roleID)
    {
        return(roleID==Administrator.getStaticRoleID()?"ADMINISTRATOR":roleID==Student.getStaticRoleID()?"STUDENT":"TUTOR");
    }


    //endregion



    //region FUNCTIONS DELIVERABLE 2

    /**
     *
     * @param c complaint to add in te DB
     * @return true if the complaint is successfully added in the DB, false if not
     */
    public boolean addComplaint(Complaint c)
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("StudentID",c.getStudentID());
        contentValues.put("TutorID",c.getTutorID());
        contentValues.put("title",c.getTitle());
        contentValues.put("description",c.getDescription());
        int is_processed = c.getIs_processed()==false?0:1;
        contentValues.put("is_processed",is_processed);
        String strDate  = new SimpleDateFormat(Complaint.getDATE_FORMAT()).format(c.getSuspension_end_date());
        contentValues.put("date_arret_suspension",strDate);

        long result = MyData.insert("complaint",null,contentValues);
        if(result==-1) return false;
        else
            return true;

    }

    /**
     * This function updates the decision and the date of return of the Chef's suspension
     * @param ComplaintID the ID of the complaint that the admin is processing
     * @param decision type of the decision taken by the admin
     * @param dateRetourSuspension date on which the suspension ends
     * @return true if the complaint decision  was successfully modified in the DB, false if not
     */
    public boolean make_complaint_decision (int ComplaintID, Complaint.Decisions decision, Date dateRetourSuspension)
    {

        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int decisionID = decision == Complaint.Decisions.DISMISSED? 1 :  decision == Complaint.Decisions.TemporarilySuspended? 2 : 3;
        contentValues.put("DecisionsID",decisionID);
        String strDate  =  dateRetourSuspension == null? null: new SimpleDateFormat(Complaint.getDATE_FORMAT()).format(dateRetourSuspension);
        contentValues.put("suspension_end_date",strDate);
        contentValues.put("is_processed",1);
        Cursor cursor = MyData.rawQuery("Select * from complaint where ID = ?", new String[]{String.valueOf(ComplaintID)});
        if(cursor.getCount() > 0)
        {
            long update = MyData.update("complaint",contentValues,"ID=?",new String[]{String.valueOf(ComplaintID)});
            return (update==-1? false:true);
        }
        else
        {
            return false;
        }
    }

    /**
     *
     * @return the list of complaints not processed by the admin
     */
    public ArrayList<Complaint> activeComplaintsList()
    {
        ArrayList<Complaint> activeComplaints = new ArrayList<Complaint>();

        SQLiteDatabase MyData = this.getWritableDatabase();

        Cursor res = MyData.rawQuery("SELECT * FROM complaint WHERE is_processed = ?",new String[] {"0"});

        while (res.moveToNext()) {
            Complaint temp= new Complaint();

            temp.setID(res.getInt(0));
            temp.setStudentID(res.getInt(1));
            temp.setTutorID(res.getInt(2));
            temp.setTitle(res.getString(3));
            temp.setDescription(res.getString(4));
            Boolean is_Processed =res.getInt(5) ==0? false:true;
            temp.setIs_processed(is_Processed);
            int decisionID = res.getInt(6);
            Complaint.Decisions decision = decisionID == 1? Complaint.Decisions.DISMISSED : decisionID == 2? Complaint.Decisions.TemporarilySuspended : Complaint.Decisions.PermanentSuspended;
            temp.setDecision(decision);

            try {
                Date date  = res.getString(7)==null?null: new SimpleDateFormat(Complaint.getDATE_FORMAT()).parse(res.getString(7));
                temp.setSuspension_end_date(date);
            } catch (ParseException e) {
                Log.e("ParseException", "activeComplaintsList: "+ e.getStackTrace() );
            }

            activeComplaints.add(temp);
        }

        return activeComplaints;
    }


    /**
     *
     * @return true if the tutor is suspended
     */
    public Boolean tutorSuspensionStatusByUserID(int tutorID)
    {
        //return user.getIs_suspended();
        Boolean result= false;
        SQLiteDatabase MyData = this.getWritableDatabase();
        //SELECT is_suspended FROM user WHERE ID = 4
        Cursor res = MyData.rawQuery("SELECT is_suspended FROM user WHERE ID =  ?",new String[] {String.valueOf(tutorID)});

        while (res.moveToNext()) {
            result = res.getInt(0)==0?false:true;
        }

        return result;
    }


    /**
     *
     * @param decisionID
     * @return the decision of the complaint given by the administrator in the form of String
     */
    public String getDecisionByDecisionID(int decisionID)
    {
        return(decisionID ==1?"REJEDISMISSEDTER":decisionID==2?"TEMPORARILY SUSPENDED":"PERMANENT SUSPENDED");
    }

    /**
     *
     * @param TutorID the identifier of the tutor whose ID we want
     * @return a map<type_of_the_suspension, Date_of_return_after_suspension>
     *  https://www.w3schools.com/java/java_hashmap.asp
     */
    public Map<String, Date> infoEtatSsuspension(int TutorID)
    {
        Map<String, Date> result = new HashMap<String, Date>();
        SQLiteDatabase MyData = this.getWritableDatabase();

        Cursor res = MyData.rawQuery("SELECT DecisionsID, suspension_end_date FROM complaint WHERE TutorID = ?",new String[] {String.valueOf(TutorID)});

        while (res.moveToNext()) {
            String type_suspension = getDecisionByDecisionID(res.getInt(0));
            Date date = null;
            try {
                String dateStr = res.getString(1);
                date = dateStr == null?null:new SimpleDateFormat(Complaint.getDATE_FORMAT()).parse(dateStr);

            } catch (ParseException e) {
                Log.e("ParseException", "infoEtatSsuspension: "+ e.getStackTrace() );
            }
            result.put(type_suspension, date);
        }

        return result;

    }

    /**
     * This function updates the guardian's suspension return decision
     * @param TutorID the ID of the accused tutor
     * @param is_suspended_state state of suspension of the cook
     * @return true if the update was successful
     */
    public Boolean updateTutorSuspensionState(int TutorID, boolean is_suspended_state )
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int is_susp = is_suspended_state == false?0:1;
        contentValues.put("is_suspended",is_susp);
        Cursor cursor = MyData.rawQuery("Select * from user where ID = ?", new String[]{String.valueOf(TutorID)});
        if(cursor.getCount() > 0)
        {
            long update = MyData.update("user",contentValues,"ID=?",new String[]{String.valueOf(TutorID)});
            return (update==-1? false:true);
        }
        else
        {
            return false;
        }
    }

    //endregion

}
