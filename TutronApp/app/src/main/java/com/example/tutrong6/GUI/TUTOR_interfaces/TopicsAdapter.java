package com.example.tutrong6.GUI.TUTOR_interfaces;

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

public class TopicsAdapter extends RecyclerView.Adapter<TopicsAdapter.MyViewHolder> {
    private final TopicsRecyclerInterface topicsRecyclerInterface;
    private TopicsRecyclerInterface longClickListener;
    private Context context;
    ArrayList<Topic> topics;


    public TopicsAdapter(Context context, TopicsRecyclerInterface topicsRecyclerInterface, ArrayList<Topic> topics) {
        this.context = context;
        this.topicsRecyclerInterface=topicsRecyclerInterface;
        this.topics = topics;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.topics_tutorpov_recycler_row,parent,false);
        return new MyViewHolder(v, topicsRecyclerInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.topic.setText(topics.get(position).getName());
        boolean offer = topics.get(position).getIs_offered();


        if(offer){
            holder.container.setBackgroundColor(Color.parseColor("#DBB400"));
            holder.offerText.setText("Offered");
        }else{
            holder.container.setBackgroundColor(Color.parseColor("#A6A6A6"));
            holder.offerText.setText("Not Offered");
        }

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

        public MyViewHolder(@NonNull View itemView, TopicsRecyclerInterface topicsRecyclerInterface) {
            super(itemView);
            topic = itemView.findViewById(R.id.topic_name);
            container = itemView.findViewById(R.id.container);
            offerText = itemView.findViewById(R.id.offer_type);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (topicsRecyclerInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            topicsRecyclerInterface.onItemLongClick(pos);
                        }
                    }
                return true;

            }


            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(topicsRecyclerInterface!= null) {
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            topicsRecyclerInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
