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
import com.example.tutrong6.GUI.STUDENT_interfaces.AboutTopicActivity;
import com.example.tutrong6.GUI.STUDENT_interfaces.StudentHubActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorHubActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorTopicsActivity;
import com.example.tutrong6.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


public class PurchaseRequestActivity extends AppCompatActivity implements PurchaseRequestRecyclerInterface {

    RecyclerView recyclerView;

    ArrayList<Lesson> lessons = new ArrayList<>();
    DBHelper DB;

    DBHelper DataBase = new DBHelper(this);
    PurchaseRequestAdapter adapter;

    private int userID;
    private User session_user;


    Dialog decisionDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_request_inbox);
        //adapter.setLongClickListner(this);

        SessionManagement sessionManagement = new SessionManagement(PurchaseRequestActivity.this);
        userID = sessionManagement.getSession();
        session_user = DataBase.getUserbyID(userID);


        recyclerView = findViewById(R.id.purchase_request_recycler);

        DB = new DBHelper(this);

        if (session_user.getRoleID() == Tutor.getStaticRoleID()) {
            lessons = DB.getTutorPurchaseDemands(userID);
        } else if (session_user.getRoleID() == Student.getStaticRoleID()) {
            lessons = DB.getLessonNonEvaluate(userID);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (session_user.getRoleID() == Tutor.getStaticRoleID()) {
                    Intent intent = new Intent(PurchaseRequestActivity.this, TutorHubActivity.class);
                    startActivity(intent);
                } else if (session_user.getRoleID() == Student.getStaticRoleID()) {
                    Intent intent = new Intent(PurchaseRequestActivity.this, StudentHubActivity.class);
                    startActivity(intent);
                }


            }

        });


        adapter = new PurchaseRequestAdapter(this, this, lessons, session_user);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public void onItemClick(int position) {

        if (session_user.getRoleID() == Tutor.getStaticRoleID()) {

            Lesson selected = lessons.get(position);

            decisionDialog = new Dialog(PurchaseRequestActivity.this);
            decisionDialog.setContentView(R.layout.request_dialogue);
            decisionDialog.show();
            decisionDialog.setCancelable(true);

            Window windowOffer = decisionDialog.getWindow();
            if (windowOffer != null) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(windowOffer.getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                windowOffer.setAttributes(layoutParams);
            }


            Button approve = decisionDialog.findViewById(R.id.acceptBtn);
            Button reject = decisionDialog.findViewById(R.id.rejectBtn);


            approve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click
                    selected.setStatus(Lesson.Status.APPROVED);
                    DataBase.updateStatusLesson(lessons.get(position).getID(), 2);
                    decisionDialog.dismiss();
                    recreate();
                    Toast.makeText(PurchaseRequestActivity.this, "You Have Approved This Lesson", Toast.LENGTH_SHORT).show();
                }

            });


            reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selected.setStatus(Lesson.Status.REJECTED);
                    DataBase.updateStatusLesson(lessons.get(position).getID(), 3);
                    decisionDialog.dismiss();
                    recreate();
                    Toast.makeText(PurchaseRequestActivity.this, "You Have Rejected This Lesson", Toast.LENGTH_SHORT).show();
                }
            });

        }


    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tutron_menu, menu);
        return true;
    }*/


}
