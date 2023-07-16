package com.example.tutrong6.GUI.STUDENT_interfaces;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.tutrong6.BEANS.Avaibility;
import com.example.tutrong6.BEANS.Complaint;
import com.example.tutrong6.BEANS.Lesson;
import com.example.tutrong6.BEANS.ReviewSystem;
import com.example.tutrong6.BEANS.Slot;
import com.example.tutrong6.BEANS.Topic;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.GUI.ADMIN_interfaces.AdminInboxActivity;
import com.example.tutrong6.GUI.ADMIN_interfaces.ComplaintOverviewActivity;
import com.example.tutrong6.GUI.ADMIN_interfaces.DatePickerFragment;
import com.example.tutrong6.GUI.ReviewsBoxActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.AddTopicActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorHubActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorTopicsActivity;
import com.example.tutrong6.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class AboutTopicActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    DBHelper DB;

    String tutorIdIntent = getIntent().getStringExtra("tutorId");
    String topicIdIntent = getIntent().getStringExtra("topicId");
    private int tutorId = Integer.parseInt(tutorIdIntent);
    private int topicId = Integer.parseInt(topicIdIntent);

    ArrayList<Slot> timeSlots = new ArrayList<>();

    Dialog bookSession;
    private int userID;


    private Date sessionDate;
    private Slot sessionTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_and_tutor_studentpov);

        SessionManagement sessionManagement = new SessionManagement(AboutTopicActivity.this);

        userID = sessionManagement.getSession();


        TextView topic = findViewById(R.id.about_topic);
        TextView yearsOfExperience = findViewById(R.id.years_of_experience);
        TextView topicDescription = findViewById(R.id.topic_desc);
        ImageView profilePicture =  findViewById(R.id.tutor_profile_pic);
        TextView firstName = findViewById(R.id.tutor_first_name);
        TextView lastName = findViewById(R.id.tutor_last_name);
        TextView nativeLanguage = findViewById(R.id.native_language);
        TextView educationLevel = findViewById(R.id.education_level);
        TextView tutorDescription = findViewById(R.id.tutor_desc);
        TextView hourlyRate = findViewById(R.id.hourly_rate);
        TextView lessonsGiven = findViewById(R.id.lessons_given);
        RatingBar averageRating = findViewById(R.id.ratingBar);



        topic.setText(DB.getTopicByID(topicId).getName());
        yearsOfExperience.setText(DB.getTopicByID(topicId).getYears_of_experience());
        topicDescription.setText(DB.getTopicByID(topicId).getDescription());

        byte[] profilePictureBytes = DB.getTutorByID(tutorId).getProfile_picture();
        Bitmap profilePictureBitmap = BitmapFactory.decodeByteArray(profilePictureBytes, 0, profilePictureBytes.length);
        profilePicture.setImageBitmap(profilePictureBitmap);

        firstName.setText(DB.getTutorByID(tutorId).getFirst_name());
        lastName.setText(DB.getTutorByID(tutorId).getLast_name());
        nativeLanguage.setText(DB.getTutorByID(tutorId).getNative_language());
        educationLevel.setText(DB.getTutorByID(tutorId).getEducation_level());
        tutorDescription.setText(DB.getTutorByID(tutorId).getDescription());
        hourlyRate.setText(String.valueOf(DB.getTutorByID(tutorId).getHourly_rate()));
        lessonsGiven.setText(String.valueOf(DB.getCountGivenLesson(tutorId)));
        averageRating.setRating((float) DB.getAverageTutorRating(tutorId));

        Button fab = findViewById(R.id.home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AboutTopicActivity.this, StudentHubActivity.class);
                startActivity(intent);

            }

        });


        Button reviewsBtn = findViewById(R.id.review_btn);
        reviewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(AboutTopicActivity.this, ReviewsBoxActivity.class);
                intent.putExtra("tutorId", tutorId);
                startActivity(intent);

            }

        });

        Button otherTopicsBtn = findViewById(R.id.see_other_topics_btn);
        otherTopicsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AboutTopicActivity.this, OtherTopicsActivity.class);
                intent.putExtra("tutorId", tutorId);
                startActivity(intent);


            }
        });

        Button requestSessionBtn = findViewById(R.id.request_session_btn);
        requestSessionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bookSession = new Dialog(AboutTopicActivity.this);
                bookSession.setContentView(R.layout.book_session_dialogue);
                bookSession.show();
                bookSession.setCancelable(true);

                Window windowOffer = bookSession.getWindow();
                if (windowOffer != null) {
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(windowOffer.getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    windowOffer.setAttributes(layoutParams);
                }

                Button bookRequest = bookSession.findViewById(R.id.open_date_time);
                //TextView dateText = bookSession.findViewById(R.id.date);
                //Spinner timeText = bookSession.findViewById(R.id.time);
                Button dateTimePickerButton = bookSession.findViewById(R.id.book_request_btn);

                dateTimePickerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DialogFragment datePicker = new DatePickerFragment();
                        datePicker.show(getSupportFragmentManager(), "Date Picker");
                    }
                });


                bookRequest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle button click

                        TextView date = findViewById(R.id.date);
                        Spinner time = bookSession.findViewById(R.id.time);

                        String dateText = date.getText().toString().trim();
                        String timeText = time.getSelectedItem().toString();

                        Tutor tutor = DB.getTutorByID(tutorId);

                        double tutorHourlyRate = tutor.getHourly_rate();



                        if(dateText.isEmpty() || timeText.isEmpty()
                        )
                        {
                            Toast.makeText(AboutTopicActivity.this, "Please, fill in ALL the fields", Toast.LENGTH_SHORT).show();
                        }else{
                            Lesson addLesson = new Lesson(userID, tutorId, topicId, sessionDate, sessionTime, null);
                            Boolean added = DB.addLesson(addLesson, tutorHourlyRate);
                            if (added) {
                                Toast.makeText(AboutTopicActivity.this, "Lesson Requested Successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(AboutTopicActivity.this, SearchBoxActivity.class));
                                bookSession.dismiss();
                            } else {
                                Toast.makeText(AboutTopicActivity.this, "Lesson Request Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }}



                });


            }

        });
    }





@Override
public void onDateSet(DatePicker view, int year, int month, int day){
    Calendar c = Calendar.getInstance();
    c.set(Calendar.YEAR, year);
    c.set(Calendar.MONTH, month);
    c.set(Calendar.DAY_OF_MONTH, day);

    TextView date = findViewById(R.id.date);
    Spinner time = bookSession.findViewById(R.id.time);


    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
    String selectedDate = dateFormat.format(c.getTime());
    date.setText(selectedDate);
    sessionDate = c.getTime();


    Map<Date, ArrayList<Slot>> availability = DB.getAvaibilitiesByTutorID(tutorId);
    ArrayList<Slot> timeSlots = availability.get(c.getTime());


    ArrayAdapter<Slot> slotAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeSlots);
    slotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    time.setAdapter(slotAdapter);
    time.setVisibility(View.VISIBLE);

    time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Slot selectedSlot = timeSlots.get(position);
            sessionTime=selectedSlot;
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing
        }
    });
}










}
