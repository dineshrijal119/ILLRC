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
import com.technocurl.www.parsejson.model.NajirNepalimodel;


import java.util.Collections;
import java.util.List;

import okhttp3.internal.Util;

/**
 * Created by dinesh on 8/30/16.
 */

public class ReleatednajirAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<NajirNepalimodel> data = Collections.emptyList();

    int currentPos = 0;

    // create constructor to innitilize context and data sent from SignupActivity
    public ReleatednajirAdapter(Context context, List<NajirNepalimodel> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.kanun_list_row_releatednajir, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        NajirNepalimodel current = data.get(position);
        myHolder.sn.setText(current.getSN());
        myHolder.year.setText(current.getPubyear());
        myHolder.ijlash.setText(current.getIjlash());
        myHolder.subject.setText(current.getCaseType());

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {
        TextView sn;

        TextView year;
        TextView ijlash;
        TextView subject;

        public MyHolder(View itemView) {
            super(itemView);
            sn = (TextView) itemView.findViewById(R.id.sn);
            year = (TextView) itemView.findViewById(R.id.pubyear);
            ijlash = (TextView) itemView.findViewById(R.id.ijlash);
            subject = (TextView) itemView.findViewById(R.id.bisaye);

            itemView.setClickable(true);
        }

    }

}