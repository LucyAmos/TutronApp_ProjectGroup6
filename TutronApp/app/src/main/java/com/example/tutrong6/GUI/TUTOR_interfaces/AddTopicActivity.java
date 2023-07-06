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



public class AddTopicActivity extends AppCompatActivity {


    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_topic);

        DBHelper DataBase = new DBHelper(this);

        Button updateBtn = findViewById(R.id.add_topic_button);
        EditText topicText = findViewById(R.id.topic_name_input);
        EditText descriptionText = findViewById(R.id.description_experience_input);
        EditText yearsOfExperienceText = findViewById(R.id.years_of_experience_input);
        DB = new DBHelper(this);

        String tutorIdIntent = getIntent().getStringExtra("tutorId");

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String topic = topicText.getText().toString().trim();
                String description = descriptionText.getText().toString().trim();
                int yearsOfExperience = Integer.parseInt(yearsOfExperienceText.getText().toString().trim());
                //int topicId = Integer.parseInt(topicIdIntent);
                int tutorId = Integer.parseInt(tutorIdIntent);
                //boolean offer = Boolean.parseBoolean(offerIntent);*/




                if(topic.isEmpty() || description.isEmpty() || String.valueOf(yearsOfExperience).isEmpty()
                )
                {
                    Toast.makeText(AddTopicActivity.this, "Please, fill in ALL the fields", Toast.LENGTH_SHORT).show();
                }else{
                    Topic addTopic = new Topic(tutorId, topic, yearsOfExperience, description, false);
                    Boolean added = DataBase.addTopic(addTopic);
                    if (added) {
                        Toast.makeText(AddTopicActivity.this, "Topic Added Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddTopicActivity.this, TutorTopicsActivity.class));
                    } else {
                        Toast.makeText(AddTopicActivity.this, "Topic Addition Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
            }}
        });


    }

}
