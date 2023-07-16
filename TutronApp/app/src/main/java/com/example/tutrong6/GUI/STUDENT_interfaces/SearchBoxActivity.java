package com.example.tutrong6.GUI.STUDENT_interfaces;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutrong6.BEANS.Topic;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.GUI.TUTOR_interfaces.AddTopicActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.TopicsAdapter;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorTopicsActivity;
import com.example.tutrong6.GUI.TUTOR_interfaces.UpdateTopicsActivity;
import com.example.tutrong6.R;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.Map;


public class SearchBoxActivity extends AppCompatActivity implements SearchRecyclerInterface, AdapterView.OnItemSelectedListener {


    RecyclerView recyclerView;

    ArrayList<Integer> searchTopicsID = new ArrayList<>();
    ArrayList<Topic> searchTopics = new ArrayList<>();
    DBHelper DB;

    DBHelper DataBase = new DBHelper(this);
    SearchAdapter adapter;

    Dialog searchByDialog;

    private String[] findBy = new String[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_search);

        DB = new DBHelper(this);


        SessionManagement sessionManagement = new SessionManagement(SearchBoxActivity.this);
        int userID = sessionManagement.getSession();

        Button fab = findViewById(R.id.home_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(SearchBoxActivity.this, StudentHubActivity.class);
                startActivity(intent);

            }

        });



        recyclerView = findViewById(R.id.search_recycler);

        EditText searchBar = findViewById(R.id.search_bar_student_input);
        Button searchButton = findViewById(R.id.search_button);


        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchInput = searchBar.getText().toString().trim();
                searchByDialog = new Dialog(SearchBoxActivity.this);
                searchByDialog.setContentView(R.layout.search_by_dialogue);
                searchByDialog.show();
                searchByDialog.setCancelable(true);

                Window windowOffer = searchByDialog.getWindow();
                if (windowOffer != null) {
                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(windowOffer.getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
                    windowOffer.setAttributes(layoutParams);
                }

                Button searchByTopic = findViewById(R.id.search_by_TopicBtn);
                Button searchByTutor = findViewById(R.id.search_by_tutorBtn);
                Button searchByNativeLanguage = findViewById(R.id.search_by_languageBtn);

                searchByTopic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchByDialog.dismiss();
                        findBy[0] = null; // tutor name
                        findBy[1] = null; // language spoken
                        findBy[2] = searchInput; // Topic name
                        searchTopicsID = DataBase.findTopic(findBy, -1);
                    }

                });

                searchByTutor.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchByDialog.dismiss();
                        findBy[0] = searchInput; // tutor name
                        findBy[1] = null; // language spoken
                        findBy[2] = null; // Topic name
                        searchTopicsID = DataBase.findTopic(findBy, -1);
                    }

                });

                searchByNativeLanguage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        searchByDialog.dismiss();
                        findBy[0] = null; // tutor name
                        findBy[1] = searchInput; // language spoken
                        findBy[2] = null; // Topic name
                        searchTopicsID = DataBase.findTopic(findBy, -1);
                    }

                });

            }


        });


        for (int topicId : searchTopicsID) {
            Topic topic = DB.getTopicByID(topicId);
            if (topic != null) {
                searchTopics.add(topic);
            }
        }


        adapter = new SearchAdapter(this,this, searchTopics);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Spinner sortingMenu = findViewById(R.id.dropdown_menu);

        ArrayAdapter<CharSequence> sortAdapter = ArrayAdapter.createFromResource(this, R.array.sorting, android.R.layout.simple_spinner_item);
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortingMenu.setAdapter(sortAdapter);
        sortingMenu.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemClick(int position) {

        Topic selected = searchTopics.get(position);
        String topicId = String.valueOf(selected.getID());
        String tutorId = String.valueOf(selected.getTutorID());


        Intent intent = new Intent (SearchBoxActivity.this, AboutTopicActivity.class);
        intent.putExtra("tutorId", tutorId);
        intent.putExtra("topicId", topicId);
        startActivity(intent);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tutron_menu, menu);
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String sortType = adapterView.getItemAtPosition(i).toString();
        if(sortType.equals("Sort By: User Ratings")){
            searchTopicsID = DataBase.findTopic(findBy, 0);

        }else if(sortType.equals("Sort By: Hourly Rate")){
            searchTopicsID = DataBase.findTopic(findBy, 1);

        }else if(sortType.equals("Sort By: Number of Lessons")){
            searchTopicsID = DataBase.findTopic(findBy, 3);

        }else{
            searchTopicsID = DataBase.findTopic(findBy, -1);
        }

        for (int topicId : searchTopicsID) {
            Topic topic = DB.getTopicByID(topicId);
            if (topic != null) {
                searchTopics.add(topic);
            }
        }

        adapter = new SearchAdapter(this,this, searchTopics);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
