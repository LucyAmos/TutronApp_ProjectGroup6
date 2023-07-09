package com.example.tutrong6.GUI.TUTOR_interfaces;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tutrong6.BEANS.Topic;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.R;



public class UpdateTopicsActivity extends AppCompatActivity {


    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_topic);

        DBHelper DataBase = new DBHelper(this);

        Button updateBtn = findViewById(R.id.update_topic_button);
        Button removeBtn = findViewById(R.id.remove_topic_button);
        EditText topicText = findViewById(R.id.topic_name_input);
        EditText descriptionText = findViewById(R.id.description_experience_input);
        EditText yearsOfExperienceText = findViewById(R.id.years_of_experience_input);
        DB = new DBHelper(this);



        String topicNameIntent = getIntent().getStringExtra("topic");
        String descriptionIntent = getIntent().getStringExtra("description");
        String yearsOfExperienceIntent = getIntent().getStringExtra("yearsOfExperience");
        String tutorIdIntent = getIntent().getStringExtra("tutorId");
        String topicIdIntent = getIntent().getStringExtra("topicId");
        String offerIntent = getIntent().getStringExtra("offer");


        topicText.setText(topicNameIntent);
        descriptionText.setText(descriptionIntent);
        yearsOfExperienceText.setText(yearsOfExperienceIntent);

        int topicId = Integer.parseInt(topicIdIntent);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String topic_name = topicText.getText().toString().trim();
                String description = descriptionText.getText().toString().trim();
                int yearsOfExperience = Integer.parseInt(yearsOfExperienceText.getText().toString().trim());

                if(topic_name.isEmpty() || description.isEmpty() || String.valueOf(yearsOfExperience).isEmpty()
                )
                {
                    Toast.makeText(UpdateTopicsActivity.this, "Please, fill in ALL the fields", Toast.LENGTH_SHORT).show();
                }else{
                    Topic updateTopic = new Topic(topic_name,yearsOfExperience, description);
                    Boolean addedUpdate = DataBase.updateTopic(topicId, updateTopic);

                    if(addedUpdate) {
                        Toast.makeText(UpdateTopicsActivity.this, "Topic Updated Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(UpdateTopicsActivity.this, TutorTopicsActivity.class));
                    }else{
                        Toast.makeText(UpdateTopicsActivity.this, "Topic Update Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }}
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean removed = DataBase.deleteTopic(topicId);
                if (removed) {
                    Toast.makeText(UpdateTopicsActivity.this, "Topic Removed Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateTopicsActivity.this, TutorTopicsActivity.class));
                } else {
                    Toast.makeText(UpdateTopicsActivity.this, "Topic Removal Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    }


