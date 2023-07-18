package com.example.tutrong6.GUI.STUDENT_interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutrong6.BEANS.Topic;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.GUI.TUTOR_interfaces.TopicsAdapter;
import com.example.tutrong6.GUI.TUTOR_interfaces.UpdateTopicsActivity;
import com.example.tutrong6.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Map;

public class OtherTopicsActivity extends AppCompatActivity implements OfferedTopicsRecyclerInterface {

    RecyclerView recyclerView;
    ArrayList<String> topic, offer;

    ArrayList<Topic> topics = new ArrayList<>();
    DBHelper DB;

    DBHelper DataBase = new DBHelper(this);
    TopicsAdapter adapter;

    private int totalTopics;
    private int tutorID;
    private int offeredTopics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_topics);
        //adapter.setLongClickListner(this);

        SessionManagement sessionManagement = new SessionManagement(OtherTopicsActivity.this);
        int userID = sessionManagement.getSession();



        recyclerView = findViewById(R.id.offered_topics_recycler);

        DB = new DBHelper(this);

        tutorID = getIntent().getIntExtra("tutorId",0);
        //int tutorID = Integer.parseInt(tutorIdIntent);

        topics = DB.getOfferedTopics(tutorID);
        Map<Integer, Integer> topicTotals = DataBase.countTopics(userID);
        // dateStr = new SimpleDateFormat(Plainte.getDATE_FORMAT()).format(etat_de_suspension.get(i));
        for (int i : topicTotals.keySet()) {
            totalTopics = i;
            offeredTopics = topicTotals.get(i);
            Log.e("MAP", "key: " + i + " value: " + topicTotals.get(i));

        }


        OfferedTopicsAdapter adapter = new OfferedTopicsAdapter(this,this, topics);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(OtherTopicsActivity.this, StudentHubActivity.class);
                startActivity(intent);

            }

        });



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


        Intent intent = new Intent (OtherTopicsActivity.this, AboutTopicActivity.class);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tutron_menu, menu);
        return true;
    }

}
