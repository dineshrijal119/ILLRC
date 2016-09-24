package com.technocurl.www.parsejson;

/**
 * Created by dinesh on 8/16/16.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CSharp05-user on 14/08/2016.
 */
public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> datas;

    public SpinnerAdapter(Context context, int resource, ArrayList<String> datas) {
        super(context, resource);
        this.context = context;
        this.datas = datas;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.top_off_spinner_layout, parent, false);
        TextView textView = (TextView) view.findViewById(R.id.spinner_text);
        textView.setText(datas.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View dropDownView = inflater.inflate(R.layout.drop_down_view, parent, false);

        TextView dd_GoText = (TextView) dropDownView.findViewById(R.id.gotext);
        dd_GoText.setText(datas.get(position));

        animate(dropDownView, position);
        return dropDownView;

    }

    public int getCount() {
        return datas.size();
    }

    public String getItem(int position) {
        return datas.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    private void animate(View target, int index) {

        Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_top_to_bottom);
//        animation.setStartOffset(index * 100);
        target.startAnimation(animation);
    }
}