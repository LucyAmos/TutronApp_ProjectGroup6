package com.example.tutrong6.GUI.TUTOR_interfaces;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TutorHubActivity extends AppCompatActivity {

    Dialog setHourlyRate;

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
        String hourlyRate_str = tutorHourlyRate == Tutor.getNOT_ASSIGNED()? "0.00" : String.valueOf(tutorHourlyRate);
        hourlyRate.setText(hourlyRate_str);

        if(tutorHourlyRate == -1){
            setHourlyRate = new Dialog(TutorHubActivity.this);
            setHourlyRate.setContentView(R.layout.hourly_rating_dialogue);
            setHourlyRate.show();
            setHourlyRate.setCancelable(false);

            Window windowOffer = setHourlyRate.getWindow();
            if (windowOffer != null) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(windowOffer.getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                windowOffer.setAttributes(layoutParams);
            }

            Button setRate = setHourlyRate.findViewById(R.id.set_hourly_rating_Btn);
            EditText rate = setHourlyRate.findViewById(R.id.rate);





            setRate.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View v) {

                    String rateStr = rate.getText().toString().trim();
                    //Toast.makeText(TutorHubActivity.this, rateStr, Toast.LENGTH_SHORT).show();

                    // Handle button click
                    if(!rateStr.equals("")){
                        double doubleRate = Double.parseDouble(rateStr);
                        DataBase.updateHourlyRate(userID,doubleRate);
                        setHourlyRate.dismiss();
                        recreate();
                        Toast.makeText(TutorHubActivity.this, "You Have Now Set Your Hourly Rate", Toast.LENGTH_SHORT).show();
                    //} else if (rateStr != null  && !isDouble(rateStr)) {
                        //Toast.makeText(TutorHubActivity.this, "Invalid Input", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(TutorHubActivity.this, "Please Fill In Parameter", Toast.LENGTH_SHORT).show();
                    }


                }


            });
        }

        totalLessons.setText(String.valueOf(DataBase.getCountGivenLesson(userID)));
        myRating.setRating((float) DataBase.getAverageTutorRating(userID));





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

    Button myReviewsButton = findViewById(R.id.my_reviews_button);
        myReviewsButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Handle button click
            Intent intent = new Intent(TutorHubActivity.this, com.example.tutrong6.GUI.ReviewsBoxActivity.class);
            startActivity(intent);
        }


    });

    }
/*
    private boolean isDouble(String value) {

        String doublePattern = "[0-9]+(\\\\.[0-9]+)?";
        Pattern p = java.util.regex.Pattern.compile(doublePattern);
        Matcher m = p.matcher(value);
        return m.matches();
    }*/

}
