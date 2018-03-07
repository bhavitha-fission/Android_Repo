package com.fission.db;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fission.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Image> imageArrayList;
    private View view;
    private ViewHolder viewHolder;

    public RecyclerViewAdapter(Context context,ArrayList<Image> imageArrayList) {
        this.context = context;
        this.imageArrayList = imageArrayList;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.cards_layout,parent,false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {

        holder.textView.setText(imageArrayList.get(position).getImageDescription());

        Picasso.with(context)
                .load(imageArrayList.get(position).getImageUrl())
                .resize(120, 120)
                .centerCrop()
                .into(holder.imageView);
       // Picasso.with(context).load(imageArrayList.get(position).getImageUrl()).resize(30, 30).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageArrayList.size() ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
