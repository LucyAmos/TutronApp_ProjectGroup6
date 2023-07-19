package com.example.tutrong6.GUI.STUDENT_interfaces;

import android.app.Dialog;
import android.os.Bundle;
import android.content.Intent;
//<<<<<<< Updated upstream
import android.util.Log;
//=======
//>>>>>>> Stashed changes
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutrong6.BEANS.Complaint;
import com.example.tutrong6.BEANS.Lesson;
import com.example.tutrong6.BEANS.ReviewSystem;
import com.example.tutrong6.BEANS.Student;
import com.example.tutrong6.BEANS.Topic;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.GUI.PurchaseRequestAdapter;
import com.example.tutrong6.GUI.PurchaseRequestRecyclerInterface;
import com.example.tutrong6.GUI.ReviewsBoxActivity;
import com.example.tutrong6.GUI.STUDENT_interfaces.AboutTopicActivity;
import com.example.tutrong6.GUI.STUDENT_interfaces.StudentHubActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorTopicsActivity;
import com.example.tutrong6.R;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class StudentApprovedLessonsActivity extends AppCompatActivity implements PurchaseRequestRecyclerInterface {

    RecyclerView recyclerView;

    ArrayList<Lesson> lessons = new ArrayList<>();
    DBHelper DB;

    DBHelper DataBase = new DBHelper(this);
    PurchaseRequestAdapter adapter;

    private int userID;

    private Lesson selected;
    private User session_user;


    Dialog doNextDialog;
    Dialog writeReviewDialog;
    Dialog fileComplaintDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_request_inbox);
        //adapter.setLongClickListner(this);

        TextView title = findViewById(R.id.purchase_request_title);
        TextView desc = findViewById(R.id.purchase_request_desc);

        title.setText("MY APPROVED LESSONS");
        desc.setText("View And Interact With Your Approved Lessons");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(StudentApprovedLessonsActivity.this, StudentHubActivity.class);
                startActivity(intent);

            }

        });

        SessionManagement sessionManagement = new SessionManagement(StudentApprovedLessonsActivity.this);
        userID = sessionManagement.getSession();
        session_user= DataBase.getUserbyID(userID);



        recyclerView = findViewById(R.id.purchase_request_recycler);

        DB = new DBHelper(this);

        lessons = DB.getStudentLessons(userID, 2);


        adapter = new PurchaseRequestAdapter(this,this, lessons, session_user);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public void onItemClick(int position) {


        selected = lessons.get(position);

        doNextDialog = new Dialog(StudentApprovedLessonsActivity.this);
        doNextDialog.setContentView(R.layout.do_next_dialogue);
        doNextDialog.show();
        doNextDialog.setCancelable(true);

        Window windowOffer = doNextDialog.getWindow();
        if (windowOffer != null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(windowOffer.getAttributes());
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
            windowOffer.setAttributes(layoutParams);
        }


        Button goToInfo = doNextDialog.findViewById(R.id.view_about_btn);
        Button  postReview = doNextDialog.findViewById(R.id.review_btn);
        Button  fileComplaint = doNextDialog.findViewById(R.id.file_complaint_Btn);


        goToInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                doNextDialog.dismiss();
                Intent intent = new Intent(StudentApprovedLessonsActivity.this, AboutTopicActivity.class);
                intent.putExtra("topicId", String.valueOf(selected.getTopicID()));
                intent.putExtra("tutorId", String.valueOf(selected.getTutorID()));
                startActivity(intent);
            }

        });


        postReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doNextDialog.dismiss();

                writeReviewDialog = new Dialog(StudentApprovedLessonsActivity.this);
                writeReviewDialog.setContentView(R.layout.review_dialogue);
                writeReviewDialog.show();
                writeReviewDialog.setCancelable(true);

                Window windowOffer = writeReviewDialog.getWindow();
                if (windowOffer != null) {
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(windowOffer.getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    windowOffer.setAttributes(layoutParams);
                }


                RatingBar givenRating = writeReviewDialog.findViewById(R.id.ratingBar);
                EditText reviewDesc = writeReviewDialog.findViewById(R.id.review_message_input);
                Button post = writeReviewDialog.findViewById(R.id.post_review_btn);
                Button postAnon = writeReviewDialog.findViewById(R.id.post_anonymous_review_btn);


                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle button click

                        double rating = givenRating.getRating();
                        String review = reviewDesc.getText().toString().trim();

                        LocalDateTime currentDateTime = LocalDateTime.now();
                        ZoneId zoneId = ZoneId.systemDefault();
                        Date currentDate = Date.from(currentDateTime.atZone(zoneId).toInstant());


                        if (review.isEmpty() || rating == 0
                        ) {
                            Toast.makeText(StudentApprovedLessonsActivity.this, "Please, fill in ALL the fields", Toast.LENGTH_SHORT).show();
                        } else if (!lessons.get(position).getReview_system().getIs_topic_reviewed()) {
                            ReviewSystem addReview = new ReviewSystem(rating, false, currentDate, true, review);
                            Boolean addedReview = DataBase.updateRatingLesson(selected.getID(), addReview);

                            if (addedReview) {
                                Toast.makeText(StudentApprovedLessonsActivity.this, "Review Added Successfully", Toast.LENGTH_SHORT).show();
                                writeReviewDialog.dismiss();
                                Intent intent = new Intent(StudentApprovedLessonsActivity.this, ReviewsBoxActivity.class);
                                intent.putExtra("tutorId", selected.getTutorID());
                                startActivity(intent);
                            } else {
                                Toast.makeText(StudentApprovedLessonsActivity.this, "Review Addition Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(StudentApprovedLessonsActivity.this, "You Have Already Reviewed This Topic For This Tutor", Toast.LENGTH_SHORT).show();
                        }
                    }

                });


                postAnon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle button click

                        double rating = givenRating.getRating();
                        String review = reviewDesc.getText().toString().trim();

                        Date currentDate = new Date();


                        if (review.isEmpty() || rating == 0
                        ) {
                            Toast.makeText(StudentApprovedLessonsActivity.this, "Please, fill in ALL the fields", Toast.LENGTH_SHORT).show();
                        } else if (!selected.getReview_system().getIs_topic_reviewed()) {
                            ReviewSystem addReview = new ReviewSystem(rating, true, currentDate, true, review);
                            Boolean addedReview = DataBase.updateRatingLesson(selected.getID(), addReview);

                            if (addedReview) {
                                Toast.makeText(StudentApprovedLessonsActivity.this, "Review Added Successfully", Toast.LENGTH_SHORT).show();
                                writeReviewDialog.dismiss();
                                Intent intent = new Intent(StudentApprovedLessonsActivity.this, ReviewsBoxActivity.class);
                                intent.putExtra("tutorId", selected.getTutorID());
                                startActivity(intent);
                            } else {
                                Toast.makeText(StudentApprovedLessonsActivity.this, "Review Addition Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(StudentApprovedLessonsActivity.this, "You Have Already Reviewed This Topic For This Tutor", Toast.LENGTH_SHORT).show();
                        }
                    }

                });

            }

        });



        fileComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                doNextDialog.dismiss();

                fileComplaintDialog = new Dialog(StudentApprovedLessonsActivity.this);
                fileComplaintDialog.setContentView(R.layout.complaint_dialogue);
                fileComplaintDialog.show();
                fileComplaintDialog.setCancelable(true);

                Window windowOffer = fileComplaintDialog.getWindow();
                if (windowOffer != null) {
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(windowOffer.getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    windowOffer.setAttributes(layoutParams);
                }


                EditText titleInput = fileComplaintDialog.findViewById(R.id.complaint_title_input);
                EditText complaintInput = fileComplaintDialog.findViewById(R.id.complaint_description_input);
                Button file = fileComplaintDialog.findViewById(R.id.file_complaint_btn);

                file.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle button click

                        String complaintTitle = titleInput.getText().toString().trim();
                        String complaint = complaintInput.getText().toString().trim();

                        Date currentDate = new Date();

                        Date nullDate = null;


                        if (complaintTitle.isEmpty() || complaint.isEmpty()
                        ) {
                            Toast.makeText(StudentApprovedLessonsActivity.this, "Please, fill in ALL the fields", Toast.LENGTH_SHORT).show();
                        } else {
                            Complaint fileMadeComplaint = new Complaint(userID, selected.getTutorID(), complaintTitle, complaint, 0, false, nullDate);
                            Boolean complaintFiled = DataBase.addComplaint(fileMadeComplaint);

                            if (complaintFiled) {
                                Toast.makeText(StudentApprovedLessonsActivity.this, "Complaint Filed Successfully", Toast.LENGTH_SHORT).show();
                                fileComplaintDialog.dismiss();
                            } else {
                                Toast.makeText(StudentApprovedLessonsActivity.this, "Complaint Filing Unsuccessful", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }

                });

                }

            });



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tutron_menu, menu);
        return true;
    }




}
