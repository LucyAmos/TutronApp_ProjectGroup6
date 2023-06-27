package com.example.tutrong6.GUI.ADMIN_interfaces;

import android.os.Bundle;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutrong6.BEANS.Complaint;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.R;

import java.util.ArrayList;



public class AdminInboxActivity extends AppCompatActivity implements ComplainsRecyclerViewInterface{

    RecyclerView recyclerView;
    ArrayList<String> title, sneakPeek, complaintId;

    ArrayList<Complaint> complaints = new ArrayList<>();
    DBHelper DB;
    MyComplaintsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complaints_inbox);
        recyclerView = findViewById(R.id.complaints_recycler);

        DB = new DBHelper(this);

        complaints = DB.activeComplaintsList();

        MyComplaintsAdapter adapter = new MyComplaintsAdapter(this,this, complaints);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

        Complaint selected = complaints.get(position);
        String complaintId = String.valueOf(selected.getID());
        String title = String.valueOf(selected.getTitle());
        String tutorId = String.valueOf(selected.getTutorID());
        String studentId = String.valueOf(selected.getStudentID());
        String description = String.valueOf(selected.getDescription());

        Intent intent = new Intent (AdminInboxActivity.this, ComplaintOverviewActivity.class);

        intent.putExtra("complaintId", complaintId);
        intent.putExtra("title", title);
        intent.putExtra("tutorId", tutorId);
        intent.putExtra("studentId", studentId);
        intent.putExtra("description", description);

        startActivity(intent);

    }

    //private void displaydata() {
        //Cursor cursor = DB.activeComplaintsList();
    //}
}
