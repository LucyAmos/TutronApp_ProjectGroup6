package com.example.tutrong6.GUI.TUTOR_interfaces;

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
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutrong6.BEANS.Topic;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.GUI.STUDENT_interfaces.AboutTopicActivity;
import com.example.tutrong6.GUI.STUDENT_interfaces.StudentHubActivity;
import com.example.tutrong6.R;

import java.util.ArrayList;
import java.util.Map;


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
        //adapter.setLongClickListner(this);

        SessionManagement sessionManagement = new SessionManagement(TutorTopicsActivity.this);
        int userID = sessionManagement.getSession();



        recyclerView = findViewById(R.id.topics_recycler);

        DB = new DBHelper(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(TutorTopicsActivity.this, TutorHubActivity.class);
                startActivity(intent);

            }

        });

        topics = DB.getAllTopics(userID);
        Map<Integer, Integer> topicTotals = DataBase.countTopics(userID);
        // dateStr = new SimpleDateFormat(Plainte.getDATE_FORMAT()).format(etat_de_suspension.get(i));
        for (int i : topicTotals.keySet()) {
            totalTopics = i;
            offeredTopics = topicTotals.get(i);
            Log.e("MAP", "key: " + i + " value: " + topicTotals.get(i));

        }


        TopicsAdapter adapter = new TopicsAdapter(this,this, topics);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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
        int creation_max = Topic.getCREATION_MAX();
        if(totalTopics >= creation_max){
            addButton.setVisibility(View.INVISIBLE);
        }else if(totalTopics < creation_max){
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
    public void onItemClick(int position) {

        Topic selected = topics.get(position);
        String topicId = String.valueOf(selected.getID());
        String tutorId = String.valueOf(selected.getTutorID());
        String topic = String.valueOf(selected.getName());
        String description = String.valueOf(selected.getDescription());
        String yearsOfExperience = String.valueOf(selected.getYears_of_experience());
        Boolean offer = selected.getIs_offered();


        Intent intent = new Intent (TutorTopicsActivity.this, UpdateTopicsActivity.class);

        //intent.putExtra("selectedTopic", (Parcelable) selected);
        intent.putExtra("yearsOfExperience", yearsOfExperience);
        intent.putExtra("topic", topic);
        intent.putExtra("tutorId", tutorId);
        intent.putExtra("topicId", topicId);
        intent.putExtra("offer", offer);
        intent.putExtra("description", description);

        startActivity(intent);

    }

    @Override
    public void onItemLongClick(int position) {
        //I will add the parameters for the maximum and minimum after the value can be set in the database

        unofferDialog = new Dialog(TutorTopicsActivity.this);
        unofferDialog.setContentView(R.layout.remove_topic_dialogue);
        unofferDialog.setCancelable(true);

        Topic selected = topics.get(position);
        Boolean offer = selected.getIs_offered();

        if(!offer && offeredTopics < Topic.getOFFERING_MAX()){
            offerDialog = new Dialog(TutorTopicsActivity.this);
            offerDialog.setContentView(R.layout.offer_topic_dialogue);
            offerDialog.show();
            offerDialog.setCancelable(true);

            Window windowOffer = offerDialog.getWindow();
            if (windowOffer != null) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(windowOffer.getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                windowOffer.setAttributes(layoutParams);
            }

            Button offerTopic = offerDialog.findViewById(R.id.offerBtn);

            offerTopic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click
                    selected.setIs_offered(true);
                    DataBase.updateTopicOffer(selected.getID(),true);
                    offerDialog.dismiss();
                    recreate();
                    Toast.makeText(TutorTopicsActivity.this, "You Are Now Offering This Topic", Toast.LENGTH_SHORT).show();
                }


            });



        } else if (offer == true) {
            unofferDialog = new Dialog(TutorTopicsActivity.this);
            unofferDialog.setContentView(R.layout.remove_topic_dialogue);
            unofferDialog.show();
            unofferDialog.setCancelable(true);

            Window windowUnoffer = unofferDialog.getWindow();
            if (windowUnoffer != null) {
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                layoutParams.copyFrom(windowUnoffer.getAttributes());
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                windowUnoffer.setAttributes(layoutParams);
            }

            Button unoffer = unofferDialog.findViewById(R.id.removeBtn);

            unoffer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle button click
                    selected.setIs_offered(false);
                    DataBase.updateTopicOffer(selected.getID(),false);
                    unofferDialog.dismiss();
                    recreate();
                    Toast.makeText(TutorTopicsActivity.this, "You Are No Longer Offering This Topic", Toast.LENGTH_SHORT).show();
                }


            });

        }else{
            Toast.makeText(TutorTopicsActivity.this, "You are already offering 5 topics", Toast.LENGTH_SHORT).show();
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tutron_menu, menu);
        return true;
    }




}
