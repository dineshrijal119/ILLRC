package com.technocurl.www.parsejson.najiarenglish;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
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

public class NajirrecycleviewAdapterenglish extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<NajirNepalimodel> data= Collections.emptyList();
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    int currentPos=0;

    // create constructor to innitilize context and data sent from SignupActivity
    public NajirrecycleviewAdapterenglish(Context context, List<NajirNepalimodel> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
        mPref = context.getSharedPreferences("person", Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.najir_result_list_row, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;

        NajirNepalimodel  current=data.get(position);
        myHolder.publishyear.setText(current.getPubyear());
        myHolder.month.setText(current.getMonth());
        myHolder.peg.setText(current.getPageNumber());
        myHolder.supportdef.setText(current.getSubjectCaseType());
        myHolder.nirnayeno.setText(current.getNirnayeNumber());
        if (data.get(position).isSelected()) {
            holder.itemView.setBackgroundColor(Color.parseColor("#d5d5d5"));
        }


    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }
    public void add(int position, NajirNepalimodel item) {
        data.add(position, item);
        notifyItemInserted(position);
    }
    public void remove(String item) {
        int position = data.indexOf(item);
        data.remove(position);
        notifyItemRemoved(position);
    }
    public void setSelected(int pos) {
        try {
            if (data.size() > 1) {
                data.get(mPref.getInt("position", 0)).setSelected(false);;
                mEditor.putInt("position", pos);
                mEditor.commit();
            }
            data.get(pos).setSelected(false);;
            notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    class MyHolder extends RecyclerView.ViewHolder {
        TextView publishyear;
        TextView month;
        TextView peg;
        TextView supportdef;
        TextView nirnayeno;

        public MyHolder(View itemView) {
            super(itemView);
            publishyear = (TextView) itemView.findViewById(R.id.pub_year);
            month = (TextView) itemView.findViewById(R.id.month);
            peg = (TextView) itemView.findViewById(R.id.peg);
            supportdef = (TextView) itemView.findViewById(R.id.supprtdef);
            nirnayeno = (TextView) itemView.findViewById(R.id.nirnayeno);


        }

    }
}