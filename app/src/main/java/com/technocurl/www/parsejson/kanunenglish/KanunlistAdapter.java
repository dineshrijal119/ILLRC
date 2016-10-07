package com.technocurl.www.parsejson.kanunenglish;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.technocurl.www.parsejson.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by dinesh on 8/30/16.
 */

public class KanunlistAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<Kanunimodel> data= Collections.emptyList();

    int currentPos=0;

    // create constructor to innitilize context and data sent from SignupActivity
    public KanunlistAdapter(Context context, List<Kanunimodel> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.kanun_list_row, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
       /* if (!myHolder.itemView.isSelected()){
            NajirNepalimodel  current=data.get(position);
            myHolder.publication.setText(current.getPublication());
            myHolder.publishyear.setText(current.getPubyear());
            myHolder.month.setText(current.getMonth());
            myHolder.peg.setText(current.getPageNumber());
            myHolder.supportdef.setText(current.getSubjectCaseType());
        }else {*/
        Kanunimodel current=data.get(position);
            myHolder.sn.setText(current.getSn());
            myHolder.dafa.setText(current.getDapha());
            myHolder.details.setText(current.getDetailnow());

//        }



//        myHolder.itemView.setSelected(focusedItem == position);



    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
        TextView sn;
        TextView dafa;
        TextView details;

        public MyHolder(View itemView) {
            super(itemView);
            sn= (TextView) itemView.findViewById(R.id.sn);
            dafa = (TextView) itemView.findViewById(R.id.dafa);
            details = (TextView) itemView.findViewById(R.id.details);

            itemView.setClickable(true);
        }

    }

}