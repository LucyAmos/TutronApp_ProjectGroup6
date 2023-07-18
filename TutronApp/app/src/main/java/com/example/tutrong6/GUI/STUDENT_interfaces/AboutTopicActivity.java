package com.example.tutrong6.GUI.STUDENT_interfaces;


import static android.widget.Toast.LENGTH_LONG;

import static com.example.tutrong6.BEANS.Avaibility.DATE_FORMAT;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tutrong6.BEANS.Avaibility;
import com.example.tutrong6.BEANS.Complaint;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.tutrong6.BEANS.Lesson;
import com.example.tutrong6.BEANS.Slot;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.GUI.ADMIN_interfaces.DatePickerFragment;
import com.example.tutrong6.GUI.ReviewsBoxActivity;
import com.example.tutrong6.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

public class AboutTopicActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    DBHelper DB;

    private int tutorId;
    private int topicId;

    ArrayList<Slot> timeSlots = new ArrayList<>();

    Dialog bookSession;
    private int userID;

    private String dateText = "";
    private String timeText = "";


    private Date sessionDate;
    private Slot sessionTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_and_tutor_studentpov);

        DBHelper DB = new DBHelper(this);

        String tutorIdIntent = getIntent().getStringExtra("tutorId");
        String topicIdIntent = getIntent().getStringExtra("topicId");
        tutorId = Integer.parseInt(tutorIdIntent);
        topicId = Integer.parseInt(topicIdIntent);

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
        yearsOfExperience.setText(String.valueOf(DB.getTopicByID(topicId).getYears_of_experience()));
        topicDescription.setText(DB.getTopicByID(topicId).getDescription());

        Tutor tutor = DB.getTutorByID(tutorId);

        byte[] profilePictureBytes =tutor.getProfile_picture();
        if(profilePictureBytes != null)
        {
            Bitmap profilePictureBitmap = BitmapFactory.decodeByteArray(profilePictureBytes, 0, profilePictureBytes.length);
            profilePicture.setImageBitmap(profilePictureBitmap);
        }


        firstName.setText(tutor.getFirst_name());
        lastName.setText(tutor.getLast_name());
        nativeLanguage.setText(tutor.getNative_language());
        educationLevel.setText(tutor.getEducation_level());
        tutorDescription.setText(tutor.getDescription());
        hourlyRate.setText(String.valueOf(tutor.getHourly_rate()));
        lessonsGiven.setText(String.valueOf(DB.getCountGivenLesson(tutorId)));
        averageRating.setRating((float) DB.getAverageTutorRating(tutorId));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.home_fab);
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

                Button bookRequest = bookSession.findViewById(R.id.book_request_btn);
                //TextView dateText = bookSession.findViewById(R.id.date);
                //Spinner timeText = bookSession.findViewById(R.id.time);
                Button dateTimePickerButton = bookSession.findViewById(R.id.open_date_time);

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



                        Tutor tutor = DB.getTutorByID(tutorId);

                        double tutorHourlyRate = tutor.getHourly_rate();

                        Log.e("LESSON REQUEST" , "dateText = "+dateText +"timeText="+timeText+ "sessionDate= "+sessionDate +"sessionTime= "+sessionTime);
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
    DBHelper DB = new DBHelper(this);
    Calendar c = Calendar.getInstance();
    c.set(Calendar.YEAR, year);
    c.set(Calendar.MONTH, month);
    c.set(Calendar.DAY_OF_MONTH, day);

    TextView date = bookSession.findViewById(R.id.date);
    Spinner time = bookSession.findViewById(R.id.time);



    //date.setText(DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime()));
    //timeText = time.getSelectedItem().toString();


    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
    String selectedDate = dateFormat.format(c.getTime());
    dateText = selectedDate;
    date.setText(selectedDate);
    Date selectedDateDateFormat = null;
    try {
        selectedDateDateFormat = dateFormat.parse(selectedDate);
    } catch (ParseException e) {
        throw new RuntimeException(e);
    }
    String real_month = month>=0&&month<=8?"0"+(month+1):""+(month+1);
    try {
        sessionDate = new SimpleDateFormat(Complaint.getDATE_FORMAT()).parse(day +"/"+real_month+"/"+year);
    } catch (ParseException e) {
        Log.e("ERROR TEMP_BTN", "onDateSet: "+e.getStackTrace() );
    }
   // sessionDate = c.getTime();


    Map<Date, ArrayList<Slot>> availability = DB.getAvaibilitiesByTutorID(tutorId);

    ArrayList<Slot> timeSlots = availability.get(selectedDateDateFormat);


    if (timeSlots != null && !timeSlots.isEmpty()) {
        ArrayAdapter<Slot> slotAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeSlots);
        slotAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(slotAdapter);
        time.setVisibility(View.VISIBLE);

        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Slot selectedSlot = timeSlots.get(position);
                timeText= String.valueOf(selectedSlot);
                sessionTime = selectedSlot;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    } else {
        Toast.makeText(AboutTopicActivity.this, "No Available Timeslots", LENGTH_LONG).show();
    }
}










}
