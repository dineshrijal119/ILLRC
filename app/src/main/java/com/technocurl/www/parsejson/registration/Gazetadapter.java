package com.technocurl.www.parsejson.registration;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.utility.Tags;

import java.util.ArrayList;

/**
 * Created by dinesh on 9/17/16.
 */
public class Gazetadapter extends ArrayAdapter<Gazetchoosemodel> {
    Choosepackage choosepackage;

    public Gazetadapter(Context context, ArrayList<Gazetchoosemodel> users,Choosepackage choosepackage) {

        super(context, 0, users);
        this.choosepackage=choosepackage;

    }



    @Override

    public View getView(final int position, View view, ViewGroup parent) {

        // Get the data item for this position

        Gazetchoosemodel user = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view

        if (view == null) {

            view = LayoutInflater.from(getContext()).inflate(R.layout.list_iteam_pin, parent, false);

        }

        // Lookup view for data population
        TextView package_content = (TextView) view.findViewById(R.id.package_content);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.check);
        package_content.setText(user.name);
        checkBox.setChecked(false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                    if (isChecked == true) {
                                                        choosepackage.insertGazet(getItem(position).getName());
                                                        Log.d(Tags.TAG, "positon : " + position);
                                                    }

                                                }
                                            }
        );








        return view;

    }
}
