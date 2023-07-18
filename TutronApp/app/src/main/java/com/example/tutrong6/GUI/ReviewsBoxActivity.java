package com.example.tutrong6.GUI;

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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutrong6.BEANS.Lesson;
import com.example.tutrong6.BEANS.ReviewSystem;
import com.example.tutrong6.BEANS.Student;
import com.example.tutrong6.BEANS.Topic;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.GUI.STUDENT_interfaces.StudentHubActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorHubActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorTopicsActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.UpdateTopicsActivity;
import com.example.tutrong6.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;


public class ReviewsBoxActivity extends AppCompatActivity implements ReviewRecyclerInterface {

    RecyclerView recyclerView;

    Map<int[], ReviewSystem> reviews = new Map<int[], ReviewSystem>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsValue(@Nullable Object o) {
            return false;
        }

        @Nullable
        @Override
        public ReviewSystem get(@Nullable Object o) {
            return null;
        }

        @Nullable
        @Override
        public ReviewSystem put(int[] ints, ReviewSystem reviewSystem) {
            return null;
        }

        @Nullable
        @Override
        public ReviewSystem remove(@Nullable Object o) {
            return null;
        }

        @Override
        public void putAll(@NonNull Map<? extends int[], ? extends ReviewSystem> map) {

        }

        @Override
        public void clear() {

        }

        @NonNull
        @Override
        public Set<int[]> keySet() {
            return null;
        }

        @NonNull
        @Override
        public Collection<ReviewSystem> values() {
            return null;
        }

        @NonNull
        @Override
        public Set<Entry<int[], ReviewSystem>> entrySet() {
            return null;
        }
    };
    DBHelper DB;

    DBHelper DataBase = new DBHelper(this);
    ReviewsAdapter adapter;
    private int tutorId;


    private int userID;
    private User session_user;


    Dialog editReviewDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews_viewer);
        //adapter.setLongClickListner(this);

        SessionManagement sessionManagement = new SessionManagement(ReviewsBoxActivity.this);

        userID = sessionManagement.getSession();
        session_user= DataBase.getUserbyID(userID);


        if(session_user.getRoleID() == Tutor.getStaticRoleID()){
            tutorId = userID;
        }else{
            tutorId = getIntent().getIntExtra("tutorId",0);
            //tutorId = Integer.parseInt(tutorIdIntent);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(session_user.getRoleID() == Tutor.getStaticRoleID()){
                    Intent intent = new Intent(ReviewsBoxActivity.this, TutorHubActivity.class);
                    startActivity(intent);
                }else if(session_user.getRoleID() == Student.getStaticRoleID()){
                    Intent intent = new Intent(ReviewsBoxActivity.this, StudentHubActivity.class);
                    startActivity(intent);
                }


            }

        });

        recyclerView = findViewById(R.id.reviews_recycler);

        DB = new DBHelper(this);

        reviews = DB.getAllReviewSystems(tutorId);
        int reviewTotals = DataBase.getAllReviewSystems(tutorId).size();
        // dateStr = new SimpleDateFormat(Plainte.getDATE_FORMAT()).format(etat_de_suspension.get(i));



        adapter = new ReviewsAdapter(this,this, reviews);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        TextView totalReviews = findViewById(R.id.number_of_reviews);
        TextView tutorFirstName = findViewById(R.id.tutor_first_name);
        TextView tutorLastName = findViewById(R.id.tutor_last_name);
        RatingBar averageRating = findViewById(R.id.ratingBar);


        totalReviews.setText(String.valueOf(reviewTotals));
        tutorFirstName.setText(DB.getTutorByID(tutorId).getFirst_name());
        tutorLastName.setText(DB.getTutorByID(tutorId).getLast_name());
        averageRating.setRating((float) DB.getAverageTutorRating(tutorId));



    }


    @Override
    public void onItemClick(int position) {

        int[] keys = (int[]) reviews.keySet().toArray()[position];
        //ReviewSystem reviewSystemKeys = reviews.get(keys);

        if(session_user.getRoleID() == Student.getStaticRoleID() && keys[0] == session_user.getID()) {

            ReviewSystem reviewSystemPos = reviews.get(position);
            Date ratingDate = reviewSystemPos.getRating_date();

            Calendar currentTime = Calendar.getInstance();
            Calendar oneWeekAgo = Calendar.getInstance();
            oneWeekAgo.add(Calendar.WEEK_OF_YEAR, -1);

            if (ratingDate.before(oneWeekAgo.getTime())) {
                Toast.makeText(ReviewsBoxActivity.this, "ONE WEEK LIMIT: You Can No Longer Edit Review", Toast.LENGTH_SHORT).show();
            } else {

                editReviewDialog = new Dialog(ReviewsBoxActivity.this);
                editReviewDialog.setContentView(R.layout.offer_topic_dialogue);
                editReviewDialog.show();
                editReviewDialog.setCancelable(true);

                Window windowOffer = editReviewDialog.getWindow();
                if (windowOffer != null) {
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(windowOffer.getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    windowOffer.setAttributes(layoutParams);
                }

                RatingBar givenRating = editReviewDialog.findViewById(R.id.ratingBar);
                EditText reviewDesc = editReviewDialog.findViewById(R.id.review_message_input);
                Button update = editReviewDialog.findViewById(R.id.edit_review_btn);
                Button delete = editReviewDialog.findViewById(R.id.edit_review_btn);

                reviewDesc.setText(reviewSystemPos.getReview());
                givenRating.setRating((float) reviewSystemPos.getRating());

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle button click

                        double rating = givenRating.getRating();
                        String review = reviewDesc.getText().toString().trim();


                        if (review.isEmpty() || rating == 0
                        ) {
                            Toast.makeText(ReviewsBoxActivity.this, "Please, fill in ALL the fields", Toast.LENGTH_SHORT).show();
                        } else {
                            ReviewSystem updateReview = new ReviewSystem(rating, reviewSystemPos.getIs_rating_anonymous(), reviewSystemPos.getRating_date(), reviewSystemPos.getIs_topic_reviewed(), review);
                            Boolean addedUpdate = DataBase.updateRatingLesson(keys[2], updateReview);

                            if (addedUpdate) {
                                Toast.makeText(ReviewsBoxActivity.this, "Review Updated Successfully", Toast.LENGTH_SHORT).show();
                                editReviewDialog.dismiss();
                                recreate();
                            } else {
                                Toast.makeText(ReviewsBoxActivity.this, "Review Update Unsuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                });


                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Boolean removed = DataBase.deleteRatingLesson(keys[2]);
                        if (removed) {
                            Toast.makeText(ReviewsBoxActivity.this, "Review Removed Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ReviewsBoxActivity.this, TutorTopicsActivity.class));
                        } else {
                            Toast.makeText(ReviewsBoxActivity.this, "Review Removal Unsuccessful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tutron_menu, menu);
        return true;
    }




}
