package com.technocurl.www.parsejson.gazat;

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
import com.technocurl.www.parsejson.model.NajirNepalimodel;

import java.util.Collections;
import java.util.List;

import okhttp3.internal.Util;

/**
 * Created by dinesh on 8/30/16.
 */

public class Gazetadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Gazetmodel> data= Collections.emptyList();

    int currentPos=0;

    // create constructor to innitilize context and data sent from SignupActivity
    public Gazetadapter(Context context, List<Gazetmodel> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.gazet_result_list_row, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder= (MyHolder) holder;
        Gazetmodel  current=data.get(position);
            myHolder.barsa.setText(current.getBarsha());
            myHolder.miti.setText(current.getMiti());
            myHolder.bisaya.setText(current.getBishaya());

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        TextView barsa;
        TextView miti;
        TextView bisaya;
        public MyHolder(View itemView) {
            super(itemView);
            barsa= (TextView) itemView.findViewById(R.id.barsa);
            miti = (TextView) itemView.findViewById(R.id.miti);
            bisaya = (TextView) itemView.findViewById(R.id.bisaya);
            itemView.setClickable(true);
        }

    }

}