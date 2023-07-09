package com.example.tutrong6.GUI.TUTOR_interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.GUI.STUDENT_interfaces.StudentSignUp;
import com.example.tutrong6.GUI.SignUpLauncherActivity;
import com.example.tutrong6.GUI.WelcomePage;
import com.example.tutrong6.R;

public class TutorHubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_home_page);

        DBHelper DataBase = new DBHelper(this);

        TextView hourlyRate = findViewById(R.id.hourly_rate);
        TextView totalLessons = findViewById(R.id.total_lessons_number);
        RatingBar myRating = findViewById(R.id.ratingBar);

        SessionManagement sessionManagement = new SessionManagement(TutorHubActivity.this);

        int userID = sessionManagement.getSession();

        Tutor session_tutor = DataBase.getTutorByID(userID);

        double tutorHourlyRate = session_tutor.getHourly_rate();
        String hourlyRate_str = tutorHourlyRate == Tutor.getNOT_ASSIGNED()? "Not Given yet" : String.valueOf(tutorHourlyRate);
        hourlyRate.setText(hourlyRate_str);

        totalLessons.setText("0");
        myRating.setRating(0);





        Button myTopicsButton = findViewById(R.id.my_topics_button);
        myTopicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                Intent intent = new Intent(TutorHubActivity.this, TutorTopicsActivity.class);
                startActivity(intent);
            }


        });
        Button purchaseRequestButton = findViewById(R.id.purchase_request_button);
        purchaseRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                Toast.makeText(TutorHubActivity.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
               /* Intent intent = new Intent(TutorHubActivity.this, PurchaseRequestInboxActivity.class);
                startActivity(intent);*/
            }


        });
    }

}
