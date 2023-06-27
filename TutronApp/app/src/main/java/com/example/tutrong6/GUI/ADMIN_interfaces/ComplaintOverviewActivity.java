package com.example.tutrong6.GUI.ADMIN_interfaces;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.tutrong6.R;

import java.text.DateFormat;
import java.util.Calendar;

public class ComplaintOverviewActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_complain_dialogue);

        Button datePickerButton = findViewById(R.id.date_picker);
        Button dismiss = findViewById(R.id.dismiss_btn);
        Button tempSuspend = findViewById(R.id.temporary_btn);
        Button permSuspend = findViewById(R.id.permanent_btn);
        TextView tutorId = findViewById(R.id.tutorID);
        TextView studentId = findViewById(R.id.studentID);
        TextView title = findViewById(R.id.title);
        TextView complaint = findViewById(R.id.complaint_desc);
        TextView complaintId = findViewById(R.id.complaint_id);

        String complaintIdIntent = getIntent().getStringExtra("complaintId");
        String tutorIdIntent = getIntent().getStringExtra("tutorId");
        String studentIdIntent = getIntent().getStringExtra("studentId");
        String titleIntent = getIntent().getStringExtra("title");
        String descriptionIntent = getIntent().getStringExtra("description");

        tutorId.setText(tutorIdIntent);
        studentId.setText(studentIdIntent);
        complaintId.setText(complaintIdIntent);
        complaint.setText(descriptionIntent);
        title.setText(titleIntent);

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        permSuspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tempSuspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "Date Picker");
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView date = findViewById(R.id.date);
        date.setText(currentDateString);
    }

}
