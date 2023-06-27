package com.example.tutrong6.GUI.ADMIN_interfaces;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.tutrong6.BEANS.Complaint;
import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.R;

import java.text.DateFormat;
import java.util.ArrayList;
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
        DBHelper DB = new DBHelper(this);



        String complaintIdIntent = getIntent().getStringExtra("complaintId");
        String tutorIdIntent = getIntent().getStringExtra("tutorId");
        String studentIdIntent = getIntent().getStringExtra("studentId");
        String titleIntent = getIntent().getStringExtra("title");
        String descriptionIntent = getIntent().getStringExtra("description");

        User accused_tutor = DB.getUserbyID(Integer.parseInt(tutorIdIntent) );
        User accuser_student = DB.getUserbyID(Integer.parseInt(studentIdIntent) );

        tutorId.setText(""+ accused_tutor.getFirst_name() + " " + accused_tutor.getLast_name());
        studentId.setText(""+ accuser_student.getFirst_name() + " " + accuser_student.getLast_name());
        complaintId.setText(complaintIdIntent);
        complaint.setText(descriptionIntent);
        title.setText(titleIntent);

        int complaintID = Integer.parseInt(complaintIdIntent);
        int accused_tutorID = Integer.parseInt(tutorIdIntent);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ArrayList<Complaint> userArrayList = DB.activeComplaintsList();


                int decisionID = Complaint.getComplaintIdByDecisions(Complaint.Decisions.DISMISSED);
                boolean validation_complaint_update = DB.make_complaint_decision(complaintID, decisionID , null);

                if(validation_complaint_update == true){

                    Toast.makeText(ComplaintOverviewActivity.this, "the complaint has been processed", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(ComplaintOverviewActivity.this,ComplaintOverviewActivity.class);

                    startActivity(i);
                }else{
                    Toast.makeText(ComplaintOverviewActivity.this, "The complaint  hasn't been processed", Toast.LENGTH_SHORT).show();
                }

            }
        });

        permSuspend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int decisionID = Complaint.getComplaintIdByDecisions(Complaint.Decisions.PermanentSuspended);
                boolean validation_complaint_update = DB.make_complaint_decision(complaintID, decisionID, null);

                boolean validation_tutor_update =  DB.updateTutorSuspensionState(accused_tutorID, true);

                if(validation_complaint_update == true && validation_tutor_update == true){

                    Toast.makeText(ComplaintOverviewActivity.this, "The complaint has been processed", Toast.LENGTH_SHORT).show();



                    Intent i = new Intent(ComplaintOverviewActivity.this,AdminInboxActivity.class);

                    startActivity(i);
                }else{
                    Toast.makeText(ComplaintOverviewActivity.this, "The complaint  hasn't been processed", Toast.LENGTH_SHORT).show();
                }


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
