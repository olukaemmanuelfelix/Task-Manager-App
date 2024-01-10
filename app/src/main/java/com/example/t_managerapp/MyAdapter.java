package com.example.t_managerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList Task_title_id, Date_id, Time_id, Priority_id, Description_id;

    public MyAdapter(Context context, ArrayList task_title_id, ArrayList date_id, ArrayList time_id, ArrayList priority_id, ArrayList description_id) {
        this.context = context;
        this.Task_title_id = task_title_id;
        this.Date_id = date_id;
        this.Time_id = time_id;
        this.Priority_id = priority_id;
        this.Description_id = description_id;
    }

    @NonNull
    @Override
    public  MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.userentry, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.task_title_id.setText(String.valueOf(Task_title_id.get(position)));
        holder.description_id.setText(String.valueOf(Description_id.get(position)));
        holder.date_id.setText(String.valueOf(Date_id.get(position)));
        holder.time_id.setText(String.valueOf(Time_id.get(position)));
        holder.priority_id.setText(String.valueOf(Priority_id.get(position)));

    }

    @Override
    public int getItemCount() {
        return Task_title_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView task_title_id;
        TextView date_id;
        TextView time_id;
        TextView priority_id;
        TextView description_id;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            task_title_id = itemView.findViewById(R.id.taskTile);
            date_id = itemView.findViewById(R.id.datetxt);
            time_id = itemView.findViewById(R.id.timetxt);
            priority_id = itemView.findViewById(R.id.prioritytxt);
            description_id  = itemView.findViewById(R.id.descriptiontxt);
        }
    }
}
