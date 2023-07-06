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



        String topicIntent = getIntent().getStringExtra("topic");
        String descriptionIntent = getIntent().getStringExtra("description");
        String yearsOfExperienceIntent = getIntent().getStringExtra("yearsOfExperience");
        String tutorIdIntent = getIntent().getStringExtra("tutorId");
        String topicIdIntent = getIntent().getStringExtra("topicId");
        String offerIntent = getIntent().getStringExtra("offer");
        Topic selectedIntent = getIntent().getParcelableExtra("selectedTopic");


        topicText.setText(topicIntent);
        descriptionText.setText(descriptionIntent);
        yearsOfExperienceText.setText(yearsOfExperienceIntent);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String topic = topicText.getText().toString().trim();
                String description = descriptionText.getText().toString().trim();
                int yearsOfExperience = Integer.parseInt(yearsOfExperienceText.getText().toString().trim());
                int topicId = Integer.parseInt(topicIdIntent);
                int tutorId = Integer.parseInt(tutorIdIntent);
                boolean offer = Boolean.parseBoolean(offerIntent);
                Topic selected = selectedIntent;




                if(topic.isEmpty() || description.isEmpty() || String.valueOf(yearsOfExperience).isEmpty()
                )
                {
                    Toast.makeText(UpdateTopicsActivity.this, "Please, fill in ALL the fields", Toast.LENGTH_SHORT).show();
                }else{
                    selected.setName(topic);
                    selected.setDescription(description);
                    selected.setYears_of_experience(yearsOfExperience);
                    Toast.makeText(UpdateTopicsActivity.this, "Topic Updated Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateTopicsActivity.this, TutorTopicsActivity.class));
                }}
        });

        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Topic selected = selectedIntent;

                Topic removeTopic = selected;
                Boolean removed = DataBase.deleteTopic(removeTopic.getID());
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


