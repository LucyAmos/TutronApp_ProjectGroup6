package com.example.tutrong6.GUI.STUDENT_interfaces;

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

import com.example.tutrong6.BEANS.Topic;
import com.example.tutrong6.BEANS.Tutor;
import com.example.tutrong6.BEANS.User;
import com.example.tutrong6.DAO.DBHelper;
import com.example.tutrong6.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder>{

    private final SearchRecyclerInterface searchRecyclerInterface;

    private Context context;
    ArrayList<Topic> searchTopics;


    private DBHelper DataBase;


    public SearchAdapter(Context context, SearchRecyclerInterface searchRecyclerInterface, ArrayList<Topic> searchTopics) {
        this.context = context;
        this.searchRecyclerInterface=searchRecyclerInterface;
        this.searchTopics = searchTopics;

        DataBase = new DBHelper(context);

    }

    @NonNull
    @Override
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.student_topic_search_recycler_row,parent,false);
        return new SearchAdapter.MyViewHolder(v, searchRecyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {

        int topicID = searchTopics.get(position).getID();
        Topic topic = DataBase.getTopicByID(topicID);

        holder.topic.setText(topic.getName());
        Tutor tutor = DataBase.getTutorByID(topic.getTutorID());
        holder.tutorFirstName.setText(tutor.getFirst_name());
        holder.tutorLastName.setText(tutor.getLast_name());

        byte[] profilePictureBytes = tutor.getProfile_picture();
        if(profilePictureBytes != null) {
            Bitmap profilePictureBitmap = BitmapFactory.decodeByteArray(profilePictureBytes, 0, profilePictureBytes.length);
            holder.profilePic.setImageBitmap(profilePictureBitmap);
        }else{
            holder.profilePic.setImageDrawable(context.getResources().getDrawable(R.drawable.default_avatar));
        }
        holder.nativeLanguage.setText(tutor.getNative_language());
        holder.hourlyRate.setText(String.valueOf(tutor.getHourly_rate()));
        holder.averageRating.setRating((float) DataBase.getAverageTutorRating(tutor.getID()));
        holder.totalLessons.setText(String.valueOf(DataBase.getCountGivenLesson(tutor.getID())));




    }

    @Override
    public int getItemCount() {
        //return title.size();
        return searchTopics.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView topic;
        ImageView profilePic;
        TextView tutorFirstName;
        TextView tutorLastName;
        TextView hourlyRate;
        TextView totalLessons;
        TextView nativeLanguage;
        RatingBar averageRating;



        public MyViewHolder(@NonNull View itemView, SearchRecyclerInterface searchRecyclerInterface) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic_name);
            profilePic = itemView.findViewById(R.id.tutor_profile_pic);
            tutorFirstName = itemView.findViewById(R.id.tutor_first_name);
            tutorLastName = itemView.findViewById(R.id.tutor_last_name);
            hourlyRate = itemView.findViewById(R.id.hourly_rate_input);
            totalLessons = itemView.findViewById(R.id.number_lessons_input);
            nativeLanguage = itemView.findViewById(R.id.native_language_input);
            averageRating = itemView.findViewById(R.id.ratingBar);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(searchRecyclerInterface!= null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            searchRecyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }


}
