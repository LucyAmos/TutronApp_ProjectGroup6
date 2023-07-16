package com.example.tutrong6.GUI.STUDENT_interfaces;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutrong6.BEANS.Topic;
import com.example.tutrong6.R;

import java.util.ArrayList;

public class OfferedTopicsAdapter extends RecyclerView.Adapter<OfferedTopicsAdapter.MyViewHolder> {
    private final OfferedTopicsRecyclerInterface offeredTopicsRecyclerInterface;

    private Context context;
    ArrayList<Topic> topics;


    public OfferedTopicsAdapter(Context context, OfferedTopicsRecyclerInterface offeredTopicsRecyclerInterface, ArrayList<Topic> topics) {
        this.context = context;
        this.offeredTopicsRecyclerInterface=offeredTopicsRecyclerInterface;
        this.topics = topics;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.topics_tutorpov_recycler_row,parent,false);
        return new MyViewHolder(v, offeredTopicsRecyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.topic.setText(topics.get(position).getName());
        boolean offer = topics.get(position).getIs_offered();
        holder.container.setBackgroundColor(Color.parseColor("#DBB400"));
        holder.offerText.setText("Offered");


    }

    @Override
    public int getItemCount() {
        //return title.size();
        return topics.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView topic;

        TextView offerText;
        ConstraintLayout container;

        public MyViewHolder(@NonNull View itemView, OfferedTopicsRecyclerInterface offeredTopicsRecyclerInterface) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic_name);
            container = itemView.findViewById(R.id.container);
            offerText = itemView.findViewById(R.id.offer_type);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(offeredTopicsRecyclerInterface!= null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            offeredTopicsRecyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}

