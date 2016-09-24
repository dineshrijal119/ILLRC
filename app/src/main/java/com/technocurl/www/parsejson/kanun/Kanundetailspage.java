package com.technocurl.www.parsejson.kanun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.technocurl.www.parsejson.R;
import com.technocurl.www.parsejson.model.NajirNepalimodel;

import java.util.ArrayList;

/**
 * Created by dinesh on 9/11/16.
 */
public class Kanundetailspage extends AppCompatActivity {
    TextView details;
    int position;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kanun_details);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("बिस्तृत");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        details=(TextView)findViewById(R.id.details);
        final ArrayList<Kanunimodel> myList = (ArrayList<Kanunimodel>) getIntent().getSerializableExtra("mylist");
        position = getIntent().getIntExtra("position", 0);
        details.setText(myList.get(position).getDetailnow());
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
