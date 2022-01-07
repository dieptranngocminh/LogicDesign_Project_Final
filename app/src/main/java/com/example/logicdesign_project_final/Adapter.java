package com.example.logicdesign_project_final;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    Context context;

    ArrayList<Route> list;

    public Adapter(Context context, ArrayList<Route> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Route route = list.get(position);
        holder.building.setText(route.getBuilding());
        holder.room.setText(route.getRoom());
        holder.time.setText(route.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView building, room, time;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            building = itemView.findViewById(R.id.tvbuilding);
            room = itemView.findViewById(R.id.tvroom);
            time = itemView.findViewById(R.id.tvtime);

        }
    }
}
