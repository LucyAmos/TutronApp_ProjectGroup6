package com.example.tutrong6.GUI.STUDENT_interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.GUI.PurchaseRequestActivity;
import com.example.tutrong6.GUI.STUDENT_interfaces.StudentApprovedLessonsActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorHubActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorTopicsActivity;
import com.example.tutrong6.R;

public class StudentHubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home_page);

        DBHelper DataBase = new DBHelper(this);



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

    }


}
