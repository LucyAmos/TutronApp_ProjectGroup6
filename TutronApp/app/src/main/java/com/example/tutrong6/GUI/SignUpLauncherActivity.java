package com.example.tutrong6.GUI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.GUI.STUDENT_interfaces.StudentSignUp;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorSignUpActivity;
import com.example.tutrong6.R;

public class SignUpLauncherActivity extends AppCompatActivity {

    DBHelper DataBase = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_signup);

        Button tutorButton = findViewById(R.id.tutor_btn);
        tutorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
               // Tutor test = new Tutor("tutor","test","tt@gmail.com","test","bachelor","english","just for test purpose",null);
                //DataBase.addTutor(test);
                Intent intent = new Intent(SignUpLauncherActivity.this, TutorSignUpActivity.class);
                startActivity(intent);
            }


        });
        Button studentButton = findViewById(R.id.student_btn);
        studentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                Intent intent = new Intent(SignUpLauncherActivity.this, StudentSignUp.class);
                startActivity(intent);
            }


    });
}}