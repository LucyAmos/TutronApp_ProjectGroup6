package com.example.tutrong6.GUI.TUTOR_interfaces;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutrong6.BEANS.Tutor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutrong6.BEANS.Slot;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class TutorProfileActivity extends AppCompatActivity  {


    DBHelper DB;





    Dialog bookSession;
    private int userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile);

        SessionManagement sessionManagement = new SessionManagement(TutorProfileActivity.this);

        userID = sessionManagement.getSession();

        DBHelper DataBase = new DBHelper(this);

        Tutor session_tutor = DataBase.getTutorByID(userID);



        ImageView profilePicture = findViewById(R.id.tutor_profile_pic);
        TextView firstName = findViewById(R.id.tutor_first_name);
        TextView lastName = findViewById(R.id.tutor_last_name);
        TextView nativeLanguage = findViewById(R.id.native_language);
        TextView educationLevel = findViewById(R.id.education_level);
        TextView tutorDescription = findViewById(R.id.tutor_desc);
        TextView hourlyRate = findViewById(R.id.hourly_rate);
        TextView lessonsGiven = findViewById(R.id.lessons_given);
        RatingBar averageRating = findViewById(R.id.ratingBar);


        byte[] profilePictureBytes = session_tutor.getProfile_picture();
        if(profilePictureBytes !=null)
        {
            Bitmap profilePictureBitmap = BitmapFactory.decodeByteArray(profilePictureBytes, 0, profilePictureBytes.length);
            profilePicture.setImageBitmap(profilePictureBitmap);
        }



        firstName.setText(session_tutor.getFirst_name());
        lastName.setText(session_tutor.getLast_name());
        nativeLanguage.setText(session_tutor.getNative_language());
        educationLevel.setText(session_tutor.getEducation_level());
        tutorDescription.setText(session_tutor.getDescription());
        hourlyRate.setText(String.valueOf(session_tutor.getHourly_rate()));
        lessonsGiven.setText(String.valueOf(DataBase.getCountGivenLesson(userID)));
        averageRating.setRating((float) DataBase.getAverageTutorRating(userID));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(TutorProfileActivity.this, TutorHubActivity.class);
                startActivity(intent);

            }

        });


    }


}

