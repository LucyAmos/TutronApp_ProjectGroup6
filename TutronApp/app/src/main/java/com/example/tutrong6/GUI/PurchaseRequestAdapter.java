package com.example.tutrong6.GUI;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutrong6.BEANS.Lesson;
import com.example.tutrong6.BEANS.Student;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.GUI.TUTOR_interfaces.TutorHubActivity;
import com.example.tutrong6.R;

import java.util.ArrayList;

public class PurchaseRequestAdapter extends RecyclerView.Adapter<PurchaseRequestAdapter.MyViewHolder>{

    private final PurchaseRequestRecyclerInterface purchaseRequestRecyclerInterface;
    private final User sessionUser;

    private Context context;
    ArrayList<Lesson> lessons;


    private DBHelper DataBase;


    public PurchaseRequestAdapter(Context context, PurchaseRequestRecyclerInterface purchaseRequestRecyclerInterface, ArrayList<Lesson> lessons, User sessionUser) {
        this.context = context;
        this.purchaseRequestRecyclerInterface=purchaseRequestRecyclerInterface;
        this.lessons = lessons;
        this.sessionUser = sessionUser;

        DataBase = new DBHelper(context);

    }


    @NonNull
    @Override
    public PurchaseRequestAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.purchase_request_recycler_row,parent,false);
        return new PurchaseRequestAdapter.MyViewHolder(v, purchaseRequestRecyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseRequestAdapter.MyViewHolder holder, int position) {

        int tutorId = lessons.get(position).getTutorID();
        int topicId = lessons.get(position).getTopicID();
        int studentId = lessons.get(position).getStudentID();

        holder.date.setText(String.valueOf(lessons.get(position).getDate_appointment()));
        holder.timeSlot.setText(String.valueOf(lessons.get(position).getSlot()));
        holder.price.setText(String.valueOf(lessons.get(position).getPrice()));

        Lesson.Status status = lessons.get(position).getStatus();

        if(status == Lesson.Status.APPROVED){
            holder.container.setBackgroundColor(Color.parseColor("#A2FF7B"));
            holder.status.setText("Approved");
        }else if(status == Lesson.Status.REJECTED){
            holder.container.setBackgroundColor(Color.parseColor("#FF6F6F"));
            holder.status.setText("Rejected");
        }else {
            holder.container.setBackgroundColor(Color.parseColor("#FFE49E"));
            holder.status.setText("Pending");
        }

        holder.topic.setText(DataBase.getTopicByID(topicId).getName());

        if(sessionUser.getRoleID() == Tutor.getStaticRoleID()){
            holder.firstName.setText(DataBase.getUserbyID(studentId).getFirst_name());
            holder.lastName.setText(DataBase.getUserbyID(studentId).getLast_name());
        } else if(sessionUser.getRoleID() == Student.getStaticRoleID()){
            holder.firstName.setText(DataBase.getUserbyID(tutorId).getFirst_name());
            holder.lastName.setText(DataBase.getUserbyID(tutorId).getLast_name());
        }

    }

    @Override
    public int getItemCount() {
        //return title.size();
        return lessons.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView topic;
        TextView firstName;
        TextView lastName;
        TextView date;
        TextView timeSlot;
        TextView status;
        TextView price;
        ConstraintLayout container;



        public MyViewHolder(@NonNull View itemView, PurchaseRequestRecyclerInterface purchaseRequestRecyclerInterface) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic);
            firstName = itemView.findViewById(R.id.first_name);
            lastName = itemView.findViewById(R.id.last_name);
            date = itemView.findViewById(R.id.date);
            timeSlot = itemView.findViewById(R.id.time1);
            status = itemView.findViewById(R.id.status);
            price = itemView.findViewById(R.id.price);
            container = itemView.findViewById(R.id.container);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(purchaseRequestRecyclerInterface!= null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            purchaseRequestRecyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }


}

