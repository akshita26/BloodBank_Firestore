package com.example.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.DatalistViewHolder> {


    private Context mCtx;
    private List<dataholder> AdminList;

    public DonorAdapter(Context mCtx, List<dataholder> AdminList) {
        this.mCtx = mCtx;
        this.AdminList = AdminList;
    }


    @Override
    public DatalistViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        return new DatalistViewHolder(
                LayoutInflater.from(mCtx).inflate(R.layout.user, parent, false)
        );

    }

    @Override
    public void onBindViewHolder( DatalistViewHolder holder, int position) {

        dataholder adminDetail = AdminList.get(position);

        holder.name.setText(adminDetail.getName());
        holder.address.setText(adminDetail.getAddress());
        holder.mobilee.setText(adminDetail.getMobile());
        holder.bldgrp.setText(adminDetail.getBloodgroup());
        Log.d("check","imageurl  "+ adminDetail.getPimage() );
        Glide.with(mCtx).load(adminDetail.getPimage()).override(400,400).centerCrop().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return AdminList.size();
    }

    public class DatalistViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView address;
        TextView mobilee;
        TextView bldgrp;
        ImageView imageView;

        public DatalistViewHolder( View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.textView8);
            address = itemView.findViewById(R.id.textView14);
            mobilee = itemView.findViewById(R.id.textView12);
            bldgrp = itemView.findViewById(R.id.textView10);
            imageView = itemView.findViewById(R.id.imageView2);
            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {

            dataholder product = AdminList.get(getAdapterPosition());
            Intent intent = new Intent(mCtx, Showdata.class);
            intent.putExtra("listdata",  product);
            mCtx.startActivity(intent);

        }
    }
}

