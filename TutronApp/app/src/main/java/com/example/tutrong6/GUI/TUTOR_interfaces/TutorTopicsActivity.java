package com.example.tutrong6.GUI.TUTOR_interfaces;

import android.app.Dialog;
import android.os.Bundle;
import android.content.Intent;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutrong6.BEANS.Complaint;
import com.example.tutrong6.BEANS.Topic;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.GUI.SignUpLauncherActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TopicsRecyclerInterface;
import com.example.tutrong6.GUI.WelcomePage;
import com.example.tutrong6.R;

import java.util.ArrayList;



public class TutorTopicsActivity extends AppCompatActivity implements TopicsRecyclerInterface {

    RecyclerView recyclerView;
    ArrayList<String> topic, offer;

    ArrayList<Topic> topics = new ArrayList<>();
    DBHelper DB;

    DBHelper DataBase = new DBHelper(this);
    TopicsAdapter adapter;

    private int totalTopics;
    private int offeredTopics;

    Dialog offerDialog;
    Dialog unofferDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_topics);
        adapter.setLongClickListner(this);

        SessionManagement sessionManagement = new SessionManagement(TutorTopicsActivity.this);
        int userID = sessionManagement.getSession();



        recyclerView = findViewById(R.id.topics_recycler);

        DB = new DBHelper(this);

        //topics = DB.activeComplaintsList();

        TopicsAdapter adapter = new TopicsAdapter(this,this, topics);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        totalTopics = DataBase.getAllTopics(userID).toArray().length;
        offeredTopics = DataBase.getOfferedTopics(userID).toArray().length;

        TextView totalTopicsNumber = findViewById(R.id.total_topics_number);
        TextView offeredTopicsNumber = findViewById(R.id.offered_topics_number);

        totalTopicsNumber.setText(String.valueOf(totalTopics));
        offeredTopicsNumber.setText(String.valueOf(offeredTopics));

        Button addButton = findViewById(R.id.add_topics_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click
                Intent intent = new Intent(TutorTopicsActivity.this, AddTopicActivity.class);
                String tutorId = String.valueOf(userID);
                intent.putExtra("tutorId", tutorId);
                startActivity(intent);
            }


        });

        if(totalTopics >= 20){
            addButton.setVisibility(View.INVISIBLE);
        }else if(totalTopics < 20){
            addButton.setVisibility(View.VISIBLE);
        }


/*
        DB = new DBHelper(this);
        title = new ArrayList<>();
        sneakPeek = new ArrayList<>();
        recyclerView = findViewById(R.id.complaints_recycler);
        adapter = new MyComplaintsAdapter(this, title, sneakPeek,complaintId, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); */
        //displaydata();


    }


    @Override
    public void onItemLongClick(int position) {

        Topic selected = topics.get(position);
        String topicId = String.valueOf(selected.getID());
        String tutorId = String.valueOf(selected.getTutorID());
        String topic = String.valueOf(selected.getName());
        String description = String.valueOf(selected.getDescription());
        String yearsOfExperience = String.valueOf(selected.getYears_of_experience());
        Boolean offer = selected.getIs_offered();


        Intent intent = new Intent (TutorTopicsActivity.this, UpdateTopicsActivity.class);

        intent.putExtra("selectedTopic", (Parcelable) selected);
        intent.putExtra("yearsOfExperience", yearsOfExperience);
        intent.putExtra("topic", topic);
        intent.putExtra("tutorId", tutorId);
        intent.putExtra("topicId", topicId);
        intent.putExtra("offer", offer);
        intent.putExtra("description", description);

        startActivity(intent);

    }

    @Override
    public void onItemClick(int position) {
        //I will add the parameters for the maximum and minimum after the value can be set in the database

        unofferDialog = new Dialog(TutorTopicsActivity.this);
        unofferDialog.setContentView(R.layout.remove_topic_dialogue);
        unofferDialog.setCancelable(true);

        Topic selected = topics.get(position);
        Boolean offer = selected.getIs_offered();

        if(offer == false || offeredTopics < 5){
            offerDialog = new Dialog(TutorTopicsActivity.this);
            offerDialog.setContentView(R.layout.offer_topic_dialogue);
            offerDialog.setCancelable(true);

            Button offerTopic = unofferDialog.findViewById(R.id.offerBtn);

            offerTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click
                    selected.setIs_offered(true);
                    Toast.makeText(TutorTopicsActivity.this, "You Are Now Offering This Topic", Toast.LENGTH_SHORT).show();
                    unofferDialog.dismiss();
                }


            });



        } else if (offer == true) {
            unofferDialog = new Dialog(TutorTopicsActivity.this);
            unofferDialog.setContentView(R.layout.remove_topic_dialogue);
            unofferDialog.setCancelable(true);

            Button unoffer = unofferDialog.findViewById(R.id.removeBtn);

            unoffer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click
                    selected.setIs_offered(false);
                    Toast.makeText(TutorTopicsActivity.this, "You Are No Longer Offering This Topic", Toast.LENGTH_SHORT).show();
                    unofferDialog.dismiss();
                }


            });

        }else{

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }




}
