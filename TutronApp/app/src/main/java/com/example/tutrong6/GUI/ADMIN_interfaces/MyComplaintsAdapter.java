package com.example.tutrong6.GUI.ADMIN_interfaces;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutrong6.R;

import java.util.ArrayList;

public class MyComplaintsAdapter extends RecyclerView.Adapter<MyComplaintsAdapter.MyViewHolder> {
    private final ComplainsRecyclerViewInterface complainsRecyclerViewInterface;
    private Context context;
    private ArrayList title, sneak_peek, complaint_id;

    public MyComplaintsAdapter(Context context, ArrayList title, ArrayList sneak_peek, ArrayList complaint_id, ComplainsRecyclerViewInterface complainsRecyclerViewInterface) {
        this.context = context;
        this.title = title;
        this.sneak_peek = sneak_peek;
        this.complaint_id=complaint_id;
        this.complainsRecyclerViewInterface=complainsRecyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.complaints_recycler_row,parent,false);
        return new MyViewHolder(v, complainsRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(String.valueOf(title.get(position)));
        holder.sneak_peek.setText(String.valueOf(sneak_peek.get(position)));
        holder.complaint_id.setText(String.valueOf(complaint_id.get(position)));


    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, sneak_peek, complaint_id;
        public MyViewHolder(@NonNull View itemView, ComplainsRecyclerViewInterface complainsRecyclerViewInterface) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            sneak_peek = itemView.findViewById(R.id.sneak_peek);
            complaint_id = itemView.findViewById(R.id.complaint_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   if(complainsRecyclerViewInterface!= null) {
                       int pos = getAdapterPosition();

                       if(pos != RecyclerView.NO_POSITION){
                           complainsRecyclerViewInterface.onItemClick(pos);
                       }
                   }
                }
            });
        }
    }
}
