
package com.example.tutrong6.GUI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import com.example.tutrong6.BEANS.*;
import com.example.tutrong6.DAO.*;
import com.example.tutrong6.R;


import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentSignUp extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);

        DBHelper DataBase = new DBHelper(this);

        EditText studentFirstName = findViewById(R.id.first_name_student_input);
        EditText studentLastName = findViewById(R.id.last_name_student_input);
        EditText studentEmail = findViewById(R.id.email_student_input);
        EditText studentPassword = findViewById(R.id.password_student_input);
        EditText studentAddress = findViewById(R.id.address_student_input);
        EditText studentCity = findViewById(R.id.city_student_input);
        EditText studentRegion = findViewById(R.id.Region_student_input);
        EditText studentCountry = findViewById(R.id.Country_student_input);
        EditText studentPostal = findViewById(R.id.postal_student_input);



        EditText studentCardHolderName = findViewById(R.id.cardholder_name_input);
        EditText studentCardNumber = findViewById(R.id.card_number_input);
        EditText studentCVV = findViewById(R.id.CVV_number_input);
        EditText studentExDate = findViewById(R.id.card_exdate_input);
        Button studentSubmit = findViewById(R.id.submit_student_button);
        //TextView warningSign = findViewById(R.id.warning_sign_student);

        //TextView warningSignEmail = findViewById(R.id.warning_sign_email_student);

        studentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String first_name = studentFirstName.getText().toString().trim();
                String last_name = studentLastName.getText().toString().trim();
                String email = studentEmail.getText().toString().trim();
                String password = studentPassword.getText().toString().trim();

                String street_address = studentAddress.getText().toString().trim();
                String city = studentCity.getText().toString().trim();
                String region = studentRegion.getText().toString().trim();
                String country = studentCountry.getText().toString().trim();
                String postal = studentPostal.getText().toString().trim();

                String card_holder_name = studentCardHolderName.getText().toString().trim();
                String card_number = studentCardNumber.getText().toString().trim();
                String cvc = studentCVV.getText().toString().trim();
                //int cvc = Integer.parseInt(studentCVV.getText().toString().trim());
                String exp_date = studentExDate.getText().toString().trim();


                if (first_name.isEmpty() || last_name.isEmpty() || email.isEmpty() || password.isEmpty() ||
                        street_address.isEmpty() || city.isEmpty() || region.isEmpty() || country.isEmpty() || postal.isEmpty() ||
                        card_holder_name.isEmpty() || card_number.isEmpty() || cvc.isEmpty() || exp_date.isEmpty())

                    Toast.makeText(StudentSignUp.this, "Please, fill in ALL the fields", Toast.LENGTH_SHORT).show();

                else {

                    if (!User.isValidEmailAddressFormat(email)) {
                        Toast.makeText(StudentSignUp.this, "Invalid Email Format", Toast.LENGTH_SHORT).show();
                    } else {

                        Log.i("email Value=", " " + email);

                        int checkUser = DataBase.checkEmail(email) == false ? 0 : 1;

                        switch (checkUser) {
                            case 0:
                                Address address = new Address(street_address, city, region, postal, country);
                                CreditCard credit_card = new CreditCard(card_number, card_holder_name, exp_date, Integer.parseInt(cvc));
                                Student student = new Student(first_name, last_name, email, password, address, credit_card);

                                Boolean insert = DataBase.addStudent(student);
                                if (insert == true) {
                                    Toast.makeText(StudentSignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(StudentSignUp.this, LoginPageActivity.class));

                                } else {
                                    Toast.makeText(StudentSignUp.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 1:
                                Toast.makeText(StudentSignUp.this, "You ALREADY HAVE an Account, please LOG IN", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                }


            }


				/*Intent intent = new Intent(StudentSignUp.this, LoginPageActivity.class);
				startActivity(intent);*/

        });
    }


}

	
	
