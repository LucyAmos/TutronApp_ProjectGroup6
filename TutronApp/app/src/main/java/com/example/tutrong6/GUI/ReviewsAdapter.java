package com.example.tutrong6.GUI;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutrong6.BEANS.Lesson;
import com.example.tutrong6.BEANS.ReviewSystem;
import com.example.tutrong6.BEANS.Student;
import com.example.tutrong6.BEANS.Topic;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.DAO.SessionManagement;
import com.example.tutrong6.R;

import java.util.ArrayList;
import java.util.Map;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.MyViewHolder>{

    private final ReviewRecyclerInterface reviewRecyclerInterface;

    private Context context;
    Map<int[], ReviewSystem> reviewSystemMap;


    private DBHelper DataBase;




    public ReviewsAdapter(Context context, ReviewRecyclerInterface reviewRecyclerInterface, Map<int[], ReviewSystem> reviewSystemMap) {
        this.context = context;
        this.reviewRecyclerInterface=reviewRecyclerInterface;
        this.reviewSystemMap = reviewSystemMap;

        DataBase = new DBHelper(context);

    }


    @NonNull
    @Override
    public ReviewsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.review_recycler_row,parent,false);
        return new ReviewsAdapter.MyViewHolder(v, reviewRecyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.MyViewHolder holder, int position) {

        int[] keys = (int[]) reviewSystemMap.keySet().toArray()[position];
        ReviewSystem reviewSystem = reviewSystemMap.get(keys);

        int topicId = keys[1];
        int studentId = keys[0];


        Topic topic = DataBase.getTopicByID(topicId);
        if (topic != null) {
            holder.topic.setText(topic.getName());
        }

        boolean anonStatus = reviewSystem.getIs_rating_anonymous();

        holder.date.setText(String.valueOf(reviewSystem.getRating_date()));
        holder.givenRating.setRating((float) reviewSystem.getRating());
        holder.reviewMessage.setText(reviewSystem.getReview());

        if(anonStatus) {
            holder.studentFirstName.setText("Anonymous");
            holder.studentLastName.setText("Student");
        }else{
            holder.studentFirstName.setText(DataBase.getStudentByID(studentId).getFirst_name());
            holder.studentLastName.setText(DataBase.getStudentByID(studentId).getLast_name());
        }


    }

    @Override
    public int getItemCount() {
        //return title.size();
        return reviewSystemMap.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView topic;
        TextView studentFirstName;
        TextView studentLastName;
        TextView date;
        TextView reviewMessage;
        RatingBar givenRating;



        public MyViewHolder(@NonNull View itemView, ReviewRecyclerInterface reviewRecyclerInterface) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic);
            studentFirstName = itemView.findViewById(R.id.student_first_name);
            studentLastName = itemView.findViewById(R.id.student_last_name);
            date = itemView.findViewById(R.id.student_date_posted);
            reviewMessage = itemView.findViewById(R.id.review);
            givenRating = itemView.findViewById(R.id.ratingBar);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(reviewRecyclerInterface!= null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            reviewRecyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }


}
