package com.example.tutrong6.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutrong6.R;

public class LoginPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText loginEmail = findViewById(R.id.email_login_input);
        EditText loginPassword = findViewById(R.id.password_login_input);
        Button login = findViewById(R.id.loginlogin_button);
        TextView warningSign = findViewById(R.id.warning_sign_login);
        TextView warningSignEmail = findViewById(R.id.warning_sign_invalid_account);
        TextView warningSignPassword = findViewById(R.id.warning_sign_incorrect_password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String lEM = loginEmail.getText().toString();
                String lPS = loginPassword.getText().toString();


                if (lEM.isEmpty() || lPS.isEmpty()) {
                    warningSign.setVisibility(View.VISIBLE);

                    //COMPARE INPUT TO DATABASE VALUE.  CHECK WHETHER EMAIL IS IN DB!!
                //}else if (){
                    //warningSignEmail.setVisibility(View.VISIBLE);

                    //COMPARE INPUT TO DATABASE VALUE.  CHECK WHETHER PASSWORD IS WITH ASSOCIATED EMAIL!!
                //}else if (){
                    //warningSignPassword.setVisibility(View.VISIBLE);
                }   else {
                    Intent intent = new Intent(LoginPageActivity.this, WelcomePage.class);
                    startActivity(intent);
                }
            }
        });
}}
