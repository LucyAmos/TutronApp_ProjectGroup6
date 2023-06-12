package com.example.tutrong6.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.R;

public class LoginPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DBHelper DataBase = new DBHelper(this);


        EditText loginEmail = findViewById(R.id.email_login_input);
        EditText loginPassword = findViewById(R.id.password_login_input);
        Button login = findViewById(R.id.loginlogin_button);
        TextView warningSign = findViewById(R.id.warning_sign_login);
        TextView warningSignEmail = findViewById(R.id.warning_sign_invalid_account);
        TextView warningSignPassword = findViewById(R.id.warning_sign_incorrect_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = loginEmail.getText().toString().trim();
                String pass = loginPassword.getText().toString().trim();


                if(email.equals("") || pass.equals(""))
                    Toast.makeText(LoginPageActivity.this, "Please, fill in ALL the fields", Toast.LENGTH_SHORT).show();
                else{

                    Boolean check_user_pass = DataBase.checkEmailPassword(email,pass);
                    if(check_user_pass==true){

                        //prendre les informations du client connecté
                        User logged_user = DataBase.getUserLoggedInfoByEmail(email);
                        Log.i("LOGGED USER", "ici: " + logged_user);

                        //creer sa session
                        SessionManagement sessionManagement = new SessionManagement(LoginPageActivity.this);
                        sessionManagement.saveSession(logged_user);

                        //message d approbation
                        Toast.makeText(LoginPageActivity.this, "Log in Successful ", Toast.LENGTH_SHORT).show();
                        //diriger vers la page d accueil
                         startActivity(new Intent(LoginPageActivity.this, WelcomePage.class));

                    }
                    else{
                        int check_email_existance = DataBase.checkEmail(email) == false? 0:1;
                        Log.i("CHECK_EMAIL", "" + check_email_existance);
                        switch (check_email_existance)
                        {
                            case 0:
                                Toast.makeText(LoginPageActivity.this, "This account does not exist, SIGN UP!", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                Toast.makeText(LoginPageActivity.this, "INCORRECT Credentials", Toast.LENGTH_SHORT).show();
                                break;
                        }

                    }
                }

               // Intent intent = new Intent(LoginPageActivity.this, WelcomePage.class);
               // startActivity(intent);
            }
        });
}}
