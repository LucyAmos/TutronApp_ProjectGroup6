package com.example.tutrong6.GUI.STUDENT_interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.tutrong6.GUI.LandingPageActivity;
import com.example.tutrong6.GUI.PurchaseRequestActivity;
import com.example.tutrong6.GUI.STUDENT_interfaces.StudentApprovedLessonsActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorHubActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorTopicsActivity;
import com.example.tutrong6.GUI.WelcomePage;
import com.example.tutrong6.R;

public class StudentHubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        DBHelper DataBase = new DBHelper(this);

        //get the ID of the logged user
        SessionManagement sessionManagement = new SessionManagement(StudentHubActivity.this);
        int userID = sessionManagement.getSession();
        Log.i("USER ID LOGGED", "ICI "+ userID);
        User session_user= DataBase.getUserbyID(userID);
        Log.i("USER_session(STUHUB)", "ici "+ session_user);



        Button myTopicsButton = findViewById(R.id.search_button);
        myTopicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                Intent intent = new Intent(StudentHubActivity.this, SearchBoxActivity.class);
                startActivity(intent);
            }


        });
        Button purchaseRequestButton = findViewById(R.id.purchase_request_button);
        purchaseRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                Intent intent = new Intent(StudentHubActivity.this, PurchaseRequestActivity.class);
                startActivity(intent);;
               /* Intent intent = new Intent(TutorHubActivity.this, PurchaseRequestInboxActivity.class);
                startActivity(intent);*/
            }


        });

        Button myReviewsButton = findViewById(R.id.approved_button);
        myReviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                Intent intent = new Intent(StudentHubActivity.this, StudentApprovedLessonsActivity.class);
                startActivity(intent);
            }


        });

        Button logout_btn = findViewById(R.id.logoff_button);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //logout
                sessionManagement.removeSession();
                //aller a la page d accueil.
                startActivity(new Intent(StudentHubActivity.this, LandingPageActivity.class));

            }
        });

    }


}
