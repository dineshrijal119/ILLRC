package com.technocurl.www.parsejson.kanun;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technocurl.www.parsejson.R;


import java.util.Collections;
import java.util.List;

import okhttp3.internal.Util;

/**
 * Created by dinesh on 8/30/16.
 */

public class Thyadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Tathya> data= Collections.emptyList();

    int currentPos=0;

    // create constructor to innitilize context and data sent from SignupActivity
    public Thyadapter(Context context, List<Tathya> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.thya_list_row, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        Tathya  current=data.get(position);
        myHolder.sn.setText(current.getSn());
        myHolder.thya.setText(current.getName());


    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        TextView sn;
        TextView thya;


        public MyHolder(View itemView) {
            super(itemView);
            sn= (TextView) itemView.findViewById(R.id.sn);
            thya = (TextView) itemView.findViewById(R.id.thya);


            itemView.setClickable(true);
        }

    }

}