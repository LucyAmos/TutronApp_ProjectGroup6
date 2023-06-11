package com.example.tutrong6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tutrong6.BEANS.*;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

//attribut
    private static final String DATABASE_NAME = "tutronDB";
    private static final int DATABASE_VERSION = 1;

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


                "  FOREIGN KEY (roleID) REFERENCES role (ID),\n" +
                "  FOREIGN KEY (credit_card_id) REFERENCES creditcard (ID),\n" +
                "  FOREIGN KEY (addressID) REFERENCES address (ID)\n" +
                "\n" +
                ")");

        //POPULER LES TABLES

        //add the 3 roles of an user in the database
        sqLiteDatabase.execSQL("INSERT INTO role (name) VALUES\n" +
                "(\"Administrator\"),\n" +
                "(\"Student\"),\n" +
                "(\"Tutor\")");

        //populate somes admins in the database
        sqLiteDatabase.execSQL("INSERT INTO user (roleID, first_name, last_name, email, password, education_level, native_language, description, profile_picture, addressID, credit_card_id) VALUES\n" +
                "( 1, 'Admin1', 'Java', 'admin1@tutron.ca', '1234', NULL, NULL, NULL, NULL, NULL, NULL),\n" +
                "( 1, 'Admin2', 'Android', 'admin2@tutron.ca', '5678', NULL, NULL, NULL, NULL, NULL, NULL)");


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

}
