package com.example.tutrong6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.tutrong6.BEANS.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DBHelper extends SQLiteOpenHelper {

//attribut
    private static final String DATABASE_NAME = "tutronDB";
    private static final int DATABASE_VERSION = 5;

    public static final int SORT_BY_RATINGS = 0;
    public static final int SORT_BY_HOURLY_RATE = 1;
    public static final int SORT_BY_NUMBER_OF_LESSONS = 2;
    public static final int NO_SORT = 3;

    public static final int FIND_TAB_LENGTH = 3;

    public static final int FIND_TAB_POS_TUTOR_NAME = 0;
    public static final int FIND_TAB_POS_LANGUAGE_SPOKEN = 1;
    public static final int FIND_TAB_POS_TOPIC_NAME = 2;

    public static final int STUDENT_ID_POS = 0;
    public static final int TOPIC_ID_POS = 1;


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

                "  hourly_rate REAL DEFAULT -1,\n" +


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
                "  is_processed BOOLEAN DEFAULT 0,\n" +
                "  DecisionsID INTEGER DEFAULT NULL,\n" +
                "  suspension_end_date TEXT DEFAULT NULL,\n" +
                "  FOREIGN KEY (StudentID) REFERENCES user (ID),\n" +
                "  FOREIGN KEY (TutorID) REFERENCES user (ID),\n" +
                "  FOREIGN KEY (DecisionsID) REFERENCES decision (ID)\n" +
                ")");

        //topic table
        sqLiteDatabase.execSQL("CREATE TABLE topic(\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  TutorID INTEGER NOT NULL,\n" +
                "  name TEXT NOT NULL,\n" +
                "  years_of_experience INTEGER NOT NULL,\n" +
                "  description TEXT NOT NULL,\n" +
                "  is_offered BOOLEAN DEFAULT 0,\n" +
                "  FOREIGN KEY (TutorID) REFERENCES user (ID)\n" +
                ")");

        //status table
        sqLiteDatabase.execSQL("CREATE TABLE status (\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  name TEXT NOT NULL\n" +
                ")");




        //lesson table
        sqLiteDatabase.execSQL("CREATE TABLE lesson(\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  StudentID INTEGER NOT NULL,\n" +
                "  TutorID INTEGER NOT NULL,\n" +
                "  TopicID INTEGER NOT NULL,\n" +
                "  StatusID INTEGER NOT NULL,\n" +

                "  date_appointment TEXT NOT NULL,\n" +
                "  start_time TEXT NOT NULL,\n" +
                "  end_time TEXT NOT NULL,\n" +
                "  price REAL DEFAULT NULL,\n" +

                "  rating REAL DEFAULT -1,\n" +
                "  rating_date TEXT DEFAULT NULL,\n" +
                "  is_rating_anonymous BOOLEAN DEFAULT 0,\n" +
                "  is_topic_reviewed BOOLEAN DEFAULT 0,\n" +
                "  review TEXT DEFAULT NULL,\n" +


                "  FOREIGN KEY (StudentID) REFERENCES user (ID),\n" +
                "  FOREIGN KEY (TutorID) REFERENCES user (ID),\n" +
                "  FOREIGN KEY (TopicID) REFERENCES topic (ID),\n" +
                "  FOREIGN KEY (StatusID) REFERENCES status (ID)\n" +
                ")");

        //avaibility table

        sqLiteDatabase.execSQL("CREATE TABLE avaibility(\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  TutorID INTEGER NOT NULL,\n" +
                "  date TEXT NOT NULL,\n" +
                "  FOREIGN KEY (TutorID) REFERENCES user (ID)\n" +
                ")");

        //table slot
        sqLiteDatabase.execSQL("CREATE TABLE slot(\n" +
                "  ID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  AvaibilityID INTEGER NOT NULL,\n" +
                "  start_time TEXT NOT NULL,\n" +
                "  end_time TEXT NOT NULL,\n" +
                "  is_reserved BOOLEAN DEFAULT 0,\n" +
                "  FOREIGN KEY (AvaibilityID) REFERENCES avaibility (ID)\n" +
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
                "( 3, 'Gordon', 'Loutou', 'gl@gmail.com', '0000', 'Bachelor', 'english', 'provide clear and simple explanation', NULL, NULL,NULL, 0),\n" +
                "( 3, 'Puistas', 'Coukap', 'tut@gmail.com', '5555', 'Phd', 'french', 'science enthousiast at your service', NULL, NULL,NULL, 1),\n" +
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


        //add 3 possibles status for lesson
        sqLiteDatabase.execSQL("INSERT INTO status (name) VALUES\n" +
                "(\"PENDING\"),\n" +
                "(\"APPROVED\"),\n" +
                "(\"REJECTED\")");

        //populate topic
        sqLiteDatabase.execSQL("INSERT INTO topic (TutorID, name, years_of_experience, description, is_offered) VALUES\n" +
                "(3, ' Software Testing', 10, 'Software testing is the process of evaluating and verifying that a software product or application does what it is supposed to do.', 1),\n" +
                "(3, 'Algebra', 5, 'Algebra is a branch of mathematics that deals with variables, constants, and arithmetic operations.', 1),\n" +
                "(3, 'mechanics of Physics', 20, 'Mechanics is the branch of Physics dealing with the study of motion when subjected to forces or displacements, and the subsequent effects of the bodies on their environment.', 0),\n" +
                "(4, 'Chemistry', 10, 'the study of matter, analysing its structure, properties and behaviour to see what happens when they change in chemical reactions.', 1),\n" +
                "(4, 'Biology', 7, ' Biology encompasses diverse fields, including botany, conservation, ecology, evolution, genetics, marine biology, medicine, microbiology, molecular biology, physiology, and zoology', 1)");


        //populate avaibility
        sqLiteDatabase.execSQL("INSERT INTO avaibility (TutorID, date) VALUES\n" +
                "(3, '17/07/2023'),\n" +
                "(3, '18/07/2022'),\n" +
                "(3, '20/07/2023'),\n" +
                "(3, '22/07/2023'),\n" +
                "(4, '17/07/2023'),\n" +
                "(4, '18/07/2022'),\n" +
                "(4, '20/07/2023'),\n" +
                "(4, '22/07/2023'),\n" +
                "(4, '24/07/2023'),\n" +
                "(3, '24/07/2023')");

        //populate slot
        sqLiteDatabase.execSQL("INSERT INTO slot (AvaibilityID, start_time, end_time) VALUES\n" +
                "(1, '09:00', '11:00'),\n" +
                "(1, '13:00', '15:00'),\n" +
                "(1, '18:00', '20:00'),\n" +

                "(2, '09:00', '11:00'),\n" +
                "(2, '13:00', '15:00'),\n" +
                "(2, '18:00', '20:00'),\n" +

                "(3, '09:00', '11:00'),\n" +
                "(3, '13:00', '15:00'),\n" +
                "(3, '18:00', '20:00'),\n" +

                "(4, '09:00', '11:00'),\n" +
                "(4, '13:00', '15:00'),\n" +
                "(4, '18:00', '20:00'),\n" +

                "(5, '09:00', '11:00'),\n" +
                "(5, '13:00', '15:00'),\n" +
                "(5, '18:00', '20:00'),\n" +

                "(6, '09:00', '11:00'),\n" +
                "(6, '13:00', '15:00'),\n" +
                "(6, '18:00', '20:00'),\n" +

                "(7, '09:00', '11:00'),\n" +
                "(7, '13:00', '15:00'),\n" +
                "(7, '18:00', '20:00'),\n" +

                "(8, '09:00', '11:00'),\n" +
                "(8, '13:00', '15:00'),\n" +
                "(8, '18:00', '20:00'),\n" +

                "(9, '09:00', '11:00'),\n" +
                "(9, '13:00', '15:00'),\n" +
                "(9, '18:00', '20:00'),\n" +

                "(10, '09:00', '11:00'),\n" +
                "(10, '13:00', '15:00'),\n" +
                "(10, '18:00', '20:00')");

        //populate lesson

        sqLiteDatabase.execSQL("INSERT INTO lesson (StudentID, TutorID, TopicID, StatusID, date_appointment, start_time, end_time, rating, rating_date, is_rating_anonymous, is_topic_reviewed, review) VALUES\n" +
                "(7, 3, 2, 3, '02/12/2022', '10:00', '12:00', 5, '04/12/2022', 0, 1, 'excellent tutor'),\n" +
                "(7, 3, 2, 3, '02/03/2023', '17:30', '19:30', -1, NULL, 0, 0, NULL),\n" +
                "(7, 3, 2, 1, '22/06/2024', '18:30', '20:30', -1, NULL, 0, 0, NULL),\n" +
                "(7, 3, 2, 1, '17/04/2026', '13:30', '15:30', -1, NULL, 0, 0, NULL),\n" +
                "(7, 4, 5, 3, '21/05/2023', '09:00', '11:00', 5, '22/05/2023', 1, 1, 'clear explanation')");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("drop table if exists creditcard");
        sqLiteDatabase.execSQL("drop table if exists address");
        sqLiteDatabase.execSQL("drop table if exists role");
        sqLiteDatabase.execSQL("drop table if exists user");
        sqLiteDatabase.execSQL("drop table if exists decision");
        sqLiteDatabase.execSQL("drop table if exists complaint");
        sqLiteDatabase.execSQL("drop table if exists topic");
        sqLiteDatabase.execSQL("drop table if exists status");
        sqLiteDatabase.execSQL("drop table if exists lesson");
        sqLiteDatabase.execSQL("drop table if exists avaibility");
        sqLiteDatabase.execSQL("drop table if exists slot");

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
     * @return credit card found in the DB
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
     * @return all the informations of the user with this id
     */
    public User getUserbyID(int id)
    {
        User logged_user = new User();
        ArrayList<Object>infos_logged_user = new ArrayList<Object>();
        int logged_user_roleID =0;
        int userID =0;
        boolean is_susp = false;

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

             is_susp = res.getInt(12) ==0? false:true;  // is_suspended
            infos_logged_user.add(is_susp);

        }

        User tempUser = new User( String.valueOf(infos_logged_user.get(2)),
                String.valueOf(infos_logged_user.get(3)),
                String.valueOf(infos_logged_user.get(4)),
                String.valueOf(infos_logged_user.get(5))
        );
        tempUser.setRoleID(logged_user_roleID);

        tempUser.setID((int) infos_logged_user.get(0));
        Log.e("user ID" ,  "ici " + infos_logged_user.get(0));
        tempUser.setIs_suspended(is_susp);

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
     * @param decisionID type of the decision taken by the admin
     * @param dateRetourSuspension date on which the suspension ends
     * @return true if the complaint decision  was successfully modified in the DB, false if not
     */
    public boolean make_complaint_decision (int ComplaintID, int decisionID, Date dateRetourSuspension)
    {

        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
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
            temp.setDecisionID(res.getInt(6));

            try {
                Date date  = res.getString(7)==null?null: new SimpleDateFormat(Complaint.getDATE_FORMAT()).parse(res.getString(7));
                temp.setSuspension_end_date(date);
            } catch (ParseException e) {
                Log.e("ParseException", "activeComplaintsList: "+ e.getStackTrace() );
            }

            activeComplaints.add(temp);
        }
        for(Complaint c : activeComplaints)
        {
            Log.e("LISTE COMPLAINTS", "= "+ c );
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
        return(decisionID ==1?"DISMISSED":decisionID==2?"TEMPORARILY SUSPENDED":"PERMANENT SUSPENDED");
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



    //region FUNCTIONS DELIVERABLE 3

    /**
     * add a topic in the database
     * @param topic the topic that will be added in the DB
     * @return true if the addition in the DB was successfull, otherwise return false
     */
    public boolean addTopic(Topic topic)
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("TutorID",topic.getTutorID());
        contentValues.put("name",topic.getName());
        contentValues.put("years_of_experience",topic.getYears_of_experience());
        contentValues.put("description",topic.getDescription());
        int is_offered= topic.getIs_offered()==false?0:1;
        contentValues.put("is_offered",is_offered);

        long result = MyData.insert("topic",null,contentValues);

        if(result==-1) return false;
        else
            return true;
    }

    /**
     * delete a topic in the DB
     * @param topicID ID of the topic to delete
     * @return true if the deletion in the DB was successfull, otherwise return false
     */
    public boolean deleteTopic(int topicID)
    {

        SQLiteDatabase MyData = this.getWritableDatabase();
        Cursor cursor = MyData.rawQuery("Select * from topic where  ID =  ?", new String[]{String.valueOf(topicID)});
        if (cursor.getCount() > 0)
        {
            long result = MyData.delete("topic", "ID=?", new String[]{String.valueOf(topicID)});
            return (result==-1? false:true);
        }
        else {
            return false;
        }

    }

    /**
     * This function updates the topic status: if it is an offered topic or not
     * @param topicID the ID of the topic whose offer must be updated
     * @param offer_state new value of topic offer status
     * @return true if the update has been carried out correctly
     */
    public boolean updateTopicOffer(int topicID, boolean offer_state)
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int is_offered = offer_state == false?0:1;
        contentValues.put("is_offered",is_offered);
        Cursor cursor = MyData.rawQuery("Select * from topic where ID = ?", new String[]{String.valueOf(topicID)});
        if(cursor.getCount() > 0)
        {
            long update = MyData.update("topic",contentValues,"ID=?",new String[]{String.valueOf(topicID)});
            return (update==-1? false:true);
        }
        else
        {
            return false;
        }
    }

    /**
     * This function updates the topic
     * @param topicID the ID of the topic whose offer must be updated
     * @return true if the update has been carried out correctly
     */
    public boolean updateTopic(int topicID, Topic topic)
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name",topic.getName());
        contentValues.put("years_of_experience", topic.getYears_of_experience());
        contentValues.put("description", topic.getDescription());

        Cursor cursor = MyData.rawQuery("Select * from topic where ID = ?", new String[]{String.valueOf(topicID)});
        if(cursor.getCount() > 0)
        {
            long update = MyData.update("topic",contentValues,"ID=?",new String[]{String.valueOf(topicID)});
            return (update==-1? false:true);
        }
        else
        {
            return false;
        }
    }

    /**
     * finds a topic based on its ID
     * @param topicID the ID of the topic whose information we want
     * @return the topic corresponding to the ID given as a parameter
     */
    public Topic getTopicByID(int topicID)
    {

        Topic topic = new Topic();
        SQLiteDatabase MyData = this.getWritableDatabase();

        Cursor res = MyData.rawQuery("SELECT * FROM topic WHERE ID = ? ",new String[] {String.valueOf(topicID)});

        while (res.moveToNext()) {

            topic.setID(res.getInt(0));
            topic.setTutorID(res.getInt(1));
            topic.setName(res.getString(2));
            topic.setYears_of_experience(res.getInt(3));
            topic.setDescription(res.getString(4));
            boolean is_offered =res.getInt(5) ==0? false:true;
            topic.setIs_offered(is_offered);
        }
//        MyData.close();
        return topic;

    }

    /**
     * gives all the topics created by a tutor
     * @param tutorID the ID of the tutor whose topics we want
     * @return list of all the topics created by a tutor whose ID was given as a parameter
     */
    public ArrayList<Topic> getAllTopics(int tutorID)
    {
        ArrayList<Topic> topics = new ArrayList<Topic>();

        SQLiteDatabase MyData = this.getWritableDatabase();

        Cursor res = MyData.rawQuery("SELECT * FROM topic WHERE TutorID = ?",new String[] {String.valueOf(tutorID)});

        while (res.moveToNext()) {
            Topic temp= new Topic();

            temp.setID(res.getInt(0));
            temp.setTutorID(res.getInt(1));
            temp.setName(res.getString(2));
            temp.setYears_of_experience(res.getInt(3));
            temp.setDescription(res.getString(4));
            boolean is_offered =res.getInt(5) ==0? false:true;
            temp.setIs_offered(is_offered);

            topics.add(temp);
        }

//        MyData.close();
        return topics;
    }

    /**
     *gives the topics offered by a tutor
     * @param tutorID the ID of the tutor whose topics we want
     * @return list of  topics offered by the tutor whose ID was given as a parameter
     */
    public ArrayList<Topic> getOfferedTopics(int tutorID)
    {
        ArrayList<Topic> offered_topics = new ArrayList<Topic>();

        SQLiteDatabase MyData = this.getWritableDatabase();
        Cursor res = MyData.rawQuery("SELECT * FROM topic WHERE TutorID = ? AND is_offered = ?",new String[] {String.valueOf(tutorID),"1"});

        while (res.moveToNext()) {
            Topic temp= new Topic();

            temp.setID(res.getInt(0));
            temp.setTutorID(res.getInt(1));
            temp.setName(res.getString(2));
            temp.setYears_of_experience(res.getInt(3));
            temp.setDescription(res.getString(4));
            boolean is_offered =res.getInt(5) ==0? false:true;
            temp.setIs_offered(is_offered);

            offered_topics.add(temp);
        }

//        MyData.close();
        return offered_topics;
    }

    /**
     *
     * @param tutorID ID of the tutor we want
     * @return all the informations of the tutor with this id
     */
    public Tutor getTutorByID(int tutorID)
    {
        Log.e("getTutorByID", "tutorID= "+ tutorID);
        Tutor tutor = new Tutor();
        SQLiteDatabase MyData = this.getWritableDatabase();

        Cursor res = MyData.rawQuery("SELECT * FROM user WHERE ID = ? ",new String[] {String.valueOf(tutorID)});

        while (res.moveToNext()) {

            tutor.setID(res.getInt(0));
            tutor.setRoleID(res.getInt(1));
            tutor.setFirst_name(res.getString(2));
            tutor.setLast_name(res.getString(3));
            tutor.setEmail(res.getString(4));
            tutor.setPassword(res.getString(5));

            tutor.setEducation_level(res.getString(6));
            tutor.setNative_language(res.getString(7));
            tutor.setDescription(res.getString(8));
            tutor.setProfile_picture(res.getBlob(9));

            Boolean is_susp = res.getInt(12) ==0? false:true;
            tutor.setIs_suspended(is_susp);
            tutor.setHourly_rate(res.getDouble(13));

        }
        Log.e("getTutorByID", "Return: "+ tutor);
//      MyData.close();
        return tutor;
    }

    /**
     *
     * @param tutorID Id of the interrested tutor
     * @return a map<total_created_topics, total_offered_topics>
     *     https://www.w3schools.com/java/java_hashmap.asp
     */
    public  Map<Integer, Integer> countTopics(int tutorID)
    {
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        int total_created_topics =0;
        int total_offered_topics = 0;
        SQLiteDatabase MyData = this.getWritableDatabase();
        //SELECT COUNT(ID) from topic where is_offered=1 AND TutorID = 8
        Cursor res = MyData.rawQuery("SELECT COUNT(ID) from topic where is_offered = ? AND TutorID = ? ",new String[] {"1",String.valueOf(tutorID)});
        while (res.moveToNext()) {
            total_offered_topics = res.getInt(0);
        }
        res = MyData.rawQuery("SELECT COUNT(ID) from topic where TutorID = ? ",new String[] {String.valueOf(tutorID)});
        while (res.moveToNext()) {
            total_created_topics = res.getInt(0);
        }
        result.put(total_created_topics, total_offered_topics);
        return result;
    }


    //endregion


    //region FUNCTIONS DELIVERABLE 4

    /**
     *
     * @param StudentID
     * @return a student whose has the given ID
     */
    public Student getStudentByID(int StudentID)
    {
        Student student = new Student();
        SQLiteDatabase MyData = this.getWritableDatabase();
        //SELECT ID, nom FROM user WHERE email = "admin1@mealer.ca"
        Cursor res = MyData.rawQuery("SELECT * FROM user WHERE ID = ? ",new String[] {String.valueOf(StudentID)});

        while (res.moveToNext()) {

            student.setID(res.getInt(0));
            student.setRoleID(res.getInt(1));
            student.setFirst_name(res.getString(2));
            student.setLast_name(res.getString(3));
            student.setEmail(res.getString(4));
            student.setPassword(res.getString(5));

            Address addr = this.getAddressByID(res.getInt(10));
            CreditCard cc = this.getCreditCardByID(res.getInt(11));
            student.setAddress(addr);
            student.setCredit_card(cc);


        }
//        MyData.close();
        return student;
    }

    /**
     *
     * @param statusID
     * @return status in String form
     */
    public String getStatusByID(int statusID)
    {
        return(statusID ==1?"PENDING":statusID==2?"APPROVED":statusID==3?"REJECTED":"COMPLETED");
    }

    /**
     *
     * @param lessonID
     * @return status in String form
     */
    public String getStatusByLessonID(int lessonID)
    {
        int statusID = -1;
        SQLiteDatabase MyData = this.getWritableDatabase();

        Cursor res = MyData.rawQuery("SELECT StatusID FROM lesson WHERE ID = ? ",new String[] {String.valueOf(lessonID)});
        while (res.moveToNext()) {
            statusID = res.getInt(0);
        }
        return this.getStatusByID(statusID);
    }

    /**
     *
     * @param tutorID id of the tutor who want to update his hourly rate
     * @param hourly_rate new hourly rate value
     * @return true if the hourly rate was successfully updated and false if otherwise
     */
    public boolean updateHourlyRate(int tutorID, double hourly_rate)
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("hourly_rate",hourly_rate);

        Cursor cursor = MyData.rawQuery("Select * from user where ID = ?", new String[]{String.valueOf(tutorID)});
        if(cursor.getCount() > 0)
        {
            long update = MyData.update("user",contentValues,"ID=?",new String[]{String.valueOf(tutorID)});
            return (update==-1? false:true);
        }
        else
        {
            return false;
        }
    }

    /**
     *
     * @param avaibilityID ID of the avaibility we are interested in
     * @return all the slots of a given avaibility
     */
    public ArrayList<Slot> getSlotsByAvaibilityID(int avaibilityID)
    {
        ArrayList<Slot> result = new ArrayList<Slot>();
        SQLiteDatabase MyData = this.getWritableDatabase();
        //SELECT ID, nom FROM user WHERE email = "admin1@mealer.ca"
        Cursor res = MyData.rawQuery("SELECT * FROM slot WHERE AvaibilityID = ? AND is_reserved = ?",new String[] {String.valueOf(avaibilityID),"0"});

        while (res.moveToNext()) {
            Slot temp = new Slot();

            temp.setID(res.getInt(0));
            temp.setAvaibilityID(res.getInt(1));
            String start_str = res.getString(2).trim();
            String end_str = res.getString(3).trim();
            //Call requires API level 26
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                temp.setStartTime(LocalTime.parse(start_str));
                temp.setEndTime(LocalTime.parse(end_str));
            }
            boolean is_reserved = res.getInt(4) ==0 ? false : true;
            temp.setIs_reserved(is_reserved);
            result.add(temp);
        }
        return result;
    }

    /**
     *
     * @param tutorID ID if the tutor we want avaibilities
     * @return a map<avaibilitiy_date, array of all slots for this specific date>
     *       https://www.w3schools.com/java/java_hashmap.asp
     */
    public Map<Date,ArrayList<Slot>> getAvaibilitiesByTutorID(int tutorID)
    {
        Map<Date,ArrayList<Slot>> result = new HashMap<Date,ArrayList<Slot>>();

        SQLiteDatabase MyData = this.getWritableDatabase();

        Cursor res = MyData.rawQuery("SELECT * FROM avaibility WHERE TutorID = ? ",new String[] {String.valueOf(tutorID)});
        while (res.moveToNext()) {
            int ID = res.getInt(0);
            Date date = new Date();
            try {
                 date  = res.getString(2)==null?null: new SimpleDateFormat(Complaint.getDATE_FORMAT()).parse(res.getString(2));
            } catch (ParseException e) {
                Log.e("ParseException", "activeComplaintsList: "+ e.getStackTrace() );
            }
            ArrayList<Slot> slots = this.getSlotsByAvaibilityID(ID);
            result.put(date,slots);

        }
        return result;
    }


    /**
     *
     * @param findBy table of values to look for in the DB.findBy[0]=tutor_name, findBy[1]= language_spoken, findBy[2]=Topic_name
     * @param sortBy sorting value. 0 = user-rtings, 1 = hourly_rate and 3 = number_of and -1 = no sort
     * @return all offered topic IDs matching the values given in parameters
     */
    public ArrayList<Integer> findTopic(String[] findBy, int sortBy)
    {
        Log.e("findTopic", "IN the findTopic FUNCTION: "+ findBy[0] + findBy[1] + findBy[2] + sortBy);

         ArrayList<Integer> result = new ArrayList<Integer>();
        String tutorName = findBy[FIND_TAB_POS_TUTOR_NAME];
        String language_spoken = findBy[FIND_TAB_POS_LANGUAGE_SPOKEN];
        String topic_name = findBy[FIND_TAB_POS_TOPIC_NAME];
        String lesson_join_part = "";
        String second_return_parameter = "";

        String condition_part = "";
        String sort_part = "";


        //condition management

        String addTutorName = " AND user.first_name LIKE '%" + tutorName + "%' OR user.last_name LIKE '%"+ tutorName +"%'\n";
        String addLanguage = " AND user.native_language LIKE '%"+ language_spoken + "%' \n ";
        String addTopicName = "AND topic.name  LIKE '%" + topic_name + "%' \n";

        // X = empty and O = not empty
        // tutor_name X

        if(tutorName.isEmpty())
        {
            // language_spokenO
            if(!language_spoken.isEmpty())
            {
                //case1: tutor_name X * language_spoken O *  topic_name X
                //and case2: tutor_name X * language_spoken O *  topic_name O
                condition_part += topic_name.isEmpty()? addLanguage : addLanguage+addTopicName;
            }
            //language_spoken X
            else
            {
                //case3: tutor_name X * language_spoken X *  topic_name O
                //and case4: tutor_name X * language_spoken X *  topic_name X
                condition_part += !topic_name.isEmpty()? addTopicName :"";

            }
        }
        // tutor_name O
        else
        {
            condition_part += addTutorName;
            if(!language_spoken.isEmpty())
            {
                //case5: tutor_name O * language_spoken O *  topic_name X
                //and case6: tutor_name O * language_spoken O *  topic_name O
                condition_part += topic_name.isEmpty()? addLanguage : addLanguage+addTopicName;
            }
            //language_spoken X
            else
            {
                //case7: tutor_name O * language_spoken X *  topic_name O
                //and case8: tutor_name O * language_spoken X *  topic_name X
                condition_part += !topic_name.isEmpty()? addTopicName :"";
            }

        }

        //sort mamangement
        switch(sortBy)
        {
            case SORT_BY_RATINGS:
                second_return_parameter=",lesson.rating as RatingLesson";
                lesson_join_part="left Join lesson ON topic.ID = lesson.TopicID";
                sort_part = " ORDER BY lesson.rating DESC ";
                break;
            case SORT_BY_HOURLY_RATE:
                second_return_parameter=",user.hourly_rate ";
                lesson_join_part="Join lesson ON user.ID = lesson.TutorID";
                sort_part = " ORDER BY user.hourly_rate ASC ";
                break;
            case SORT_BY_NUMBER_OF_LESSONS:
                second_return_parameter=",(select count(lesson.ID) from lesson where lesson.TopicID = topic.ID) as countLesson";
                lesson_join_part="Join lesson ON user.ID = lesson.TutorID";
                sort_part = "ORDER BY countLesson DESC";
                break;
        }

        //SQL management
        String part1 = "SELECT  DISTINCT topic.ID as TopicID\n" +
                second_return_parameter +
                "from topic \n" +
                "join user ON topic.TutorID = user.ID  " +
                lesson_join_part +
                "WHERE\n" +
                "topic.is_offered = 1\n";

        String cmd = part1 + condition_part + sort_part;
        SQLiteDatabase MyData = this.getWritableDatabase();
        Cursor res = MyData.rawQuery(cmd,null);

        Log.e("findTopic", "Query= "+ cmd );


        while (res.moveToNext()) {
            int temp = res.getInt(0);
            result.add(temp);
        }
        List<Integer> temp= result.stream().distinct().collect(Collectors.toList());
        result.clear();
        result.addAll(temp);

        for (int var : result)
        {
            Log.e("findTopic", "result: "+ var );

        }
        Log.e("findTopic", "Was IN FINDBY");

         return result;
    }

    /**
     *
     * @param lesson the lesson to add in the DB
     * @param tutorHourlyRate hourly rate of the tutor from whom the lesson is purchased
     * @return true if the lesson was added in the DB
     */
    public boolean addLesson(Lesson lesson, double tutorHourlyRate)
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("StudentID",lesson.getStudentID());
        contentValues.put("TutorID",lesson.getTutorID());
        contentValues.put("TopicID",lesson.getTopicID());
        int statusID = Lesson.getstatusIDByEnum(Lesson.Status.PENDING);
        contentValues.put("StatusID",statusID);

        String strDate  = new SimpleDateFormat(Complaint.getDATE_FORMAT()).format(lesson.getDate_appointment());
        contentValues.put("date_appointment",String.valueOf(strDate));
        Slot timeSlot = lesson.getSlot();
        contentValues.put("start_time",String.valueOf(timeSlot.getStartTime()));
        contentValues.put("end_time",String.valueOf(timeSlot.getEndTime()));

        double price = tutorHourlyRate * Slot.getSlotDuration(timeSlot);
        contentValues.put("price",price);

        long result = MyData.insert("lesson",null,contentValues);

        if(result==-1) return false;
        else
            return true;

    }

    /**
     *
     * @param lessonID the ID of the lesson whose status we want
     * @param statusID the new value of the statusID.
     *                 Get the ID of the status wanted by the function getstatusIDByEnum(Status status) in the class Lesson
     * @return true if the status was updated
     */
    public boolean updateStatusLesson(int lessonID, int statusID)
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("StatusID",statusID);
        Cursor cursor = MyData.rawQuery("Select * from lesson where ID = ?", new String[]{String.valueOf(lessonID)});
        if(cursor.getCount() > 0)
        {
            long update = MyData.update("lesson",contentValues,"ID=?",new String[]{String.valueOf(lessonID)});
            return (update==-1? false:true);
        }
        else
        {
            return false;
        }
    }
    public boolean updateRatingLesson(int lessonID, ReviewSystem review)
    {

        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("rating",review.getRating());
        contentValues.put("rating_date",String.valueOf(review.getRating_date()));
        int is_rating_anonymous = review.getIs_rating_anonymous() == false? 0:1;
        contentValues.put("is_rating_anonymous",is_rating_anonymous);
        int is_topic_reviewed = review.getIs_topic_reviewed() == false? 0 : 1;
        contentValues.put("is_topic_reviewed",is_topic_reviewed);
        contentValues.put("review",review.getReview());

        Cursor cursor = MyData.rawQuery("Select * from lesson where ID = ?", new String[]{String.valueOf(lessonID)});
        if(cursor.getCount() > 0)
        {
            long update = MyData.update("lesson",contentValues,"ID=?",new String[]{String.valueOf(lessonID)});
            return (update==-1? false:true);
        }
        else
        {
            return false;
        }
    }

    /**
     * delete a lesson's review in the DB
     * @param lessonID ID of the lesson to delete
     * @return true if the deletion in the DB was successfull, otherwise return false
     */

    public boolean deleteRatingLesson(int lessonID)
    {
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       String default_str=null;
       int default_bool  = 0;

        contentValues.put("rating",-1);
        contentValues.put("rating_date",default_str);

        contentValues.put("is_rating_anonymous",default_bool);

        contentValues.put("is_topic_reviewed",default_bool);
        contentValues.put("review",default_str);

        Cursor cursor = MyData.rawQuery("Select * from lesson where ID = ?", new String[]{String.valueOf(lessonID)});
        if(cursor.getCount() > 0)
        {
            long update = MyData.update("lesson",contentValues,"ID=?",new String[]{String.valueOf(lessonID)});
            return (update==-1? false:true);
        }
        else
        {
            return false;
        }
    }

    /**
     *
     * @param tutorID ID of the tutor we are interested in
     * @return the number of lessons given/sold by the tutor
     */
    public int getCountGivenLesson(int tutorID)
    {
        int result = -1;
        SQLiteDatabase MyData = this.getWritableDatabase();
        Cursor res = MyData.rawQuery("SELECT COUNT(ID) FROM lesson WHERE TutorID = ? AND StatusID = ? ",new String[] {String.valueOf(tutorID),"3"});
        while (res.moveToNext()) {
            result = res.getInt(0);
        }
        return result;

    }

    /**
     *
     * @param tutorID ID of the tutor we want
     * @return the average rating of the given tutor
     */
    public double getAverageTutorRating(int tutorID)
    {
        double result=-1;
        SQLiteDatabase MyData = this.getWritableDatabase();
        //SELECT AVG(lesson.rating) FROM lesson JOIN topic ON lesson.TopicID = topic.ID WHERE lesson.TutorID = 5 AND lesson.rating != 0
        Cursor res = MyData.rawQuery("SELECT AVG(lesson.rating) FROM lesson JOIN topic ON lesson.TopicID = topic.ID WHERE lesson.TutorID = ? AND lesson.rating != ? ",new String[] {String.valueOf(tutorID),"-1"});
        while (res.moveToNext()) {
            result = res.getDouble(0);
        }
        return result;
    }

    /**
     *
     * @param tutorID
     * @return a map< int[], ReviewSystem>: int[] is table of int containing the StudentID at pos[0], TopicID at pos[1] and lessonID at pos[2]
     *      *  https://www.w3schools.com/java/java_hashmap.asp
     */
    public Map<int[],ReviewSystem> getAllReviewSystems(int tutorID)
    {
        Map<int[],ReviewSystem> result = new HashMap<int[],ReviewSystem>();

        SQLiteDatabase MyData = this.getWritableDatabase();

        Cursor res = MyData.rawQuery("SELECT rating,is_rating_anonymous,rating_date,is_topic_reviewed,review,StudentID,TopicID,ID from lesson WHERE TutorID = ? AND rating != ? ",new String[] {String.valueOf(tutorID),"-1"});

        while (res.moveToNext()) {

            ReviewSystem review_temp = new ReviewSystem();
            review_temp.setRating(res.getInt(0));

            Boolean is_rating_anonymous = res.getInt(1) == 0 ? false : true;
            review_temp.setIs_rating_anonymous(is_rating_anonymous);

            try {
                Date date = res.getString(2) == null ? null : new SimpleDateFormat(Complaint.getDATE_FORMAT()).parse(res.getString(2));
                review_temp.setRating_date(date);
            } catch (ParseException e)
            {
                Log.e("ParseException", "activeComplaintsList: " + e.getStackTrace());
            }

            Boolean is_topic_reviewed = res.getInt(3) == 0 ? false : true;
            review_temp.setIs_topic_reviewed(is_topic_reviewed);
            review_temp.setReview(res.getString(4));

            int[] IDs = new int[3];
            IDs[0]= res.getInt(5) ;
            IDs[1]= res.getInt(6) ;
            IDs[2]= res.getInt(7) ;

            result.put(IDs,review_temp);
        }

        return result;
    }

    /**
     *
     * @param lessonID
     * @return the reviewSystem of the given lesson
     */
    public ReviewSystem getReviewSystemByLessonID(int lessonID)
    {
        ReviewSystem result = new ReviewSystem();

        SQLiteDatabase MyData = this.getWritableDatabase();

        Cursor res = MyData.rawQuery("SELECT rating,is_rating_anonymous,rating_date,is_topic_reviewed,review from lesson WHERE ID == ?",new String[] {String.valueOf(lessonID)});

        while (res.moveToNext()) {

            ReviewSystem temp = new ReviewSystem();
            temp.setRating(res.getInt(0));

            Boolean is_rating_anonymous = res.getInt(1) == 0 ? false : true;
            temp.setIs_rating_anonymous(is_rating_anonymous);

            try {
                Date date = res.getString(2) == null ? null : new SimpleDateFormat(Complaint.getDATE_FORMAT()).parse(res.getString(2));
                temp.setRating_date(date);
            } catch (ParseException e)
            {
                Log.e("ParseException", "activeComplaintsList: " + e.getStackTrace());
            }

            Boolean is_topic_reviewed = res.getInt(3) == 0 ? false : true;
            temp.setIs_topic_reviewed(is_topic_reviewed);
            temp.setReview(res.getString(4));

            result=temp;
        }

        return result;
    }

    /**
     *
     * @param tutorID
     * @param topicID
     * @return
     */
    public double getTopicRating(int tutorID, int topicID)
    {
        double result = -1;
        SQLiteDatabase MyData = this.getWritableDatabase();
        //SELECT AVG(lesson.rating) FROM lesson
        //JOIN topic ON topic.ID = lesson.TopicID
        //JOIN user ON user.ID = lesson.TutorID
        //WHERE lesson.TutorID = 3
        //AND lesson.TopicID = 1
        //AND lesson.rating != -1

        Cursor res = MyData.rawQuery("SELECT AVG(lesson.rating) FROM lesson \n" +
                "JOIN topic ON topic.ID = lesson.TopicID \n" +
                "JOIN user ON user.ID = lesson.TutorID\n" +
                "WHERE lesson.TutorID = ? \n" +
                "AND lesson.TopicID = ? \n" +
                "AND lesson.rating != ?",new String[] {String.valueOf(tutorID), String.valueOf(topicID),"-1"});
        while (res.moveToNext()) {
            result = res.getDouble(0);
        }
        return result;
    }

    /**
     *
     * @param StudentID id of the student we are interested in
     * @param statusID the status of the lesson we want.
     *                 If we don't want all the statuses together then we insert the value -1
     * @return the list of customer lessons according to the given parameters
     */
    public ArrayList<Lesson> getStudentLessons(int StudentID, int statusID)
    {
        ArrayList<Lesson> result = new ArrayList<Lesson>();
        SQLiteDatabase MyData = this.getWritableDatabase();
        String part1 = "SELECT * FROM lesson WHERE StudentID = ? ";
        String part2 = statusID == -1 ? "": "AND StatusID = ? ";
        String part3 = "ORDER by ID DESC";
        String cmd_sql = part1 + part2 + part3;

        Cursor res = MyData.rawQuery(cmd_sql,new String[] {String.valueOf(StudentID),String.valueOf(statusID)});

        while (res.moveToNext()) {

            Lesson temp = new Lesson();
            int lessonID = res.getInt(0);
            temp.setID(lessonID);
            temp.setStudentID(res.getInt(1));
            temp.setTutorID(res.getInt(2));
            temp.setTopicID(res.getInt(3));
            temp.setStatus(Lesson.getstatusEnumByID(res.getInt(4)));

            try {
                Date date = res.getString(5) == null ? null : new SimpleDateFormat(Complaint.getDATE_FORMAT()).parse(res.getString(5));
                temp.setDate_appointment(date);
            } catch (ParseException e) {
                Log.e("ParseException", "activeComplaintsList: " + e.getStackTrace());
            }

            //Call requires API level 26
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalTime start = LocalTime.parse(res.getString(6).trim());
                LocalTime end = LocalTime.parse(res.getString(7).trim());
                Slot slot = new Slot(start, end);
                temp.setSlot(slot);
            }

            temp.setPrice(res.getDouble(8));

            ReviewSystem rs = getReviewSystemByLessonID(lessonID);
            temp.setReview_system(rs);

            result.add(temp);
        }
        return result;
    }

    /**
     *
     * @param studentID the ID of the student whose non evaluated lessons we want
     * @return list of lessons not evaluated by the student
     */
    public ArrayList<Lesson> getLessonNonEvaluate(int studentID)
    {
        ArrayList<Lesson> result = new ArrayList<Lesson>();
        SQLiteDatabase MyData = this.getWritableDatabase();
        Cursor res = MyData.rawQuery("SELECT * FROM lesson WHERE StudentID = ? AND StatusID = ? AND rating = ?",new String[] {String.valueOf(studentID),"-1"});

        while (res.moveToNext()) {

            Lesson temp = new Lesson();
            int lessonID = res.getInt(0);
            temp.setID(lessonID);
            temp.setStudentID(res.getInt(1));
            temp.setTutorID(res.getInt(2));
            temp.setTopicID(res.getInt(3));
            temp.setStatus(Lesson.getstatusEnumByID(res.getInt(4)));

            try {
                Date date = res.getString(5) == null ? null : new SimpleDateFormat(Complaint.getDATE_FORMAT()).parse(res.getString(5));
                temp.setDate_appointment(date);
            } catch (ParseException e) {
                Log.e("ParseException", "activeComplaintsList: " + e.getStackTrace());
            }

            //Call requires API level 26
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalTime start = LocalTime.parse(res.getString(6).trim());
                LocalTime end = LocalTime.parse(res.getString(7).trim());
                Slot slot = new Slot(start, end);
                temp.setSlot(slot);
            }

            temp.setPrice(res.getDouble(8));

            ReviewSystem rs = getReviewSystemByLessonID(lessonID);
            temp.setReview_system(rs);

            result.add(temp);
        }

        return result  ;
    }

    /**
     *
     * @param tutorID
     * @return
     */
    public ArrayList<Lesson> getTutorPurchaseDemands(int tutorID)
    {
        ArrayList<Lesson> result = new ArrayList<Lesson>();
        SQLiteDatabase MyData = this.getWritableDatabase();
        int pending = Lesson.getstatusIDByEnum(Lesson.Status.PENDING);
        Cursor res = MyData.rawQuery("SELECT * FROM lesson WHERE TutorID = ? AND StatusID = ?",new String[] {String.valueOf(tutorID),String.valueOf(pending)});

        while (res.moveToNext()) {

            Lesson temp = new Lesson();
            int lessonID = res.getInt(0);
            temp.setID(lessonID);
            temp.setStudentID(res.getInt(1));
            temp.setTutorID(res.getInt(2));
            temp.setTopicID(res.getInt(3));
            temp.setStatus(Lesson.getstatusEnumByID(res.getInt(4)));

            try {
                Date date = res.getString(5) == null ? null : new SimpleDateFormat(Complaint.getDATE_FORMAT()).parse(res.getString(5));
                temp.setDate_appointment(date);
            } catch (ParseException e) {
                Log.e("ParseException", "activeComplaintsList: " + e.getStackTrace());
            }

            //Call requires API level 26
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                LocalTime start = LocalTime.parse(res.getString(6).trim());
                LocalTime end = LocalTime.parse(res.getString(7).trim());
                Slot slot = new Slot(start, end);
                temp.setSlot(slot);
            }

            temp.setPrice(res.getDouble(8));

            ReviewSystem rs = getReviewSystemByLessonID(lessonID);
            temp.setReview_system(rs);

            result.add(temp);
        }

        return result  ;
    }

    public boolean addSlots(Slot slot)
    {
        //TODO
        return false;
    }
    /**
     *
     * @param avs
     * @return
     */
    public boolean addAvaibilities(ArrayList<Avaibility> avs, int tutorID)
    {
        //TODO
        SQLiteDatabase MyData = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Log.i("addAvaibilities ", "DANS AvaibilitiesList:"+ avs);

        for (Avaibility av : avs)
        {

            //add avaibility
            contentValues.put("ID",av.getID());
            contentValues.put("TutorID",tutorID);
            String strDate  = new SimpleDateFormat(Avaibility.DATE_FORMAT).format(av.getDate());
            contentValues.put("date",strDate);

            long result = MyData.insert("avaibility",null,contentValues);

            if(result==-1) return false;
            Log.i("addAvaibilities ", "FINI D INSERER AVAIBILiTY ");

            //add slot
            //get last avaibilities created
            int avID = 0;
            Cursor cursor = MyData.rawQuery("SELECT * FROM avaibility ORDER BY ID DESC LIMIT 1 ", null);
            if (cursor.moveToFirst()){
                do {
                    // Passing values
                    avID = cursor.getInt(0);
                } while(cursor.moveToNext());
            }

            ArrayList<Slot> slots = av.getSlots();
            for(Slot sl : slots)
            {
                contentValues.put("ID",sl.getID());
                contentValues.put("AvaibilityID",avID);

                String start_time="";
                String end_time="";


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                     start_time = sl.getStartTime().format(DateTimeFormatter.ofPattern(Slot.getTIME_FORMAT()));
                     end_time = sl.getEndTime().format(DateTimeFormatter.ofPattern(Slot.getTIME_FORMAT()));
                }


               // String start_time  = new SimpleDateFormat(Slot.getTIME_FORMAT()).format(sl.getStartTime());
                //String end_time  = new SimpleDateFormat(Slot.getTIME_FORMAT()).format(sl.getEndTime());

                contentValues.put("start_time",start_time);
                contentValues.put("end_time",end_time);

                long result2 = MyData.insert("slot",null,contentValues);

                if(result2==-1) return false;
                Log.i("addAvaibilities ", "FINI D INSERER SLOT ");
            }

        }
        return true;

    }

    public boolean CreatedDefaultAvaibilities()
    {
            //TODO
        SQLiteDatabase MyData = this.getWritableDatabase();
        //get the last tutor object create
        int tutorID = 0;
        Cursor cursor = MyData.rawQuery("SELECT * FROM user WHERE roleID= ? ORDER BY ID DESC LIMIT 1 ", new String[] {String.valueOf(Tutor.getStaticRoleID())});
        if (cursor.moveToFirst()){
            do {
                // Passing values
                tutorID = cursor.getInt(0);
            } while(cursor.moveToNext());
        }
        //put the tutorOD on the availabilies
        ArrayList<Avaibility> DefaultAvaibility = Avaibility.DefaultAvaibility();
        this.addAvaibilities(DefaultAvaibility,tutorID);
        return true;
    }





    //TODO  add tutor avaibilities function


    // endregion
}
