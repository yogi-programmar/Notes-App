package com.example.notes;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Size;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHokder> {
    Context context;
    Activity activity;
    List<Modal> notelist;

    public Adapter(Context context, Activity activity, List<Modal> notelist) {
        this.context = context;
        this.activity = activity;
        this.notelist = notelist;
    }



    @NonNull
    @Override
    public MyViewHokder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);

        return new MyViewHokder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHokder holder, int position) {
        holder.title.setText(notelist.get(position).getTitle());
        holder.description.setText(notelist.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }

    public class MyViewHokder extends  RecyclerView.ViewHolder {
        TextView title, description;
        RelativeLayout relativeLayout;
        public MyViewHokder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            description=itemView.findViewById(R.id.desccription);
            relativeLayout=itemView.findViewById(R.id.layout_main);
        }
    }
}
